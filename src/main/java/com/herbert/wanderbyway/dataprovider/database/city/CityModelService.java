package com.herbert.wanderbyway.dataprovider.database.city;

import com.herbert.wanderbyway.core.routeSearch.connectors.FindRouteSearchCityById;
import com.herbert.wanderbyway.core.routeSearch.entity.RouteSearchCity;
import com.herbert.wanderbyway.core.search.connectors.FindCitiesByName;
import com.herbert.wanderbyway.core.search.connectors.FindSearchCityById;
import com.herbert.wanderbyway.core.search.entity.SearchItem;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CityModelService implements FindCitiesByName, FindRouteSearchCityById, FindSearchCityById {
    private final CityModelRepository cityModelRepository;
    private final CityModelMapper cityModelMapper;

    public CityModelService(CityModelRepository cityModelRepository, CityModelMapper cityModelMapper) {
        this.cityModelRepository = cityModelRepository;
        this.cityModelMapper = cityModelMapper;
    }

    @Override
    public List<SearchItem> findByName(String query) {
        return cityModelMapper.toSearchResults(cityModelRepository.findBySlugContaining(query));
    }

    @Override
    public RouteSearchCity findRouteSearchCityById(int id) {
        return cityModelRepository.findById(id).map(cityModelMapper::toRouteSearchCity).orElse(null);
    }

    @Override
    public SearchItem findSearchCityById(int id) {
        return cityModelRepository.findById(id).map(cityModelMapper::toSearchResult).orElse(null);
    }
}
