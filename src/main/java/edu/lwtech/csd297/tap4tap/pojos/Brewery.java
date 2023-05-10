package edu.lwtech.csd297.tap4tap.pojos;

public class Brewery {
    private int breweryId;
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
    private float longitude;
    private float latitude;

    public Brewery(int breweryId, String name, String breweryType,
            String address1, String address2, String address3, String city,
            String stateProvince, String postalCode, String country,
            String websiteUrl, String phone, float longitude, float latitude ){
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

    public int getBreweryId() {
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

    public float getLongitude() {
        return longitude;
    }

    public float getLatitude() {
        return latitude;
    }
}
