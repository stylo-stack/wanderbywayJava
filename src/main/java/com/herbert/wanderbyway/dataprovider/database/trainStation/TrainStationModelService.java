package com.herbert.wanderbyway.dataprovider.database.trainStation;

import com.herbert.wanderbyway.core.routeSearch.connectors.FindRouteSearchStationById;
import com.herbert.wanderbyway.core.routeSearch.connectors.FindStationsFromDbId;
import com.herbert.wanderbyway.core.routeSearch.entity.RouteSearchTrainStation;
import com.herbert.wanderbyway.core.search.connectors.FindSearchStationById;
import com.herbert.wanderbyway.core.search.connectors.FindTrainStationsByName;
import com.herbert.wanderbyway.core.search.entity.SearchItem;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TrainStationModelService implements FindTrainStationsByName, FindRouteSearchStationById, FindStationsFromDbId, FindSearchStationById {
    private final TrainStationModelMapper trainStationModelMapper;
    private final TrainStationModelRepository trainStationModelRepository;

    public TrainStationModelService(TrainStationModelMapper trainStationModelMapper, TrainStationModelRepository trainStationModelRepository) {
        this.trainStationModelMapper = trainStationModelMapper;
        this.trainStationModelRepository = trainStationModelRepository;
    }

    @Override
    public List<SearchItem> findByName(String query) {
        return trainStationModelMapper.toSearchResults(trainStationModelRepository.findBySlugContainingAndDbIdIsPresent(query));
    }

    @Override
    public RouteSearchTrainStation findRouteSearchStationById(int id) {
        return trainStationModelRepository.findById(id).map(trainStationModelMapper::toRouteSearchTrainStation).orElse(null);
    }

    @Override
    public List<RouteSearchTrainStation> findFromDbId(List<String> dbIds) {
        return trainStationModelMapper.toRouteSearchTrainStation(trainStationModelRepository.findByDbIdIn(dbIds));
    }

    @Override
    public SearchItem findSearchStationById(int id) {
        return trainStationModelRepository.findById(id).map(trainStationModelMapper::toSearchResult).orElse(null);
    }
}
