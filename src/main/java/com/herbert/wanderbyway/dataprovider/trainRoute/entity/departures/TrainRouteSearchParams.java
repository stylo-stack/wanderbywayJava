package com.herbert.wanderbyway.dataprovider.trainRoute.entity.departures;

import com.herbert.wanderbyway.utils.DateFormat;
import com.herbert.wanderbyway.utils.DateUtils;
import com.herbert.wanderbyway.utils.rest.SearchParams;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class TrainRouteSearchParams implements SearchParams {
    String results; //int
    String duration; //int
    String when; //date
    String direction;
    String linesOfStops; //boolean
    String nationalExpress; //boolean
    String national; //boolean
    String regionalExpress; //boolean
    String regional; //boolean
    String suburban; //boolean
    String bus; //boolean
    String ferry; //boolean;
    String subway; //boolean
    String tram;// boolean
    String taxi; //boolean

    public TrainRouteSearchParams(){}

    public void setResults(int results) {
        this.results = String.valueOf(results);
    }

    public void setDuration(int duration) {
        this.duration = String.valueOf(duration);
    }

    public void setWhen(LocalDateTime when) {
        setWhen(when.atZone(ZoneId.systemDefault()));
    }

    public void setWhen(ZonedDateTime when){
        this.when = DateUtils.formatDateToString(when, DateFormat.DB_BAHN);
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public void setLinesOfStops(boolean linesOfStops) {
        this.linesOfStops = String.valueOf(linesOfStops);
    }

    public void setNationalExpress(boolean nationalExpress) {
        this.nationalExpress = String.valueOf(nationalExpress);
    }

    public void setNational(boolean national) {
        this.national = String.valueOf(national);
    }

    public void setRegionalExpress(boolean regionalExpress) {
        this.regionalExpress = String.valueOf(regionalExpress);
    }

    public void setRegional(boolean regional) {
        this.regional = String.valueOf(regional);
    }

    public void setSuburban(boolean suburban) {
        this.suburban = String.valueOf(suburban);
    }

    public void setBus(boolean bus) {
        this.bus = String.valueOf(bus);
    }

    public void setFerry(boolean ferry) {
        this.ferry = String.valueOf(ferry);
    }

    public void setSubway(boolean subway) {
        this.subway = String.valueOf(subway);
    }

    public void setTram(boolean tram) {
        this.tram = String.valueOf(tram);
    }

    public void setTaxi(boolean taxi) {
        this.taxi = String.valueOf(taxi);
    }

    public MultiValueMap<String, String> toMap() {
        MultiValueMap<String, String> items = new LinkedMultiValueMap<>();

        if (results != null) items.add("results", results);
        if (duration != null) items.add("duration", duration);
        if (when != null) items.add("when", when);
        if (direction != null) items.add("direction", direction);
        if (linesOfStops != null) items.add("linesOfStops", linesOfStops);
        if (nationalExpress != null) items.add("nationalExpress", nationalExpress);
        if (national != null) items.add("national", national);
        if (regionalExpress != null) items.add("regionalExpress", regionalExpress);
        if (regional != null) items.add("regional", regional);
        if (suburban != null) items.add("suburban", suburban);
        if (bus != null) items.add("bus", bus);
        if (ferry != null) items.add("ferry", ferry);
        if (subway != null) items.add("subway", subway);
        if (tram != null) items.add("tram", tram);
        if (taxi != null) items.add("taxi", taxi);

        return items;
    }
}
