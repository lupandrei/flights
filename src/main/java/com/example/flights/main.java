package com.example.flights;

import com.example.flights.controller.ControllerLogin;
import com.example.flights.repo.BiletRepo;
import com.example.flights.repo.clientDBRepo;
import com.example.flights.repo.flightDBRepo;
import com.example.flights.service.Service;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class main extends Application {
    private Service srv;

    public static void main(String [] args){
        launch(args);
    }
    public void start(Stage primaryStage)throws IOException {
        String url = "jdbc:postgresql://localhost:5432/flights";
        clientDBRepo repoclient = new clientDBRepo(url,"postgres","postgres");
        flightDBRepo repoflight = new flightDBRepo(url,"postgres","postgres");
        BiletRepo biletRepo = new BiletRepo(url,"postgres","postgres");

        //Test tester = new Test();
        //tester.runTests();
        //UsersService userService = new UsersService(new inMemoryRepository<>(),new FriendshipRepo<>(),new UsewrValidator());
        srv= new Service(repoclient,repoflight, biletRepo);
        initView(primaryStage);
        primaryStage.show();

    }
    private void initView(Stage primaryStage) throws IOException{
        FXMLLoader loader =new FXMLLoader(getClass().getResource("login-view.fxml"));
        Scene scene = new Scene(loader.load(),600,400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Log in");

        ControllerLogin controllerlogin = loader.getController();
        controllerlogin.setData(srv);
    }
}