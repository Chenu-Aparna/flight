package com.capgemini.FlightManagementSystem.Repository;


import com.capgemini.FlightManagementSystem.Entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFlightRepository extends JpaRepository<Flight, Long> {
    // Custom query methods can be added here if necessary

    // Example: Find flights by carrier name
    // List<Flight> findByCarrierName(String carrierName);
}

