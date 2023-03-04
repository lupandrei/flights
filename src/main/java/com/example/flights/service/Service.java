package com.example.flights.service;

import com.example.flights.domain.Bilet;
import com.example.flights.domain.Client;
import com.example.flights.domain.Flight;
import com.example.flights.domain.FlightDTO;
import com.example.flights.observer.observable;
import com.example.flights.observer.observer;
import com.example.flights.repo.BiletRepo;
import com.example.flights.repo.repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Service implements observable {
    private repository<Client,String> repoclient;
    private repository<Flight,Integer> repoflight;

    private List<observer>obs;

    private BiletRepo biletRepo;

    public Service(repository<Client, String> repoclient, repository<Flight, Integer> repoflight, BiletRepo biletRepo) {
        this.repoclient = repoclient;
        this.repoflight = repoflight;
        this.biletRepo = biletRepo;
        this.obs=new ArrayList<>();
    }

    public List<FlightDTO>getFlightsDTO(LocalDate now, String from, String to)
    {
        List<FlightDTO> flightDTOS =new ArrayList<>();
        if(!from.equals("from")){
            List<Flight> flights = repoflight.get_all().stream().filter(x->x.getDepartureTime().toLocalDate().isEqual(now) && x.getFrom().equals(from)&& x.getTo().equals(to)).toList();
            for(Flight f:flights){
                int bilete = biletRepo.getBileteRezervate(f.getId().intValue());
                FlightDTO flightDTO = new FlightDTO(f.getId().intValue(),f.getFrom(),f.getTo(),f.getSeats(),f.getSeats()-bilete);
                flightDTOS.add(flightDTO);
            }
            return flightDTOS;
        }
        else{
            List<Flight> flights = repoflight.get_all();
            for(Flight f:flights){
                int bilete = biletRepo.getBileteRezervate(f.getId().intValue());
                FlightDTO flightDTO = new FlightDTO(f.getId().intValue(),f.getFrom(),f.getTo(),f.getSeats(),f.getSeats()-bilete);
                flightDTOS.add(flightDTO);
            }
            return flightDTOS;
        }
    }


    public List<Flight> getFlights(LocalDate now, String from, String to) {
        if(!from.equals("from")){
            return repoflight.get_all().stream().filter(x->x.getDepartureTime().toLocalDate().isEqual(now) && x.getFrom().equals(from)&& x.getTo().equals(to)).toList();
        }
        return repoflight.get_all();
    }
    public Client findClient(String username){
        return repoclient.find(username);
    }

    public void addRezervare(Bilet bilet) {
        biletRepo.add(bilet);
        notifyObservers();
    }

    @Override
    public void addObserver(observer o) {
        obs.add(o);
    }

    @Override
    public void notifyObservers() {
        for(observer o:obs){
            o.update();
        }
    }
}
