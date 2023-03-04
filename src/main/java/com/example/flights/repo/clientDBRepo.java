package com.example.flights.repo;

import com.example.flights.domain.Client;

import java.sql.*;
import java.util.List;

public class clientDBRepo implements repository<Client,String>{
    private String url;
    private String username;
    private String password;

    public clientDBRepo(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    @Override
    public void add(Client entity) {

    }

    @Override
    public void remove(Client entity) {

    }

    @Override
    public Client find(String s) {
        String sql ="SELECT * FROM client WHERE username=?";
        try(Connection connection = DriverManager.getConnection(url,username,password);
            PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1,s);
            ResultSet resultSet = ps.executeQuery();
            if(resultSet.next())
            {
                String nume = resultSet.getString("nume");
                return new Client(s,nume);
            }

        }
        catch(SQLException se){
            se.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Client> get_all() {
        return null;
    }

    @Override
    public void update(Client entity) {

    }
}
