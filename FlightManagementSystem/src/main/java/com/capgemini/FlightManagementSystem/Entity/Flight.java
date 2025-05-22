package com.capgemini.FlightManagementSystem.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "flight_number", nullable = false, unique = true)
    private Long flightNumber;

    @Column(name = "flight_model", nullable = false)
    private String flightModel;

    @Column(name = "carrier_name", nullable = false)
    private String carrierName;

    @Column(name = "seat_capacity", nullable = false)
    private Integer seatCapacity;

    @Column(name = "available_seats", nullable = false)
    @NotNull(message = "Available seats are required")
    @Min(value = 1, message = "Available seats must be at least 1")
    private Integer availableSeats;

    @Column(name = "fares", nullable = false)
    @NotNull(message = "Fares are required")
    private Double fares;

    @Column(name = "schedule_id", nullable = false)
    @NotNull(message = "Schedule ID is required")
    private Integer scheduleId;

    // Default constructor
    public Flight() {
        // Empty constructor for JPA
    }

    // Constructor with parameters (no flightNumber here)
    public Flight(String flightModel, String carrierName, Integer seatCapacity,
                  Integer availableSeats, Double fares, Integer scheduleId) {
        this.flightModel = flightModel;
        this.carrierName = carrierName;
        this.seatCapacity = seatCapacity;
        this.availableSeats = availableSeats;
        this.fares = fares;
        this.scheduleId = scheduleId;
    }

    // Getters and Setters
    public Long getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(Long flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getFlightModel() {
        return flightModel;
    }

    public void setFlightModel(String flightModel) {
        this.flightModel = flightModel;
    }

    public String getCarrierName() {
        return carrierName;
    }

    public void setCarrierName(String carrierName) {
        this.carrierName = carrierName;
    }

    public Integer getSeatCapacity() {
        return seatCapacity;
    }

    public void setSeatCapacity(Integer seatCapacity) {
        this.seatCapacity = seatCapacity;
    }

    public Integer getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(Integer availableSeats) {
        this.availableSeats = availableSeats;
    }

    public Double getFares() {
        return fares;
    }

    public void setFares(Double fares) {
        this.fares = fares;
    }

    public Integer getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Integer scheduleId) {
        this.scheduleId = scheduleId;
    }
}
