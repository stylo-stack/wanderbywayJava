package com.herbert.wanderbyway.dataprovider.database.airport;

import com.herbert.wanderbyway.core.routeSearch.connectors.FindAirportsFromIata;
import com.herbert.wanderbyway.core.routeSearch.connectors.FindRouteSearchAirportById;
import com.herbert.wanderbyway.core.routeSearch.entity.RouteSearchAirport;
import com.herbert.wanderbyway.core.search.connectors.FindAirportsByName;
import com.herbert.wanderbyway.core.search.connectors.FindSearchAirportById;
import com.herbert.wanderbyway.core.search.entity.SearchItem;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AirportModelService implements FindAirportsByName, FindAirportsFromIata, FindRouteSearchAirportById, FindSearchAirportById {
    private final AirportModelRepository airportModelRepository;
    private final AirportModelMapper airportModelMapper;

    public AirportModelService(AirportModelRepository airportModelRepository, AirportModelMapper airportModelMapper) {
        this.airportModelRepository = airportModelRepository;
        this.airportModelMapper = airportModelMapper;
    }

    @Override
    public List<SearchItem> findByName(String query) {
        return airportModelMapper.toSearchResults(airportModelRepository.findBySlugContaining(query));
    }

    @Override
    public List<RouteSearchAirport> findFromIataIn(List<String> iatas) {
        return airportModelMapper.toRouteSearchAirport(airportModelRepository.findByIataIn(iatas));
    }

    @Override
    public RouteSearchAirport findFromIata(String iata) {
        return airportModelMapper.toRouteSearchAirport(airportModelRepository.findByIata(iata));
    }

    @Override
    public RouteSearchAirport findRouteSearchAirportById(int id) {
        return airportModelRepository.findById(id).map(airportModelMapper::toRouteSearchAirport).orElse(null);
    }

    @Override
    public SearchItem findSearchAirportById(int id) {
        return airportModelRepository.findById(id).map(airportModelMapper::toSearchResult).orElse(null);
    }
}
