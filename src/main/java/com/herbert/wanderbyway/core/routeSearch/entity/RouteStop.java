package com.herbert.wanderbyway.core.routeSearch.entity;

public class RouteStop {
    private RouteSearchItemPlace stop;
    private final String arrival;
    private final String plannedArrival;
    private final String arrivalDelay;
    private final String arrivalPlatform;
    private final String plannedDeparture;
    private final String departureDelay;

    public RouteStop(
            String arrival,
            String plannedArrival,
            String arrivalDelay,
            String arrivalPlatform,
            String plannedDeparture,
            String departureDelay,
            RouteSearchItemPlace stop
    ) {
        this.stop = stop;
        this.arrival = arrival;
        this.plannedArrival = plannedArrival;
        this.arrivalDelay = arrivalDelay;
        this.arrivalPlatform = arrivalPlatform;
        this.plannedDeparture = plannedDeparture;
        this.departureDelay = departureDelay;
    }

    public String getArrival() {
        return arrival;
    }

    public String getPlannedArrival() {
        return plannedArrival;
    }

    public String getArrivalDelay() {
        return arrivalDelay;
    }

    public String getArrivalPlatform() {
        return arrivalPlatform;
    }

    public String getPlannedDeparture() {
        return plannedDeparture;
    }

    public String getDepartureDelay() {
        return departureDelay;
    }

    public RouteSearchItemPlace getStop() {
        return stop;
    }

    public void setStop(RouteSearchItemPlace stop){
        this.stop = stop;
    }

    public String getDbId(){
        return stop.dbId;
    }
}
