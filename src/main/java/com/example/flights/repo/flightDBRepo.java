package com.example.flights.repo;

import com.example.flights.domain.Client;
import com.example.flights.domain.Flight;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class flightDBRepo implements repository<Flight,Integer>{

    private String url;

    public flightDBRepo(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    private String username;
    private String password;
    @Override
    public void add(Flight entity) {

    }

    @Override
    public void remove(Flight entity) {

    }

    @Override
    public Flight find(Integer integer) {
        return null;
    }

    @Override
    public List<Flight> get_all() {
        List<Flight>all=new ArrayList<>();
        try(Connection connection= DriverManager.getConnection(url,username,password);
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM flights");
            ResultSet resultSet=ps.executeQuery()){
            while(resultSet.next()){
                int id = resultSet.getInt("flightid");
                long longid = id;
                String from = resultSet.getString("fromf");
                String to = resultSet.getString("tof");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                LocalDateTime departure = LocalDateTime.parse(resultSet.getString("departuretime"),formatter);
                LocalDateTime landing = LocalDateTime.parse(resultSet.getString("landingtime"),formatter);
                int seats = resultSet.getInt("seats");
                Flight flight= new Flight(longid,from,to,departure,landing,seats);
                all.add(flight);
            }
        }
        catch(SQLException se){
            se.printStackTrace();
        }
        return all;
    }

    @Override
    public void update(Flight entity) {

    }
}

