package com.capgemini.FlightManagementSystem.FeignClient;

 import java.util.List;

// Import Schedule entity
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.capgemini.FlightManagementSystem.Dto.Schedule;

// Feign client to connect to the Schedule service
@FeignClient(name = "schedule-service", url = "http://localhost:8081")  // Replace URL with your Schedule service URL
public interface ScheduleFeignClient {

    // Get schedule by scheduleId
    @GetMapping("/schedule/{scheduleId}")
	static
    Schedule getScheduleById(@PathVariable("scheduleId") int scheduleId) {
		// TODO Auto-generated method stub
		return null;
	}
    
    // Get all schedules (optional for future use)
    @GetMapping("/schedule")
    List<Schedule> getAllSchedules();
}
