package com.herbert.wanderbyway.dataprovider.trainRoute;

import com.herbert.wanderbyway.core.routeSearch.entity.*;
import com.herbert.wanderbyway.dataprovider.trainRoute.entity.common.TrainRouteStop;
import com.herbert.wanderbyway.dataprovider.trainRoute.entity.departures.TrainRoute;
import com.herbert.wanderbyway.dataprovider.trainRoute.entity.trip.TrainRouteTrip;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class TrainRouteMapper {
    RouteSearchItem toRouteSearchItem(TrainRoute route) {
        return new RouteSearchItem(
                RouteSearchItemType.TRAIN,
                toPlace(route.getDestination()),
                toPlace(route.getOrigin()),
                0,
                route.getWhen(),
                null,
                route.getProviders(),
                toPublicRouteId(route.getTripId())
        );
    }

    List<RouteSearchItem> toRouteSearchItem(List<TrainRoute> routes) {
        return routes.stream().map(this::toRouteSearchItem).toList();
    }

    RouteDetails toTrainRouteDetails(TrainRouteTrip trip) {
        List<RouteStop> stops = trip.getStopovers().stream()
                .map(stop -> new RouteStop(
                        stop.getArrival(),
                        stop.getPlannedArrival(),
                        stop.getArrivalDelay(),
                        stop.getArrivalPlatform(),
                        stop.getPlannedDeparture(),
                        stop.getDepartureDelay(),
                        toPlace(stop.getStop())
                ))
                .toList();

        return new RouteDetails(
                toPlace(trip.getOrigin()),
                toPlace(trip.getDestination()),
                trip.getDeparture(),
                trip.getArrival(),
                trip.getDuration(),
                toPublicRouteId(trip.getId()),
                RouteSearchItemType.TRAIN,
                stops,
                List.of(trip.getLine().getOperator().getId())
        );
    }

    private RouteSearchItemPlace toPlace(TrainRouteStop stop) {
        return new RouteSearchItemPlace(
                stop.getName(),
                null,
                RouteSearchItemPlaceType.TRAIN_STATION,
                stop.getLocation().getLongitude(),
                stop.getLocation().getLatitude(),
                null,
                null,
                stop.getId()
        );
    }

    // '|' in upstream trip ids is not URL friendly, so ids are exposed with '-' instead
    private String toPublicRouteId(String tripId) {
        return tripId.replace("|", "-");
    }
}
