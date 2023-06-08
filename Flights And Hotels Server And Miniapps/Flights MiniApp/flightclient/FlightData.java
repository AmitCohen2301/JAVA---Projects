package com.example.flightclient;

public class FlightData {
    private String originStationCode;
    private String destinationStationCode;
    private String departureDateTime;
    private String flightNumber;
    private String classOfService;

    @Override
    public String toString() {
        return "FlightData{" +
                "originStationCode='" + originStationCode + '\'' +
                ", destinationStationCode='" + destinationStationCode + '\'' +
                ", departureDateTime='" + departureDateTime + '\'' +
                ", flightNumber='" + flightNumber + '\'' +
                ", classOfService='" + classOfService + '\'' +
                '}';
    }

    public FlightData(String originStationCode, String destinationStationCode, String departureDateTime, String flightNumber, String classOfService) {
        this.originStationCode = originStationCode;
        this.destinationStationCode = destinationStationCode;
        this.departureDateTime = departureDateTime;
        this.flightNumber = flightNumber;
        this.classOfService = classOfService;
    }

    public String getOriginStationCode() {
        return originStationCode;
    }

    public void setOriginStationCode(String originStationCode) {
        this.originStationCode = originStationCode;
    }

    public String getDestinationStationCode() {
        return destinationStationCode;
    }

    public void setDestinationStationCode(String destinationStationCode) {
        this.destinationStationCode = destinationStationCode;
    }

    public String getDepartureDateTime() {
        return departureDateTime;
    }

    public void setDepartureDateTime(String departureDateTime) {
        this.departureDateTime = departureDateTime;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getClassOfService() {
        return classOfService;
    }

    public void setClassOfService(String classOfService) {
        this.classOfService = classOfService;
    }
}
