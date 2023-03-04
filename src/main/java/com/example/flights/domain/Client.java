package com.example.flights.domain;

public class Client implements HasID<String>{
    private String username;
    private String name;

    public Client(String username, String name) {
        this.username = username;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String getId() {
        return username;
    }

    @Override
    public void setId(String s) {

    }
}
