package com.herbert.wanderbyway.dataprovider.database.trainStation;

import com.herbert.wanderbyway.dataprovider.database.airport.AirportModel;
import jakarta.persistence.*;

@Entity
@Table(name = "station", schema = "public")
public class TrainStationModel {

    public TrainStationModel() {
    }

    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "slug")
    private String slug;

    @Column(name = "uic")
    private String uic;

    @Column(name = "uic8sncf")
    private String uic8sncf;

    @Column(name = "latitude")
    private String latitude;

    @Column(name = "longitude")
    private String longitude;

    @Column(name = "\"parentStationId\"")
    private String parentStationId;

    @Column(name = "country")
    private String country;

    @Column(name = "\"timeZone\"")
    private String timeZone;

    @Column(name = "\"isCity\"")
    private Boolean isCity;

    @Column(name = "\"isMainStation\"")
    private Boolean isMainStation;

    @Column(name = "\"isAirport\"")
    private Boolean isAirport;

    @Column(name = "\"sncfId\"")
    private String sncfId;

    @Column(name = "\"sncfTvsId\"")
    private String sncfTvsId;

    @Column(name = "\"sncfIsEnabled\"")
    private Boolean sncfIsEnabled;

    @Column(name = "\"enturId\"")
    private String enturId;

    @Column(name = "\"enturIsEnabled\"")
    private Boolean enturIsEnabled;

    @Column(name = "\"dbId\"")
    private String dbId;

    @Column(name = "\"dbIsEnabled\"")
    private Boolean dbIsEnabled;

    @Column(name = "\"busbudId\"")
    private String busbudId;

    @Column(name = "\"busbudIsEnabled\"")
    private Boolean busbudIsEnabled;

    @Column(name = "\"distribusionId\"")
    private String distribusionId;

    @Column(name = "\"distribusionIsEnabled\"")
    private Boolean distribusionIsEnabled;

    @Column(name = "\"flixbusId\"")
    private String flixbusId;

    @Column(name = "\"flixbusIsEnabled\"")
    private Boolean flixbusIsEnabled;

    @Column(name = "\"cffId\"")
    private String cffId;

    @Column(name = "\"cffIsEnabled\"")
    private Boolean cffIsEnabled;

    @Column(name = "\"leoexpressId\"")
    private String leoexpressId;

    @Column(name = "\"leoexpressIsEnabled\"")
    private Boolean leoexpressIsEnabled;

    @Column(name = "\"obbId\"")
    private String obbId;

    @Column(name = "\"obbIsEnabled\"")
    private Boolean obbIsEnabled;

    @Column(name = "\"ouigoId\"")
    private String ouigoId;

    @Column(name = "\"ouigoIsEnabled\"")
    private Boolean ouigoIsEnabled;

    @Column(name = "\"trenitaliaId\"")
    private String trenitaliaId;

    @Column(name = "\"trenitaliaIsEnabled\"")
    private Boolean trenitaliaIsEnabled;

    @Column(name = "\"trenitaliaRtvtId\"")
    private String trenitaliaRtvtId;

    @Column(name = "\"trenordId\"")
    private String trenordId;

    @Column(name = "\"ntvRtivId\"")
    private String ntvRtivId;

    @Column(name = "\"ntvId\"")
    private String ntvId;

    @Column(name = "\"ntvIsEnabled\"")
    private Boolean ntvIsEnabled;

    @Column(name = "\"hkxId\"")
    private String hkxId;

    @Column(name = "\"hkxIsEnabled\"")
    private Boolean hkxIsEnabled;

    @Column(name = "\"renfeId\"")
    private String renfeId;

    @Column(name = "\"renfeIsEnabled\"")
    private Boolean renfeIsEnabled;

    @Column(name = "\"atocId\"")
    private String atocId;

    @Column(name = "\"atocIsEnabled\"")
    private Boolean atocIsEnabled;

    @Column(name = "\"benerailId\"")
    private String benerailId;

    @Column(name = "\"benerailIsEnabled\"")
    private Boolean benerailIsEnabled;

    @Column(name = "\"westbahnId\"")
    private String westbahnId;

    @Column(name = "\"westbahnIsEnabled\"")
    private Boolean westbahnIsEnabled;

    @Column(name = "\"sncfSelfServiceMachine\"")
    private Boolean sncfSelfServiceMachine;

    @Column(name = "\"sameAs\"")
    private String sameAs;

    @Column(name = "\"normalisedCode\"")
    private String normalisedCode;

    @ManyToOne
    @JoinColumn(name = "iataAirportCode", referencedColumnName = "iata")
    private AirportModel airport;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getUic() {
        return uic;
    }

    public void setUic(String uic) {
        this.uic = uic;
    }

    public String getUic8sncf() {
        return uic8sncf;
    }

    public void setUic8Sncf(String uic8sncf) {
        this.uic8sncf = uic8sncf;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getParentStationId() {
        return parentStationId;
    }

    public void setParentStationId(String parentStationId) {
        this.parentStationId = parentStationId;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public Boolean getCity() {
        return isCity;
    }

    public void setCity(Boolean city) {
        isCity = city;
    }

    public Boolean getMainStation() {
        return isMainStation;
    }

    public void setMainStation(Boolean mainStation) {
        isMainStation = mainStation;
    }

    public Boolean getAirport() {
        return isAirport;
    }

    public void setAirport(AirportModel airport) {
        this.airport = airport;
    }

    public void setAirport(Boolean airport) {
        isAirport = airport;
    }

    public String getSncfId() {
        return sncfId;
    }

    public void setSncfId(String sncfId) {
        this.sncfId = sncfId;
    }

    public String getSncfTvsId() {
        return sncfTvsId;
    }

    public void setSncfTvsId(String sncfTvsId) {
        this.sncfTvsId = sncfTvsId;
    }

    public Boolean getSncfIsEnabled() {
        return sncfIsEnabled;
    }

    public void setSncfIsEnabled(Boolean sncfIsEnabled) {
        this.sncfIsEnabled = sncfIsEnabled;
    }

    public String getEnturId() {
        return enturId;
    }

    public void setEnturId(String enturId) {
        this.enturId = enturId;
    }

    public Boolean getEnturIsEnabled() {
        return enturIsEnabled;
    }

    public void setEnturIsEnabled(Boolean enturIsEnabled) {
        this.enturIsEnabled = enturIsEnabled;
    }

    public String getDbId() {
        return dbId;
    }

    public void setDbId(String dbId) {
        this.dbId = dbId;
    }

    public Boolean getDbIsEnabled() {
        return dbIsEnabled;
    }

    public void setDbIsEnabled(Boolean dbIsEnabled) {
        this.dbIsEnabled = dbIsEnabled;
    }

    public String getBusbudId() {
        return busbudId;
    }

    public void setBusbudId(String busbudId) {
        this.busbudId = busbudId;
    }

    public Boolean getBusbudIsEnabled() {
        return busbudIsEnabled;
    }

    public void setBusbudIsEnabled(Boolean busbudIsEnabled) {
        this.busbudIsEnabled = busbudIsEnabled;
    }

    public String getDistribusionId() {
        return distribusionId;
    }

    public void setDistribusionId(String distribusionId) {
        this.distribusionId = distribusionId;
    }

    public Boolean getDistribusionIsEnabled() {
        return distribusionIsEnabled;
    }

    public void setDistribusionIsEnabled(Boolean distribusionIsEnabled) {
        this.distribusionIsEnabled = distribusionIsEnabled;
    }

    public String getFlixbusId() {
        return flixbusId;
    }

    public void setFlixbusId(String flixbusId) {
        this.flixbusId = flixbusId;
    }

    public Boolean getFlixbusIsEnabled() {
        return flixbusIsEnabled;
    }

    public void setFlixbusIsEnabled(Boolean flixbusIsEnabled) {
        this.flixbusIsEnabled = flixbusIsEnabled;
    }

    public String getCffId() {
        return cffId;
    }

    public void setCffId(String cffId) {
        this.cffId = cffId;
    }

    public Boolean getCffIsEnabled() {
        return cffIsEnabled;
    }

    public void setCffIsEnabled(Boolean cffIsEnabled) {
        this.cffIsEnabled = cffIsEnabled;
    }

    public String getLeoexpressId() {
        return leoexpressId;
    }

    public void setLeoexpressId(String leoexpressId) {
        this.leoexpressId = leoexpressId;
    }

    public Boolean getLeoexpressIsEnabled() {
        return leoexpressIsEnabled;
    }

    public void setLeoexpressIsEnabled(Boolean leoexpressIsEnabled) {
        this.leoexpressIsEnabled = leoexpressIsEnabled;
    }

    public String getObbId() {
        return obbId;
    }

    public void setObbId(String obbId) {
        this.obbId = obbId;
    }

    public Boolean getObbIsEnabled() {
        return obbIsEnabled;
    }

    public void setObbIsEnabled(Boolean obbIsEnabled) {
        this.obbIsEnabled = obbIsEnabled;
    }

    public String getOuigoId() {
        return ouigoId;
    }

    public void setOuigoId(String ouigoId) {
        this.ouigoId = ouigoId;
    }

    public Boolean getOuigoIsEnabled() {
        return ouigoIsEnabled;
    }

    public void setOuigoIsEnabled(Boolean ouigoIsEnabled) {
        this.ouigoIsEnabled = ouigoIsEnabled;
    }

    public String getTrenitaliaId() {
        return trenitaliaId;
    }

    public void setTrenitaliaId(String trenitaliaId) {
        this.trenitaliaId = trenitaliaId;
    }

    public Boolean getTrenitaliaIsEnabled() {
        return trenitaliaIsEnabled;
    }

    public void setTrenitaliaIsEnabled(Boolean trenitaliaIsEnabled) {
        this.trenitaliaIsEnabled = trenitaliaIsEnabled;
    }

    public String getTrenitaliaRtvtId() {
        return trenitaliaRtvtId;
    }

    public void setTrenitaliaRtvtId(String trenitaliaRtvtId) {
        this.trenitaliaRtvtId = trenitaliaRtvtId;
    }

    public String getTrenordId() {
        return trenordId;
    }

    public void setTrenordId(String trenordId) {
        this.trenordId = trenordId;
    }

    public String getNtvRtivId() {
        return ntvRtivId;
    }

    public void setNtvRtivId(String ntvRtivId) {
        this.ntvRtivId = ntvRtivId;
    }

    public String getNtvId() {
        return ntvId;
    }

    public void setNtvId(String ntvId) {
        this.ntvId = ntvId;
    }

    public Boolean getNtvIsEnabled() {
        return ntvIsEnabled;
    }

    public void setNtvIsEnabled(Boolean ntvIsEnabled) {
        this.ntvIsEnabled = ntvIsEnabled;
    }

    public String getHkxId() {
        return hkxId;
    }

    public void setHkxId(String hkxId) {
        this.hkxId = hkxId;
    }

    public Boolean getHkxIsEnabled() {
        return hkxIsEnabled;
    }

    public void setHkxIsEnabled(Boolean hkxIsEnabled) {
        this.hkxIsEnabled = hkxIsEnabled;
    }

    public String getRenfeId() {
        return renfeId;
    }

    public void setRenfeId(String renfeId) {
        this.renfeId = renfeId;
    }

    public Boolean getRenfeIsEnabled() {
        return renfeIsEnabled;
    }

    public void setRenfeIsEnabled(Boolean renfeIsEnabled) {
        this.renfeIsEnabled = renfeIsEnabled;
    }

    public String getAtocId() {
        return atocId;
    }

    public void setAtocId(String atocId) {
        this.atocId = atocId;
    }

    public Boolean getAtocIsEnabled() {
        return atocIsEnabled;
    }

    public void setAtocIsEnabled(Boolean atocIsEnabled) {
        this.atocIsEnabled = atocIsEnabled;
    }

    public String getBenerailId() {
        return benerailId;
    }

    public void setBenerailId(String benerailId) {
        this.benerailId = benerailId;
    }

    public Boolean getBenerailIsEnabled() {
        return benerailIsEnabled;
    }

    public void setBenerailIsEnabled(Boolean benerailIsEnabled) {
        this.benerailIsEnabled = benerailIsEnabled;
    }

    public String getWestbahnId() {
        return westbahnId;
    }

    public void setWestbahnId(String westbahnId) {
        this.westbahnId = westbahnId;
    }

    public Boolean getWestbahnIsEnabled() {
        return westbahnIsEnabled;
    }

    public void setWestbahnIsEnabled(Boolean westbahnIsEnabled) {
        this.westbahnIsEnabled = westbahnIsEnabled;
    }

    public Boolean getSncfSelfServiceMachine() {
        return sncfSelfServiceMachine;
    }

    public void setSncfSelfServiceMachine(Boolean sncfSelfServiceMachine) {
        this.sncfSelfServiceMachine = sncfSelfServiceMachine;
    }

    public String getSameAs() {
        return sameAs;
    }

    public void setSameAs(String sameAs) {
        this.sameAs = sameAs;
    }

    public String getNormalisedCode() {
        return normalisedCode;
    }

    public void setNormalisedCode(String normalisedCode) {
        this.normalisedCode = normalisedCode;
    }
}
