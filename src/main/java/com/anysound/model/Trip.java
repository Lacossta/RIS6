package com.anysound.model;
import java.io.Serializable;
import java.math.BigDecimal;

public class Trip implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String flightNumber;
    private String departure;
    private String destination;
    private TransportType transportType;
    private BigDecimal price;
    private int seats;

    public Trip() {}
    public Trip(Long id, String flightNumber, String departure, String destination, TransportType transportType, BigDecimal price, int seats) {
        this.id = id; this.flightNumber = flightNumber; this.departure = departure;
        this.destination = destination; this.transportType = transportType; this.price = price; this.seats = seats;
    }

    public Long getId() { return id; } public void setId(Long id) { this.id = id; }
    public String getFlightNumber() { return flightNumber; } public void setFlightNumber(String flightNumber) { this.flightNumber = flightNumber; }
    public String getDeparture() { return departure; } public void setDeparture(String departure) { this.departure = departure; }
    public String getDestination() { return destination; } public void setDestination(String destination) { this.destination = destination; }
    public TransportType getTransportType() { return transportType; } public void setTransportType(TransportType transportType) { this.transportType = transportType; }
    public BigDecimal getPrice() { return price; } public void setPrice(BigDecimal price) { this.price = price; }
    public int getSeats() { return seats; } public void setSeats(int seats) { this.seats = seats; }
}