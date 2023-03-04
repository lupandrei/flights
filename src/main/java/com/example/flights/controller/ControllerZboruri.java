package com.example.flights.controller;

import com.example.flights.domain.Bilet;
import com.example.flights.domain.Client;
import com.example.flights.domain.Flight;
import com.example.flights.domain.FlightDTO;
import com.example.flights.observer.observer;
import com.example.flights.service.Service;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ControllerZboruri implements observer {

    private Service srv;
    private ObservableList<FlightDTO> model = FXCollections.observableArrayList();

    @FXML
    TableView<FlightDTO> tableview;

    @FXML
    TableColumn<FlightDTO,Long> tableColumnFlightId;
    @FXML
    TableColumn<FlightDTO,String> tableColumnFrom;
    @FXML
    TableColumn<FlightDTO,String> tableColumnTo;
    @FXML
    TableColumn<FlightDTO,Integer> tableColumnSeats;
    @FXML
    TableColumn<FlightDTO,Integer> tableColumnDisponibile;

    @FXML
    ComboBox<String> comboBoxTo;

    @FXML
    ComboBox<String> comboBoxFrom;

    @FXML
    DatePicker datepicker;

    @FXML
    Button buttonNext;

    @FXML
    Button buttonPrevious;

    @FXML
    Text textPagCurenta;

    @FXML
    Text textTotalPagini;
    private Client c;

    private String from;
    private String to;
    private LocalDate ld;


    public void setData(Service srv, Client c){
        this.srv=srv;
        this.c=c;
        this.to="to";
        this.from="from";
        textPagCurenta.setText("1");
        textTotalPagini.setText(String.valueOf(Math.max((srv.getFlightsDTO(LocalDate.now(),"from","to").size())/5,1)));
        initCombo();
        srv.addObserver(this);
        initView(LocalDate.now(),"from","to");
    }

    private void initCombo() {
        List<Flight> flights=srv.getFlights(LocalDate.now(),"from","to");
        List<String> locatiito = flights.stream().map(x->x.getTo()).distinct().toList();
        List<String> locatiifrom = flights.stream().map(x->x.getFrom()).distinct().toList();
        for(String s:locatiito){
            comboBoxTo.getItems().add(s);
        }
        for(String s:locatiifrom){
            comboBoxFrom.getItems().add(s);
        }
    }

    private void initView(LocalDate date, String from, String to) {
        model.setAll(srv.getFlightsDTO(date,from,to).stream().skip((Long.parseLong(textPagCurenta.getText().toString())-1)*5).limit(5).toList());
        //textTotalPagini.setText(String.valueOf((srv.getFlightsDTO(date,from,to).size()+1)/5));
    }

    @FXML
    private void initialize(){
        tableColumnFlightId.setCellValueFactory(new PropertyValueFactory<>("flightId"));
        tableColumnFrom.setCellValueFactory(new PropertyValueFactory<>("from"));
        tableColumnTo.setCellValueFactory(new PropertyValueFactory<>("to"));
        tableColumnSeats.setCellValueFactory(new PropertyValueFactory<>("seats"));
        tableColumnDisponibile.setCellValueFactory(new PropertyValueFactory<>("disponibile"));
        tableview.setItems(model);
    }

    public void handleButtonFiltreaza() {
        if(!comboBoxFrom.getSelectionModel().getSelectedItem().isEmpty() &&
                !comboBoxTo.getSelectionModel().getSelectedItem().isEmpty() &&
                datepicker.getValue()!=null)
        {
            from =comboBoxFrom.getSelectionModel().getSelectedItem().toString();
            to =comboBoxTo.getSelectionModel().getSelectedItem().toString();
            ld = datepicker.getValue();
            initView(ld,from,to);
        }
        else{
            initView(LocalDate.now(),"from","to");
        }
    }

    public void handleButtonRezervare() {
        if(tableview.getSelectionModel().getSelectedItem()!=null){
            int flightId=tableview.getSelectionModel().getSelectedItem().getFlightId();
            String username = c.getId();
            LocalDateTime ld =LocalDateTime.now();
            Bilet bilet = new Bilet(username,(long)flightId,ld);
            srv.addRezervare(bilet);
        }
    }

    @Override
    public void update() {
        initView(ld,from,to);
    }

    public void handleButtonPrevious(ActionEvent actionEvent) {
        int curent =Integer.parseInt(textPagCurenta.getText().toString());
        int total = Integer.parseInt(textTotalPagini.getText().toString());
        if(curent>1)
        {
                curent--;
                textPagCurenta.setText(String.valueOf(curent));
                initView(ld,from,to);
        }
    }

    public void handleButtonNext(ActionEvent actionEvent) {
        int curent =Integer.parseInt(textPagCurenta.getText().toString());
        int total = Integer.parseInt(textTotalPagini.getText().toString());
        if(curent<total)
        {
            curent++;
            textPagCurenta.setText(String.valueOf(curent));
            initView(ld,from,to);
        }
    }
}
