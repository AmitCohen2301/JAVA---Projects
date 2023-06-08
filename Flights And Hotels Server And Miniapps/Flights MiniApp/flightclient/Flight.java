package com.example.flightclient;

public class Flight {
    private String name;
    private String date;
    private String price;
    private String departureTime;
    private String arrivalTime;
    private String equipmentId;


    private String itineraryType;
    private String classOfService;
    public Flight(String name, String date, String price, String itineraryType , String classOfService, String equipmentId, String departureTime, String arrivalTime) {

        this.name = name;
        this.date = date;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPrice() {
        return price;
    }

    public String getItineraryType() {
        return itineraryType;
    }

    public void setItineraryType(String itineraryType) {
        this.itineraryType = itineraryType;
    }

    public String getClassOfService() {
        return classOfService;
    }

    public void setClassOfService(String classOfService) {
        this.classOfService = classOfService;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Flight:\n" + "-name:" + name + "\n-date:"+ date + "\n-price" + price + "\n-service:" + classOfService + "\n-itinerary type:" + itineraryType +"\n";
    }
}
