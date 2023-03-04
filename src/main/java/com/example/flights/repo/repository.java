package com.example.flights.repo;

import java.util.List;

    public interface repository<E,ID> {
        void add(E entity);

        void remove(E entity);

        E find(ID id);

        List<E> get_all();

        void update(E entity);
    }