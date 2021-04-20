package com.univ.tours.app.GestionEvent.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.univ.tours.app.GestionEvent.entities.Reservation;


public interface ReservationRepository extends JpaRepository<Reservation, Long> {

}
