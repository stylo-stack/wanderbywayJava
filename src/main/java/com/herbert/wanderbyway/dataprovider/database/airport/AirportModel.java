package com.herbert.wanderbyway.dataprovider.database.airport;

import jakarta.persistence.*;

@Entity
@Table(name = "airports", schema = "public", uniqueConstraints = {
        @UniqueConstraint(columnNames = "iata"),
        @UniqueConstraint(columnNames = "icao")
})
public class AirportModel {

    public AirportModel(){

    }

    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;

    @Column(name = "country")
    private String country;

    @Column(name = "name")
    private String name;

    @Column(name = "iata", unique = true)
    private String iata;

    @Column(name = "icao", unique = true)
    private String icao;

    @Column(name = "slug")
    private String slug;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIata() {
        return iata;
    }

    public void setIata(String iata) {
        this.iata = iata;
    }

    public String getIcao() {
        return icao;
    }

    public void setIcao(String icao) {
        this.icao = icao;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }
}