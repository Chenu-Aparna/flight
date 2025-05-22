/*package com.capgemini.FlightManagementSystem.Service;

import com.capgemini.FlightManagementSystem.Dto.FlightDTO;
import com.capgemini.FlightManagementSystem.Entity.Flight;
import com.capgemini.FlightManagementSystem.Exception.FlightNotFoundException;
import com.capgemini.FlightManagementSystem.Repository.IFlightRepository;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class FlightServiceImpl implements IFlightService {

    @Autowired
    private IFlightRepository flightRepository;

    @Autowired
    private Validator validator;  // For validating the DTO

    @Override
    public Flight addFlight(FlightDTO flightDTO) {
        // Validate the FlightDTO
        Set<ConstraintViolation<FlightDTO>> violations = validator.validate(flightDTO);
        if (!violations.isEmpty()) {
            throw new IllegalArgumentException("Validation failed for flight: " + violations);
        }

        Flight flight = new Flight();
        flight.setFlightNumber(flightDTO.getFlightNumber());
        flight.setFlightModel(flightDTO.getFlightModel());
        flight.setCarrierName(flightDTO.getCarrierName());
        flight.setSeatCapacity(flightDTO.getSeatCapacity());
        flight.setAvailableSeats(flightDTO.getAvailableSeats());
        flight.setFares(flightDTO.getFares());
        flight.setScheduleId(flightDTO.getScheduleId());

        return flightRepository.save(flight);
    }

    @Override
    public Flight modifyFlight(Long flightNumber, FlightDTO flightDTO) throws FlightNotFoundException {
        // Validate the FlightDTO
        Set<ConstraintViolation<FlightDTO>> violations = validator.validate(flightDTO);
        if (!violations.isEmpty()) {
            throw new IllegalArgumentException("Validation failed for flight: " + violations);
        }

        Flight flight = flightRepository.findById(flightNumber)
                .orElseThrow(() -> new FlightNotFoundException("Flight with number " + flightNumber + " not found."));

        flight.setFlightModel(flightDTO.getFlightModel());
        flight.setCarrierName(flightDTO.getCarrierName());
        flight.setSeatCapacity(flightDTO.getSeatCapacity());
        flight.setAvailableSeats(flightDTO.getAvailableSeats());
        flight.setFares(flightDTO.getFares());
        flight.setScheduleId(flightDTO.getScheduleId());

        return flightRepository.save(flight);
    }

    @Override
    public Optional<Flight> viewFlight(Long flightNumber) throws FlightNotFoundException {
        return Optional.ofNullable(flightRepository.findById(flightNumber)
                .orElseThrow(() -> new FlightNotFoundException("Flight with number " + flightNumber + " not found.")));
    }

    @Override
    public List<Flight> viewFlights() throws FlightNotFoundException {
        List<Flight> flights = flightRepository.findAll();
        if (flights.isEmpty()) {
            throw new FlightNotFoundException("No flights available.");
        }
        return flights;
    }

    @Override
    public boolean deleteFlight(Long flightNumber) throws FlightNotFoundException {
        Flight flight = flightRepository.findById(flightNumber)
                .orElseThrow(() -> new FlightNotFoundException("Flight with number " + flightNumber + " not found."));

        flightRepository.delete(flight);
        return true;
    }

    @Override
    public boolean validateFlight(FlightDTO flightDTO) {
        // Use Jakarta Validation API to validate the DTO
        Set<ConstraintViolation<FlightDTO>> violations = validator.validate(flightDTO);
        if (!violations.isEmpty()) {
            for (ConstraintViolation<FlightDTO> violation : violations) {
                System.out.println(violation.getMessage());  // You can log the violations instead
            }
            return false;
        }
        return true;
    }
}
*/

package com.capgemini.FlightManagementSystem.Service;

import com.capgemini.FlightManagementSystem.Dto.FlightDTO;
import com.capgemini.FlightManagementSystem.Entity.Flight;
import com.capgemini.FlightManagementSystem.Exception.FlightNotFoundException;
import com.capgemini.FlightManagementSystem.Repository.IFlightRepository;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class FlightServiceImpl implements IFlightService {

    private static final Logger logger = Logger.getLogger(FlightServiceImpl.class.getName());

    @Autowired
    private IFlightRepository flightRepository;

    @Autowired
    private Validator validator;  // For validating the DTO

    @Override
    public Flight addFlight(FlightDTO flightDTO) {
        logger.log(Level.INFO, "Adding a new flight: {0}", flightDTO);
        // Validate the FlightDTO
        Set<ConstraintViolation<FlightDTO>> violations = validator.validate(flightDTO);
        if (!violations.isEmpty()) {
            logger.log(Level.SEVERE, "Validation failed for flight: {0}", violations);
            throw new IllegalArgumentException("Validation failed for flight: " + violations);
        }

        Flight flight = new Flight();
        flight.setFlightNumber(flightDTO.getFlightNumber());
        flight.setFlightModel(flightDTO.getFlightModel());
        flight.setCarrierName(flightDTO.getCarrierName());
        flight.setSeatCapacity(flightDTO.getSeatCapacity());
        flight.setAvailableSeats(flightDTO.getAvailableSeats());
        flight.setFares(flightDTO.getFares());
        flight.setScheduleId(flightDTO.getScheduleId());

        Flight savedFlight = flightRepository.save(flight);
        logger.log(Level.INFO, "Flight added successfully: {0}", savedFlight);
        return savedFlight;
    }

    @Override
    public Flight modifyFlight(Long flightNumber, FlightDTO flightDTO) throws FlightNotFoundException {
        logger.log(Level.INFO, "Modifying flight with number: {0}", flightNumber);
        // Validate the FlightDTO
        Set<ConstraintViolation<FlightDTO>> violations = validator.validate(flightDTO);
        if (!violations.isEmpty()) {
            logger.log(Level.SEVERE, "Validation failed for flight: {0}", violations);
            throw new IllegalArgumentException("Validation failed for flight: " + violations);
        }

        Flight flight = flightRepository.findById(flightNumber)
                .orElseThrow(() -> new FlightNotFoundException("Flight with number " + flightNumber + " not found."));

        flight.setFlightModel(flightDTO.getFlightModel());
        flight.setCarrierName(flightDTO.getCarrierName());
        flight.setSeatCapacity(flightDTO.getSeatCapacity());
        flight.setAvailableSeats(flightDTO.getAvailableSeats());
        flight.setFares(flightDTO.getFares());
        flight.setScheduleId(flightDTO.getScheduleId());

        Flight modifiedFlight = flightRepository.save(flight);
        logger.log(Level.INFO, "Flight modified successfully: {0}", modifiedFlight);
        return modifiedFlight;
    }

    @Override
    public Optional<Flight> viewFlight(Long flightNumber) throws FlightNotFoundException {
        logger.log(Level.INFO, "Viewing flight with number: {0}", flightNumber);
        return Optional.ofNullable(flightRepository.findById(flightNumber)
                .orElseThrow(() -> {
                    logger.log(Level.SEVERE, "Flight with number {0} not found.", flightNumber);
                    return new FlightNotFoundException("Flight with number " + flightNumber + " not found.");
                }));
    }

    @Override
    public List<Flight> viewFlights() throws FlightNotFoundException {
        logger.log(Level.INFO, "Viewing all flights");
        List<Flight> flights = flightRepository.findAll();
        if (flights.isEmpty()) {
            logger.log(Level.WARNING, "No flights available.");
            throw new FlightNotFoundException("No flights available.");
        }
        logger.log(Level.INFO, "Flights found: {0}", flights);
        return flights;
    }

    @Override
    public boolean deleteFlight(Long flightNumber) throws FlightNotFoundException {
        logger.log(Level.INFO, "Deleting flight with number: {0}", flightNumber);
        Flight flight = flightRepository.findById(flightNumber)
                .orElseThrow(() -> {
                    logger.log(Level.SEVERE, "Flight with number {0} not found.", flightNumber);
                    return new FlightNotFoundException("Flight with number " + flightNumber + " not found.");
                });

        flightRepository.delete(flight);
        logger.log(Level.INFO, "Flight deleted successfully: {0}", flightNumber);
        return true;
    }

    @Override
    public boolean validateFlight(FlightDTO flightDTO) {
        logger.log(Level.INFO, "Validating flight: {0}", flightDTO);
        // Use Jakarta Validation API to validate the DTO
        Set<ConstraintViolation<FlightDTO>> violations = validator.validate(flightDTO);
        if (!violations.isEmpty()) {
            for (ConstraintViolation<FlightDTO> violation : violations) {
                logger.log(Level.WARNING, "Validation error: {0}", violation.getMessage());
            }
            return false;
        }
        logger.log(Level.INFO, "Flight is valid.");
        return true;
    }
}

