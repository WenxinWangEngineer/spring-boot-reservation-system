package com.tiffanywang.happy.business.service;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiffanywang.happy.business.domain.RoomReservation;
import com.tiffanywang.happy.data.entity.*;
import com.tiffanywang.happy.data.repository.*;

@Service
public class RoomReservationService {
	private RoomRepository roomRepository;
	private GuestRepository guestRepository;
	private ReservationRepository reservationRepository;

	private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	
	@Autowired
	public RoomReservationService(RoomRepository roomRepository, GuestRepository guestRepository,
			ReservationRepository reservationRepository) {
		this.roomRepository = roomRepository;
		this.guestRepository = guestRepository;
		this.reservationRepository = reservationRepository;
	}

	/**
	 * 
	 * @param	dateString	a string represents date such as 2019-10-08
	 * @return				an array list of roomReservation
	 */
	public List<RoomReservation> getRoomReservationsForDate(String dateString) {
		Date date = createDateFromDateString(dateString);
		Map<Long, RoomReservation> roomReservationMap = new HashMap<>();

		Iterable<Room> rooms = this.roomRepository.findAll();
		rooms.forEach(room -> {
			RoomReservation roomReservation = new RoomReservation();
			roomReservation.setRoomId(room.getId());
			roomReservation.setRoomName(room.getName());
			roomReservation.setRoomNumber(room.getNumber());
			roomReservationMap.put(room.getId(), roomReservation);
		});

		Iterable<Reservation> reservations = this.reservationRepository.findByDate(new java.sql.Date(date.getTime()));
		if (reservations != null) {

			reservations.forEach(reservation -> {
				Guest guest = this.guestRepository.findOne(reservation.getGuestId());
				if (guest != null) {
					RoomReservation roomReservation = roomReservationMap.get(reservation.getId());
					roomReservation.setGuestId(guest.getId());
					roomReservation.setFirstName(guest.getFirstName());
					roomReservation.setLastName(guest.getLastName());
					roomReservation.setDate(new java.sql.Date(date.getTime()));
				}
			});
		}

		List<RoomReservation> roomReservations = new ArrayList<>();
		for (Long roomId : roomReservationMap.keySet()) {
			roomReservations.add(roomReservationMap.get(roomId));
		}
		return roomReservations;
	}

	/**
	 * @param	dateString	a string represents date, such as 2019-10-08
	 * @return 				a Date format date
	 */
	private Date createDateFromDateString(String dateString) {
				
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
		
		return date;
	}
}
