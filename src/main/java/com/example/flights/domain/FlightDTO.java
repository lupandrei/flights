package com.example.flights.domain;

public class FlightDTO {
    private int flightId;
    private String from;
    private String to;
    private int seats;
    private int disponibile;

    public FlightDTO(int flightId, String from, String to, int seats, int disponibile) {
        this.flightId = flightId;
        this.from = from;
        this.to = to;
        this.seats = seats;
        this.disponibile = disponibile;
    }

    public int getFlightId() {
        return flightId;
    }

    public void setFlightId(int flightId) {
        this.flightId = flightId;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public int getDisponibile() {
        return disponibile;
    }

    public void setDisponibile(int disponibile) {
        this.disponibile = disponibile;
    }
}
