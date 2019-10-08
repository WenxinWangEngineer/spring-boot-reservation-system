package com.tiffanywang.happy.web.application;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.tiffanywang.happy.business.service.RoomReservationService;


@Controller
@RequestMapping(value = "reservations")
public class ReservationController{
	private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	
	@Autowired
	private RoomReservationService roomReservationService;
	@RequestMapping(method = RequestMethod.GET)
	//@ResponseBody
	public String getReservations(@RequestParam(value="date", required=false)String dateString, Model model) {
		Date date = null;
		if(null != dateString) {
			try {
				date = DATE_FORMAT.parse(dateString);
			}catch (ParseException pe) {
				date = new Date();
			}
		} else {
			date = new Date();
		}
		
		model.addAttribute("roomReservations",this.roomReservationService.getRoomReservationsForDate(date));
		return "reservations";
	}
}
	