package com.example.flights.controller;

import com.example.flights.domain.Client;
import com.example.flights.main;
import com.example.flights.service.Service;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerLogin {
    private Service srv;

    @FXML
    Button buttonLogin;

    @FXML
    TextField textfield;

    public void handlebuttonlogin(ActionEvent actionEvent) {
        if(!textfield.getText().isEmpty()){
            try {
                Stage stage = new Stage();
                FXMLLoader fxmlLoader = new FXMLLoader(main.class.getResource("view-zboruri.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 920, 460);
                stage.setTitle(textfield.getText());
                stage.setScene(scene);

                ControllerZboruri controllerZboruri = new ControllerZboruri();
                controllerZboruri = fxmlLoader.getController();
                Client c = srv.findClient(textfield.getText().toString());
                controllerZboruri.setData(srv, c);
                stage.show();
            }
            catch(IOException ex){
                ex.printStackTrace();;
            }
        }
    }

    public void setData(Service srv) {
        this.srv=srv;
    }
}
