package com.herbert.wanderbyway.dataprovider.trainRoute.entity.common;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TrainRouteLine {
    String line;
    String id;
    String fahrNr;
    String name;
    @JsonProperty("public")
    Boolean isPublic;
    String adminCode;
    String productName;
    String mode;
    String product;
    TrainRouteLineOperator operator;

    public TrainRouteLine() {
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFahrNr() {
        return fahrNr;
    }

    public void setFahrNr(String fahrNr) {
        this.fahrNr = fahrNr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getPublic() {
        return isPublic;
    }

    public void setPublic(Boolean aPublic) {
        isPublic = aPublic;
    }

    public String getAdminCode() {
        return adminCode;
    }

    public void setAdminCode(String adminCode) {
        this.adminCode = adminCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public TrainRouteLineOperator getOperator() {
        return operator;
    }

    public void setOperator(TrainRouteLineOperator operator) {
        this.operator = operator;
    }
}
