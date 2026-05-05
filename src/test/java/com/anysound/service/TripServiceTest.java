package com.anysound.service;

import com.anysound.model.TransportType;
import com.anysound.model.Trip;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class TripServiceTest {
    private TripServiceImpl tripService;

    @BeforeEach
    void setUp() {
        tripService = new TripServiceImpl();
        tripService.init(); // Внутри init() создаются 2 базовых рейса
    }

    @Test
    void testFindAll_returnsInitialTrips() {
        assertEquals(2, tripService.findAll().size(), "Должно быть 2 рейса по умолчанию");
    }

    @Test
    void testSave_addsNewTrip() {
        Trip trip = new Trip(null, "ZZ-9999", "Минск", "Гомель", TransportType.BUS, new BigDecimal("40.0"), 30);
        tripService.save(trip);
        assertEquals(3, tripService.findAll().size());
        assertNotNull(trip.getId(), "ID должен быть сгенерирован");
    }

    @Test
    void testSave_updatesExistingTrip() {
        Trip existing = tripService.findAll().get(0);
        existing.setDestination("Париж");
        tripService.save(existing);
        assertEquals("Париж", tripService.findAll().get(0).getDestination());
    }

    @Test
    void testDelete_removesTrip() {
        Long idToDelete = tripService.findAll().get(0).getId();
        tripService.delete(idToDelete);
        assertEquals(1, tripService.findAll().size());
    }

    @Test
    void testSearch_withEmptyQuery_returnsAll() {
        List<Trip> results = tripService.search("   ");
        assertEquals(2, results.size());
    }

    @Test
    void testSearch_withValidQuery_returnsMatch() {
        List<Trip> results = tripService.search("Минск");
        assertEquals(1, results.size());
        assertTrue(results.get(0).getDeparture().contains("Минск"));
    }
}