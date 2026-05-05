package com.anysound.service;
import com.anysound.model.TransportType;
import com.anysound.model.Trip;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@ApplicationScoped
public class TripServiceImpl implements TripService {
    private final List<Trip> trips = new CopyOnWriteArrayList<>();
    private final AtomicLong counter = new AtomicLong(0);

    @PostConstruct
    public void init() {
        save(new Trip(null, "MS-123", "Минск", "Москва", TransportType.TRAIN, new BigDecimal("150.00"), 50));
        save(new Trip(null, "BR-456", "Брест", "Варшава", TransportType.BUS, new BigDecimal("80.00"), 40));
    }

    @Override
    public List<Trip> findAll() { return trips; }

    @Override
    public List<Trip> search(String query) {
        if (query == null || query.isBlank()) return findAll();
        String q = query.toLowerCase();
        return trips.stream()
                .filter(t -> t.getDeparture().toLowerCase().contains(q) || t.getDestination().toLowerCase().contains(q))
                .collect(Collectors.toList());
    }

    @Override
    public void save(Trip trip) {
        if (trip.getId() == null) {
            trip.setId(Long.valueOf(counter.incrementAndGet()));
            trips.add(trip);
        } else {
            trips.replaceAll(t -> t.getId().equals(trip.getId()) ? trip : t);
        }
    }

    @Override
    public void delete(Long id) {
        trips.removeIf(t -> t.getId().equals(id));
    }
}