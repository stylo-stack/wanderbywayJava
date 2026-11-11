package com.herbert.wanderbyway.dataprovider.database.city;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.herbert.wanderbyway.dataprovider.database.airport.AirportModel;
import com.herbert.wanderbyway.dataprovider.database.trainStation.TrainStationModel;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cities", schema = "public")
public class CityModel {

    public CityModel(){

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "mongoId")
    private String mongoId;

    @Column(name = "longitude")
    private Float longitude;

    @Column(name = "latitude")
    private Float latitude;

    @Column(name = "name")
    private String name;

    @Column(name = "slug")
    private String slug;

    @Column(name = "type")
    private String type;

    @Column(name = "population")
    private Integer population;

    @Column(name = "shareId")
    private String shareId;

    @Column(name = "country")
    private String country;

    @ManyToMany
    @JoinTable(
            name = "CityStationLink",
            joinColumns = @JoinColumn(name = "cityId"),
            inverseJoinColumns = @JoinColumn(name = "stationId")
    )
    @JsonManagedReference
    private List<TrainStationModel> stations = new ArrayList<>();


    @JoinTable(name = "CityAirportLink",
            joinColumns = @JoinColumn(name = "cityId"),
            inverseJoinColumns = @JoinColumn(name = "airportId"))
    @ManyToMany
    @JsonManagedReference
    private List<AirportModel> airports = new ArrayList<>();

    public List<TrainStationModel> getStations() {
        return stations;
    }

    public List<AirportModel> getAirports() {
        return airports;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMongoId() {
        return mongoId;
    }

    public void setMongoId(String mongoId) {
        this.mongoId = mongoId;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    public String getShareId() {
        return shareId;
    }

    public void setShareId(String shareId) {
        this.shareId = shareId;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setStations(List<TrainStationModel> stations) {
        this.stations = stations;
    }

    public void setAirports(List<AirportModel> airports) {
        this.airports = airports;
    }
}
