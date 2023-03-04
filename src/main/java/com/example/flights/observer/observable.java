package com.example.flights.observer;

public interface observable {
    void addObserver(observer o);
    void notifyObservers();
}
