package com.capgemini.FlightManagementSystem;
/*
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FlightManagementSystemApplicationTests {

	@Test
	void contextLoads() {
	}

}
*/



import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;

import com.capgemini.FlightManagementSystem.Dto.FlightDTO;
import com.capgemini.FlightManagementSystem.Dto.Schedule;
import com.capgemini.FlightManagementSystem.Entity.Flight;
import com.capgemini.FlightManagementSystem.Exception.FlightNotFoundException;
import com.capgemini.FlightManagementSystem.FeignClient.ScheduleFeignClient;
import com.capgemini.FlightManagementSystem.Repository.IFlightRepository;
import com.capgemini.FlightManagementSystem.Service.FlightServiceImpl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class FlightManagementSystemApplicationTests {

    @Mock
    private IFlightRepository flightRepository;

    @Mock
    private ScheduleFeignClient scheduleFeignClient;

    @Mock
    private Validator validator;

    @InjectMocks
    private FlightServiceImpl flightService;

    private FlightDTO flightDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Initialize FlightDTO with setters
        flightDTO = new FlightDTO();
        flightDTO.setCarrierName("Air India");
        flightDTO.setFlightModel("Boeing 777");
        flightDTO.setSeatCapacity(200);
        flightDTO.setAvailableSeats(100);
        flightDTO.setFares(5000.0);
        flightDTO.setScheduleId(1);
    }

    @Test
    void testAddFlight() {
        Flight flight = new Flight("Boeing 777", "Air India", 200, 100, 5000.0, 1);
        flight.setFlightNumber(12345L);

        Schedule mockSchedule = new Schedule();
        mockSchedule.setScheduleId(1);

        when(flightRepository.save(any(Flight.class))).thenReturn(flight);
   //     when(scheduleFeignClient.getScheduleById(flightDTO.getScheduleId())).thenReturn(mockSchedule);

        Flight addedFlight = flightService.addFlight(flightDTO);

        assertNotNull(addedFlight);
        assertEquals(flight.getFlightNumber(), addedFlight.getFlightNumber());
        assertEquals("Air India", addedFlight.getCarrierName());
    }

    @Test
    void testModifyFlight() throws FlightNotFoundException {
        Flight flight = new Flight("Boeing 777", "Air India", 200, 100, 5000.0, 1);
        flight.setFlightNumber(12345L);

        when(flightRepository.findById(12345L)).thenReturn(Optional.of(flight));
        when(flightRepository.save(any(Flight.class))).thenReturn(flight);

        Flight modifiedFlight = flightService.modifyFlight(12345L, flightDTO);

        assertNotNull(modifiedFlight);
        assertEquals(flightDTO.getFlightModel(), modifiedFlight.getFlightModel());
        verify(flightRepository, times(1)).findById(12345L);
        verify(flightRepository, times(1)).save(any(Flight.class));
    }

    @Test
    void testViewFlight() throws FlightNotFoundException {
        Flight flight = new Flight("Boeing 777", "Air India", 200, 100, 5000.0, 1);
        flight.setFlightNumber(12345L);

        when(flightRepository.findById(12345L)).thenReturn(Optional.of(flight));

        Optional<Flight> result = flightService.viewFlight(12345L);

        assertTrue(result.isPresent());
        assertEquals(flight.getFlightNumber(), result.get().getFlightNumber());
        verify(flightRepository, times(1)).findById(12345L);
    }

    @Test
    void testViewFlights() throws FlightNotFoundException {
        Flight flight = new Flight("Boeing 777", "Air India", 200, 100, 5000.0, 1);
        flight.setFlightNumber(12345L);

        when(flightRepository.findAll()).thenReturn(Collections.singletonList(flight));

        List<Flight> result = flightService.viewFlights();

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        verify(flightRepository, times(1)).findAll();
    }

    @Test
    void testDeleteFlight() throws FlightNotFoundException {
        Flight flight = new Flight("Boeing 777", "Air India", 200, 100, 5000.0, 1);
        flight.setFlightNumber(12345L);

        when(flightRepository.findById(12345L)).thenReturn(Optional.of(flight));

        boolean result = flightService.deleteFlight(12345L);

        assertTrue(result);
        verify(flightRepository, times(1)).findById(12345L);
        verify(flightRepository, times(1)).delete(flight);
    }

    @Test
    void testValidateFlight() {
        Set<ConstraintViolation<FlightDTO>> violations = validator.validate(flightDTO);

        assertTrue(violations.isEmpty());
    }
}

