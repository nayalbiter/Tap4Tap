package edu.lwtech.csd297.tap4tap.pojos;

import java.util.UUID;

public class Brewery {
    private UUID breweryId;
    private String name;
    private String breweryType;
    private String address1;
    private String address2;
    private String address3;
    private String city;
    private String stateProvince;
    private String postalCode;
    private String country;
    private String websiteUrl;
    private String phone;
    private double longitude;
    private double latitude;

    public Brewery(UUID breweryId, String name, String breweryType,
            String address1, String address2, String address3, String city,
            String stateProvince, String postalCode, String country,
            String websiteUrl, String phone, double longitude, double latitude) {
                this.breweryId = breweryId;
                this.name = name;
                this.breweryType = breweryType;
                this.address1 = address1;
                this.address2 = address2;
                this.address3 = address3;
                this.city = city;
                this.stateProvince = stateProvince;
                this.postalCode = postalCode;
                this.country = country;
                this.websiteUrl = websiteUrl;
                this.phone = phone;
                this.longitude = longitude;
                this.latitude = latitude;
    }

    public UUID getBreweryId() {
        return breweryId;
    }

    public String getName() {
        return name;
    }

    public String getBreweryType() {
        return breweryType;
    }

    public String getAddress1() {
        return address1;
    }

    public String getAddress2() {
        return address2;
    }

    public String getAddress3() {
        return address3;
    }

    public String getCity() {
        return city;
    }

    public String getStateProvince() {
        return stateProvince;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getCountry() {
        return country;
    }

    public String getWebsiteUrl() {
        return websiteUrl;
    }

    public String getPhone() {
        return phone;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    @Override
    public String toString(){
        return "Brewery ID: " + breweryId + "\n" + "Brewery Name: " + name + "\n" + "Brewery Type: " + breweryType + "\n" + "Brewery Address: " + address1 + " " + address2 + " " + address3 + " " + city + " " + stateProvince + " " + postalCode + " " + country+ ".\n" + "Website: " + websiteUrl + " Phone: " + phone;
    }
}
