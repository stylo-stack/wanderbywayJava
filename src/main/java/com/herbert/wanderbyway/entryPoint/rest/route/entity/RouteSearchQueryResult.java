package com.herbert.wanderbyway.entryPoint.rest.route.entity;

import com.herbert.wanderbyway.core.routeSearch.entity.RouteSearchItem;
import com.herbert.wanderbyway.core.routeSearch.entity.RouteSearchItemPlace;
import com.herbert.wanderbyway.core.routeSearch.entity.RouteSearchResult;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RouteSearchQueryResult {
    private final RouteSearchItemPlace origin;
    private final List<DestinationGroup> destinations;
    private final int routeCount;
    private final int destinationCount;

    public RouteSearchQueryResult(RouteSearchResult searchResult){
        Map<String,List<RouteSearchItem>> groupedItems = searchResult.getResults().stream().collect(Collectors.groupingBy(it -> it.getDestination().getId()));
        this.destinations = groupedItems.values().stream().map(DestinationGroup::new).collect(Collectors.toCollection(ArrayList::new));
        this.routeCount = searchResult.getResults().size();
        this.destinationCount = destinations.size();
        this.origin = searchResult.getOrigin();
    }

    public List<DestinationGroup> getDestinations() {
        return destinations;
    }

    public int getRouteCount() {
        return this.routeCount;
    }

    public int getDestinationCount(){
        return this.destinationCount;
    }

    public RouteSearchItemPlace getOrigin(){
        return this.origin;
    }

    public void sortForSearch(){
        destinations.sort(Comparator.comparing(group -> group.getDestination().getName(), String.CASE_INSENSITIVE_ORDER));
        destinations.forEach(DestinationGroup::sortByDateAsc);
    }
}
