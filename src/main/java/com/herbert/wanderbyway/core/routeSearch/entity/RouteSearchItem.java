package com.herbert.wanderbyway.core.routeSearch.entity;

import java.util.List;

public class RouteSearchItem {
    RouteSearchItemType type;
    RouteSearchItemPlace origin;
    RouteSearchItemPlace destination;
    long durationMinutes;
    String departureTime;
    String arrivalTime;
    List<String> carriers;
    String routeId;

    public RouteSearchItem(
            RouteSearchItemType type,
            RouteSearchItemPlace destination,
            RouteSearchItemPlace origin,
            long durationMinutes,
            String departureTime,
            String arrivalTime,
            List<String> carriers,
            String routeId
    ) {
        this.type = type;
        this.destination = destination;
        this.durationMinutes = durationMinutes;
        this.origin = origin;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.carriers = carriers;
        this.routeId = routeId;
    }

    public RouteSearchItem(
            RouteSearchItemType type,
            RouteSearchItemPlace destination,
            int durationMinutes,
            String departureTime,
            String arrivalTime,
            List<String> carriers,
            String routeId
    ) {
        this.type = type;
        this.destination = destination;
        this.durationMinutes = durationMinutes;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.carriers = carriers;
        this.routeId = routeId;
    }

    public void completePlaces(RouteSearchAirport toAirport, RouteSearchAirport fromAirport){
        this.origin = new RouteSearchItemPlace(fromAirport);
        this.destination.complete(toAirport);
    }

    public void completePlaces(RouteSearchTrainStation toStation, RouteSearchTrainStation fromStation){
        this.origin = new RouteSearchItemPlace(fromStation);
        this.destination.complete(toStation);
    }

    public RouteSearchItemType getType() {
        return type;
    }

    public void setType(RouteSearchItemType type) {
        this.type = type;
    }

    public RouteSearchItemPlace getOrigin() {
        return origin;
    }

    public void setOrigin(RouteSearchItemPlace origin) {
        this.origin = origin;
    }

    public RouteSearchItemPlace getDestination() {
        return destination;
    }

    public void setDestination(RouteSearchItemPlace destination) {
        this.destination = destination;
    }

    public long getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(long durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    public String getDestinationIata(){
        return this.destination.getIata();
    }
    public String getDestinationDbId(){
        return this.destination.getDbId();
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public List<String> getCarriers() {
        return carriers;
    }

    public void setCarriers(List<String> carriers) {
        this.carriers = carriers;
    }

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }
}
