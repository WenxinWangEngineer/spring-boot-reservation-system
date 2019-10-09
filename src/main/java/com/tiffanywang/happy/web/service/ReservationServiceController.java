package com.tiffanywang.happy.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tiffanywang.happy.business.domain.RoomReservation;
import com.tiffanywang.happy.business.service.RoomReservationService;

/*
 * REST api also send the raw data in a json(default by Spring Boot) file to other services.
 * url: http://localhost:8080/api/reservations/2019-10-08
 * */
@RestController
@RequestMapping(value="/api")
public class ReservationServiceController {
	
	@Autowired 
	private RoomReservationService roomReservationService;
	/**
	 * 
	 * @param dateString	a string represents date, such as 2019-10-08
	 * @return				a json file of raw reservations data
	 */
	@RequestMapping(method=RequestMethod.GET, value="/reservations/{date}")

	public List<RoomReservation> getAllReservationsForDate(@PathVariable(value="date")String dateString) {
		return this.roomReservationService.getRoomReservationsForDate(dateString);
	}
}
