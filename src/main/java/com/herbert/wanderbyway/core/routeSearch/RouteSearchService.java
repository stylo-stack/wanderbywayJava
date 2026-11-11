package com.herbert.wanderbyway.core.routeSearch;

import com.herbert.wanderbyway.core.routeSearch.connectors.*;
import com.herbert.wanderbyway.core.routeSearch.entity.*;
import com.herbert.wanderbyway.core.routeSearch.useCases.FindRoutesFromPlaceUseCase;
import com.herbert.wanderbyway.core.routeSearch.useCases.GetRouteDetailsUseCase;
import com.herbert.wanderbyway.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class RouteSearchService implements FindRoutesFromPlaceUseCase, GetRouteDetailsUseCase {
    private final FindFlightsFromAirport findFlightsFromAirport;
    private final FindRoutesFromDbId findRoutesFromDbId;
    private final FindAirportsFromIata findAirportsFromIata;
    private final FindRouteSearchAirportById findRouteSearchAirportById;
    private final FindRouteSearchStationById findRouteSearchStationById;
    private final FindStationsFromDbId findStationsFromDbId;
    private final FindRouteSearchCityById findRouteSearchCityById;
    private final GetTrainRouteDetails getTrainRouteDetails;

    public RouteSearchService(
            FindFlightsFromAirport findFlightsFromAirport,
            FindAirportsFromIata findAirportsFromIata,
            FindRouteSearchAirportById findRouteSearchAirportById,
            FindRoutesFromDbId findRoutesFromDbId,
            FindStationsFromDbId findStationsFromDbId,
            FindRouteSearchStationById findRouteSearchStationById,
            FindRouteSearchCityById findRouteSearchCityById,
            GetTrainRouteDetails getTrainRouteDetails
    ) {
        this.findFlightsFromAirport = findFlightsFromAirport;
        this.findAirportsFromIata = findAirportsFromIata;
        this.findRouteSearchAirportById = findRouteSearchAirportById;
        this.findRoutesFromDbId = findRoutesFromDbId;
        this.findStationsFromDbId = findStationsFromDbId;
        this.findRouteSearchStationById = findRouteSearchStationById;
        this.findRouteSearchCityById = findRouteSearchCityById;
        this.getTrainRouteDetails = getTrainRouteDetails;
    }

    @Override
    public RouteSearchResult findRoutes(
            int id,
            RouteSearchItemPlaceType type,
            Date startDate,
            Date endDate
    ) throws NotFoundException {
        switch (type) {
            case AIRPORT -> {
                RouteSearchAirport origin = Optional.ofNullable(findRouteSearchAirportById.findRouteSearchAirportById(id))
                        .orElseThrow(() -> new NotFoundException("Could not find airport for id: " + id));
                return new RouteSearchResult(getFlightRoutes(origin, startDate, endDate), new RouteSearchItemPlace(origin));
            }
            case TRAIN_STATION -> {
                RouteSearchTrainStation origin = Optional.ofNullable(findRouteSearchStationById.findRouteSearchStationById(id))
                        .orElseThrow(() -> new NotFoundException("Could not find station for id: " + id));
                return new RouteSearchResult(getTrainRoutes(origin, startDate, endDate), new RouteSearchItemPlace(origin));
            }
            case CITY -> {
                RouteSearchCity origin = Optional.ofNullable(findRouteSearchCityById.findRouteSearchCityById(id))
                        .orElseThrow(() -> new NotFoundException("Could not find city for id: " + id));
                List<RouteSearchItem> routes = new ArrayList<>();
                origin.getAirports().forEach(it -> routes.addAll(getFlightRoutes(it, startDate, endDate)));
                origin.getStations().forEach(it -> routes.addAll(getTrainRoutes(it, startDate, endDate)));
                return new RouteSearchResult(routes, new RouteSearchItemPlace(origin));
            }
            default -> throw new NotFoundException("Route search is not supported for type " + type + " (id: " + id + ")");
        }
    }

    private List<RouteSearchItem> getTrainRoutes(RouteSearchTrainStation origin, Date startDate, Date endDate) {
        if (!origin.hasDbId()) return new ArrayList<>();
        List<RouteSearchItem> routes = findRoutesFromDbId.findRoutesWithDbId(origin.getDbId(), startDate, endDate);
        List<String> dbIds = routes.stream().map(RouteSearchItem::getDestinationDbId).toList();
        Map<String, RouteSearchTrainStation> destinations = indexBy(findStationsFromDbId.findFromDbId(dbIds), RouteSearchTrainStation::getDbId);

        List<RouteSearchItem> result = new ArrayList<>();
        routes.forEach(route -> {
            RouteSearchTrainStation match = destinations.get(route.getDestinationDbId());
            if (match != null) {
                route.completePlaces(match, origin);
                result.add(route);
            }
        });
        return result;
    }

    private List<RouteSearchItem> getFlightRoutes(RouteSearchAirport origin, Date startDate, Date endDate) {
        List<RouteSearchItem> flights = findFlightsFromAirport.findFlights(origin.getIata(), startDate, endDate);
        if (flights == null) return new ArrayList<>();
        List<String> iatas = flights.stream().map(RouteSearchItem::getDestinationIata).toList();
        Map<String, RouteSearchAirport> destinations = indexBy(findAirportsFromIata.findFromIataIn(iatas), RouteSearchAirport::getIata);

        List<RouteSearchItem> result = new ArrayList<>();
        flights.forEach(flight -> {
            RouteSearchAirport match = destinations.get(flight.getDestinationIata());
            if (match != null) {
                flight.completePlaces(match, origin);
                result.add(flight);
            }
        });
        return result;
    }

    @Override
    public RouteDetails findRouteDetails(String routeId) {
        return getTrainRouteDetails.getRouteDetails(routeId);
    }

    @Override
    public List<RouteDetails> findRouteDetails(List<String> routeIds, RouteSearchItemPlaceType type) {
        if (type == RouteSearchItemPlaceType.TRAIN_STATION) {
            return findTrainRouteDetails(routeIds);
        }
        return List.of();
    }

    private List<RouteDetails> findTrainRouteDetails(List<String> routeIds) {
        List<RouteDetails> routes = getTrainRouteDetails.getRouteDetails(routeIds);
        List<String> dbIds = routes.stream().flatMap(RouteDetails::retrieveAllDbIds).distinct().toList();
        Map<String, RouteSearchTrainStation> stations = indexBy(findStationsFromDbId.findFromDbId(dbIds), RouteSearchTrainStation::getDbId);

        routes.forEach(route -> {
            RouteSearchTrainStation origin = stations.get(route.getOrigin().getDbId());
            if (origin != null) {
                route.setOrigin(origin.toRouteSearchItemPlace());
            }
            RouteSearchTrainStation destination = stations.get(route.getDestination().getDbId());
            if (destination != null) {
                route.setDestination(destination.toRouteSearchItemPlace());
            }
            route.getStops().forEach(stop -> {
                RouteSearchTrainStation stopStation = stations.get(stop.getDbId());
                if (stopStation != null) {
                    stop.setStop(stopStation.toRouteSearchItemPlace());
                }
            });
        });
        return routes;
    }

    private static <T> Map<String, T> indexBy(List<T> items, Function<T, String> key) {
        return items.stream().collect(Collectors.toMap(key, Function.identity(), (first, duplicate) -> first));
    }
}
