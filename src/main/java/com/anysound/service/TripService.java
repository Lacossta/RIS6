package com.anysound.service;
import com.anysound.model.Trip;
import java.util.List;

public interface TripService {
    List<Trip> findAll();
    List<Trip> search(String query);
    void save(Trip trip);
    void delete(Long id);
}