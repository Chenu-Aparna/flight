/*package com.capgemini.FlightManagementSystem.Controller;

import com.capgemini.FlightManagementSystem.Dto.FlightDTO;
import com.capgemini.FlightManagementSystem.Entity.Flight;
import com.capgemini.FlightManagementSystem.Exception.FlightNotFoundException;
import com.capgemini.FlightManagementSystem.Service.IFlightService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/flights")
public class FlightController {

    @Autowired
    private IFlightService flightService;

    // Add a new flight
    @PostMapping("/add")
    public ResponseEntity<Object> addFlight(@Valid @RequestBody FlightDTO flightDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            // Collect all validation error messages
            StringBuilder errorMessages = new StringBuilder();
            bindingResult.getAllErrors().forEach(error -> {
                errorMessages.append(error.getDefaultMessage()).append("\n");
            });

            // Return validation errors as a list of messages
            return new ResponseEntity<>(errorMessages.toString(), HttpStatus.BAD_REQUEST);
        }
        Flight flight = flightService.addFlight(flightDTO);
        return new ResponseEntity<>(flight, HttpStatus.CREATED);
    }

    // Modify an existing flight
    @PutMapping("/modify/{flightNumber}")
    public ResponseEntity<Flight> modifyFlight(@PathVariable Long flightNumber, @RequestBody FlightDTO flightDTO) {
        try {
            Flight flight = flightService.modifyFlight(flightNumber, flightDTO);
            return new ResponseEntity<>(flight, HttpStatus.OK);
        } catch (FlightNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // View a specific flight by flight number
    @GetMapping("/{flightNumber}")
    public ResponseEntity<Flight> viewFlight(@PathVariable Long flightNumber) {
        try {
            Optional<Flight> flight = flightService.viewFlight(flightNumber);
            return flight.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                        .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (FlightNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // View all available flights
    @GetMapping("/all") // Changed from "/add" to "/all"
    public ResponseEntity<List<Flight>> viewFlights() {
        try {
            List<Flight> flights = flightService.viewFlights();
            if (flights.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content if no flights exist
            }
            return new ResponseEntity<>(flights, HttpStatus.OK);
        } catch (FlightNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete a flight by flight number
    @DeleteMapping("/delete/{flightNumber}")
    public ResponseEntity<String> deleteFlight(@PathVariable Long flightNumber) {
        try {
            boolean deleted = flightService.deleteFlight(flightNumber);
            if (deleted) {
                return new ResponseEntity<>("Flight deleted successfully.", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Flight not found.", HttpStatus.NOT_FOUND);
            }
        } catch (FlightNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    // Validate flight details
    @PostMapping("/validate")
    public ResponseEntity<String> validateFlight(@RequestBody FlightDTO flightDTO) {
        boolean isValid = flightService.validateFlight(flightDTO);
        return new ResponseEntity<>(isValid ? "Flight is valid." : "Flight is invalid.", HttpStatus.OK);
    }
}*/


package com.capgemini.FlightManagementSystem.Controller;

import com.capgemini.FlightManagementSystem.Dto.FlightDTO;
import com.capgemini.FlightManagementSystem.Entity.Flight;
import com.capgemini.FlightManagementSystem.Exception.FlightNotFoundException;
import com.capgemini.FlightManagementSystem.Service.IFlightService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/flights")
public class FlightController {

    private static final Logger logger = Logger.getLogger(FlightController.class.getName());

    @Autowired
    private IFlightService flightService;

    // Add a new flight
    @PostMapping("/add")
    public ResponseEntity<Object> addFlight(@Valid @RequestBody FlightDTO flightDTO, BindingResult bindingResult) {
        logger.log(Level.INFO, "Adding a new flight: {0}", flightDTO);
        if (bindingResult.hasErrors()) {
            // Collect all validation error messages
            StringBuilder errorMessages = new StringBuilder();
            bindingResult.getAllErrors().forEach(error -> {
                errorMessages.append(error.getDefaultMessage()).append("\n");
            });

            // Return validation errors as a list of messages
            logger.log(Level.SEVERE, "Validation errors: {0}", errorMessages.toString());
            return new ResponseEntity<>(errorMessages.toString(), HttpStatus.BAD_REQUEST);
        }
        Flight flight = flightService.addFlight(flightDTO);
        logger.log(Level.INFO, "Flight added successfully: {0}", flight);
        return new ResponseEntity<>(flight, HttpStatus.CREATED);
    }

    // Modify an existing flight
    @PutMapping("/modify/{flightNumber}")
    public ResponseEntity<Flight> modifyFlight(@PathVariable Long flightNumber, @RequestBody FlightDTO flightDTO) {
        logger.log(Level.INFO, "Modifying flight with number: {0}", flightNumber);
        try {
            Flight flight = flightService.modifyFlight(flightNumber, flightDTO);
            logger.log(Level.INFO, "Flight modified successfully: {0}", flight);
            return new ResponseEntity<>(flight, HttpStatus.OK);
        } catch (FlightNotFoundException e) {
            logger.log(Level.SEVERE, "Flight not found: {0}", e);
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // View a specific flight by flight number
    @GetMapping("/{flightNumber}")
    public ResponseEntity<Flight> viewFlight(@PathVariable Long flightNumber) {
        logger.log(Level.INFO, "Viewing flight with number: {0}", flightNumber);
        try {
            Optional<Flight> flight = flightService.viewFlight(flightNumber);
            return flight.map(value -> {
                logger.log(Level.INFO, "Flight found: {0}", value);
                return new ResponseEntity<>(value, HttpStatus.OK);
            }).orElseGet(() -> {
                logger.log(Level.WARNING, "Flight not found: {0}", flightNumber);
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            });
        } catch (FlightNotFoundException e) {
            logger.log(Level.SEVERE, "Flight not found: {0}", e);
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // View all available flights
    @GetMapping("/all")
    public ResponseEntity<List<Flight>> viewFlights() {
        logger.log(Level.INFO, "Viewing all flights");
        try {
            List<Flight> flights = flightService.viewFlights();
            if (flights.isEmpty()) {
                logger.log(Level.WARNING, "No flights available");
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            logger.log(Level.INFO, "Flights found: {0}", flights);
            return new ResponseEntity<>(flights, HttpStatus.OK);
        } catch (FlightNotFoundException e) {
            logger.log(Level.SEVERE, "Error viewing flights", e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete a flight by flight number
    @DeleteMapping("/delete/{flightNumber}")
    public ResponseEntity<String> deleteFlight(@PathVariable Long flightNumber) {
        logger.log(Level.INFO, "Deleting flight with number: {0}", flightNumber);
        try {
            boolean deleted = flightService.deleteFlight(flightNumber);
            if (deleted) {
                logger.log(Level.INFO, "Flight deleted successfully: {0}", flightNumber);
                return new ResponseEntity<>("Flight deleted successfully.", HttpStatus.OK);
            } else {
                logger.log(Level.WARNING, "Flight not found: {0}", flightNumber);
                return new ResponseEntity<>("Flight not found.", HttpStatus.NOT_FOUND);
            }
        } catch (FlightNotFoundException e) {
            logger.log(Level.SEVERE, "Error deleting flight: {0}", e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    // Validate flight details
    @PostMapping("/validate")
    public ResponseEntity<String> validateFlight(@RequestBody FlightDTO flightDTO) {
        logger.log(Level.INFO, "Validating flight: {0}", flightDTO);
        boolean isValid = flightService.validateFlight(flightDTO);
        logger.log(Level.INFO, "Flight validation result: {0}", isValid ? "valid" : "invalid");
        return new ResponseEntity<>(isValid ? "Flight is valid." : "Flight is invalid.", HttpStatus.OK);
    }
}

