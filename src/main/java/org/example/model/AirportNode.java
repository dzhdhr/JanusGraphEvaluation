package org.example.model;

import lombok.Data;

@Data
public class AirportNode {
    private String city;
    private Double lat;
    private Double lon;
    private String dist;
    private String identity;
    private String type;
    private String code;
    private String icao;
    private String desc;
    private String region;
    private Integer runways;
    private Integer longest;
    private Integer elev;
    private String country;
}