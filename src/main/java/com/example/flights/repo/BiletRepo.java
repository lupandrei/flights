package com.example.flights.repo;

import com.example.flights.domain.Bilet;
import com.example.flights.domain.Client;
import com.example.flights.domain.Flight;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class BiletRepo {
    private String url;
    private String username;
    private String password;

    public BiletRepo(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }
    public int getBileteRezervate(int flightId) {
        String sql = "SELECT count(*) FROM bilet WHERE flightId=?";
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, flightId);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                int zboruri = resultSet.getInt("count");
                return zboruri;
            }

        } catch (SQLException se) {
            se.printStackTrace();
        }
        return 0;
    }

    public void add(Bilet entity) {
        String sql = "INSERT INTO bilet(username,flightid,purchasetime) VALUES (?,?,?)";
        try(Connection connection = DriverManager.getConnection(url,username,password);
            PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1,entity.getUsername());
            ps.setInt(2,entity.getFlightId().intValue());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            ps.setString(3, entity.getPurchaseTime().format(formatter));
            ps.executeUpdate();
        }
        catch(SQLException se){
            se.getMessage();
        }
    }
}
