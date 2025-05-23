package com.capgemini.FlightManagementSystem.Service;


import com.capgemini.FlightManagementSystem.Dto.FlightDTO;
import com.capgemini.FlightManagementSystem.Entity.Flight;
import com.capgemini.FlightManagementSystem.Exception.FlightNotFoundException;

import java.util.List;
import java.util.Optional;

public interface IFlightService {

    // Add a new flight
    Flight addFlight(FlightDTO flightDTO);  // No exceptions

    // Modify an existing flight
    Flight modifyFlight(Long flightNumber, FlightDTO flightDTO) throws FlightNotFoundException;

    // View a specific flight by flight number
    Optional<Flight> viewFlight(Long flightNumber) throws FlightNotFoundException;

    // View all available flights
    List<Flight> viewFlights() throws FlightNotFoundException;

    // Delete a flight by flight number
    boolean deleteFlight(Long flightNumber) throws FlightNotFoundException;

    // Validate flight details
    boolean validateFlight(FlightDTO flightDTO);
}
