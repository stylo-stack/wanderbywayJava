package com.herbert.wanderbyway.dataprovider.flight;

import com.herbert.wanderbyway.core.routeSearch.connectors.FindFlightsFromAirport;
import com.herbert.wanderbyway.core.routeSearch.entity.RouteSearchItem;
import com.herbert.wanderbyway.dataprovider.flight.entity.FlightSearchParams;
import com.herbert.wanderbyway.dataprovider.flight.entity.FlightSearchResponse;
import com.herbert.wanderbyway.utils.DateFormat;
import com.herbert.wanderbyway.utils.DateUtils;
import com.herbert.wanderbyway.utils.rest.Header;
import com.herbert.wanderbyway.utils.rest.RestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class FlightService implements FindFlightsFromAirport {
    private static final Logger log = LoggerFactory.getLogger(FlightService.class);

    private final FlightSearchMapper flightSearchMapper;
    private final RestUtils restUtils;

    public FlightService(
            @Value("${tequila.apiKey}") String tequilaApiKey,
            @Value("${tequila.baseUrl}") String baseUrl,
            RestTemplate restTemplate,
            FlightSearchMapper flightSearchMapper
    ) {
        this.flightSearchMapper = flightSearchMapper;
        this.restUtils = new RestUtils(restTemplate, baseUrl, List.of(new Header("apiKey", tequilaApiKey)));
    }

    @Override
    public List<RouteSearchItem> findFlights(String iata, Date startDate, Date endDate) {
        try {
            FlightSearchParams params = new FlightSearchParams();
            params.setOrigin(iata);
            params.setDateFrom(DateUtils.formatDateToString(startDate, DateFormat.TEQUILA));
            params.setDateTo(DateUtils.formatDateToString(endDate, DateFormat.TEQUILA));
            FlightSearchResponse response = restUtils.call("/search", HttpMethod.GET, params, FlightSearchResponse.class);
            if (response == null) return new ArrayList<>();
            return flightSearchMapper.toRouteSearchItems(response.getData());
        } catch (Exception e) {
            log.warn("Failed to fetch flights from {}", iata, e);
            return new ArrayList<>();
        }
    }
}
