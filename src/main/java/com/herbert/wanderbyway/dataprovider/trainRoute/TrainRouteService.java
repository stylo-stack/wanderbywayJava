package com.herbert.wanderbyway.dataprovider.trainRoute;

import com.herbert.wanderbyway.core.routeSearch.connectors.FindRoutesFromDbId;
import com.herbert.wanderbyway.core.routeSearch.connectors.GetTrainRouteDetails;
import com.herbert.wanderbyway.core.routeSearch.entity.RouteDetails;
import com.herbert.wanderbyway.core.routeSearch.entity.RouteSearchItem;
import com.herbert.wanderbyway.dataprovider.trainRoute.entity.departures.DepartureSearchResult;
import com.herbert.wanderbyway.dataprovider.trainRoute.entity.departures.TrainRouteSearchParams;
import com.herbert.wanderbyway.dataprovider.trainRoute.entity.trip.TripSearchResult;
import com.herbert.wanderbyway.utils.DateUtils;
import com.herbert.wanderbyway.utils.rest.RestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class TrainRouteService implements FindRoutesFromDbId, GetTrainRouteDetails {
    private static final Logger log = LoggerFactory.getLogger(TrainRouteService.class);
    private static final int MINUTES_IN_A_DAY = 1440;
    private static final int MAX_RESULTS_PER_DAY = 500;

    private final RestUtils restUtils;
    private final TrainRouteMapper trainRouteMapper;

    public TrainRouteService(RestTemplate restTemplate, @Value("${dbBahn.baseUrl}") String baseUrl, TrainRouteMapper trainRouteMapper) {
        this.restUtils = new RestUtils(restTemplate, baseUrl);
        this.trainRouteMapper = trainRouteMapper;
    }

    @Override
    public List<RouteSearchItem> findRoutesWithDbId(String dbId, Date startDate, Date endDate) {
        String path = "stops/" + dbId + "/departures";
        List<LocalDateTime> days = DateUtils.getDatesBetween(DateUtils.atMidnight(startDate), DateUtils.atMidnight(endDate));

        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        try {
            List<CompletableFuture<List<RouteSearchItem>>> futures = days.stream()
                    .map(day -> CompletableFuture.supplyAsync(() -> findDepartures(path, day), executor))
                    .toList();
            return futures.stream().map(CompletableFuture::join).flatMap(List::stream).toList();
        } finally {
            executor.shutdown();
        }
    }

    private List<RouteSearchItem> findDepartures(String path, LocalDateTime day) {
        TrainRouteSearchParams params = new TrainRouteSearchParams();
        params.setWhen(day);
        params.setDuration(MINUTES_IN_A_DAY);
        params.setResults(MAX_RESULTS_PER_DAY);
        try {
            DepartureSearchResult results = restUtils.call(path, HttpMethod.GET, params, DepartureSearchResult.class);
            if (results == null) return List.of();
            return trainRouteMapper.toRouteSearchItem(results.getDepartures());
        } catch (Exception e) {
            log.warn("Failed to fetch departures from {} on {}", path, day, e);
            return List.of();
        }
    }

    @Override
    public RouteDetails getRouteDetails(String id) {
        // Route ids are exposed with '-' in place of the '|' used by the upstream API
        String path = "trips/" + id.replace("-", "|");
        try {
            TripSearchResult results = restUtils.call(path, HttpMethod.GET, TripSearchResult.class);
            if (results == null) return null;
            return trainRouteMapper.toTrainRouteDetails(results.getTrip());
        } catch (Exception e) {
            log.warn("Failed to fetch trip {}", id, e);
            return null;
        }
    }

    @Override
    public List<RouteDetails> getRouteDetails(List<String> ids) {
        List<CompletableFuture<RouteDetails>> futures = ids.stream()
                .map(id -> CompletableFuture.supplyAsync(() -> getRouteDetails(id)))
                .toList();
        return futures.stream().map(CompletableFuture::join).filter(Objects::nonNull).toList();
    }
}
