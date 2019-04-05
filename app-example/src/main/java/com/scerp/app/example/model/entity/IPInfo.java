package com.scerp.app.example.model.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * IP Info
 * http://ip-api.com/docs/
 */
@Data
@Accessors(chain = true)
public class IPInfo {

    /**
     * IP used for the query
     */
    private String query;
    /**
     * success or fail
     */
    private String status;
    /**
     * included only when status is fail
     * Can be one of the following: private range, reserved range, invalid query
     */
    private String message;
    /**
     * Country name
     */
    private String country;
    /**
     * Two-letter country code ISO 3166-1 alpha-2
     */
    private String countryCode;
    /**
     * Region/state short code (FIPS or ISO)
     */
    private String region;
    /**
     * Region/state
     */
    private String regionName;
    /**
     * City
     */
    private String city;
    /**
     * District (subdivision of city)
     */
    private String district;
    /**
     * Zip code
     */
    private String zip;
    /**
     * Latitude
     */
    private String lat;
    /**
     * Longitude
     */
    private String lon;
    /**
     * City timezone
     */
    private String timezone;
    /**
     * ISP name
     */
    private String isp;
    /**
     * Organization name
     */
    private String org;
    /**
     * AS number and name, separated by space
     */
    private String as;
    /**
     * Reverse DNS of the IP
     */
    private String reverse;
    /**
     * Mobile (cellular) connection
     */
    private String mobile;
    /**
     * Proxy (anonymous)
     */
    private String proxy;

}
