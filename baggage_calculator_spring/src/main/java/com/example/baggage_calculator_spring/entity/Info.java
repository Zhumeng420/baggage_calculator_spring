package com.example.baggage_calculator_spring.entity;


import lombok.Data;

import java.util.List;

@Data
public class Info {
    private String flightKind;
    private String babyTicket;
    private String statusKind;
    private String inAirfareLevel;
    private int economyAifare;

    private String outAirfareLevel;
    private String flightArea;

    private List<Luggage> luggageList;
    public String getFlightKind() {
        return flightKind;
    }

    public void setFlightKind(String flightKind) {
        this.flightKind = flightKind;
    }

    public String getBabyTicket() {
        return babyTicket;
    }

    public void setBabyTicket(String babyTicket) {
        this.babyTicket = babyTicket;
    }

    public String getStatusKind() {
        return statusKind;
    }

    public void setStatusKind(String statusKind) {
        this.statusKind = statusKind;
    }

    public String getInAirfareLevel() {
        return inAirfareLevel;
    }

    public void setInAirfareLevel(String inAirfareLevel) {
        this.inAirfareLevel = inAirfareLevel;
    }

    public int getEconomyAifare() {
        return economyAifare;
    }

    public void setEconomyAifare(int economyAifare) {
        this.economyAifare = economyAifare;
    }

    public List<Luggage> getLuggageList() {
        return luggageList;
    }

    public void setLuggageList(List<Luggage> luggageList) {
        this.luggageList = luggageList;
    }

    public String getOutAirfareLevel() {
        return outAirfareLevel;
    }

    public void setOutAirfareLevel(String outAirfareLevel) {
        this.outAirfareLevel = outAirfareLevel;
    }

    public String getFlightArea() {
        return flightArea;
    }

    public void setFlightArea(String flightArea) {
        this.flightArea = flightArea;
    }
}
