package com.tiffanywang.happy.web.application;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.tiffanywang.happy.business.service.RoomReservationService;

/*
 * regular Spring MVC Controller use the org.springframework.ui.Model for disply model data to the view.
 * */
@Controller
@RequestMapping(value = "reservations")
public class ReservationController{
	
	
	@Autowired
	private RoomReservationService roomReservationService;
	@RequestMapping(method = RequestMethod.GET)
	//@ResponseBody
	/**
	 * 
	 * @param dateString	a string represents date, such as "2019-10-08"
	 * @param model			an org.springframework.ui.Model class transfers reservation data
	 * @return 				a string of "reservations" telling Spring framework to display view template "src/main/resources/templates/reservations.hmtl".
	 */
	public String getReservations(@RequestParam(value="date", required=false)String dateString, Model model) {
		
		// Use model to transfer the model of data to the view.
		model.addAttribute("roomReservations",this.roomReservationService.getRoomReservationsForDate(dateString));
		return "reservations";	// Pass the string to the Spring Boot template for displaying the view 
	}
}
	