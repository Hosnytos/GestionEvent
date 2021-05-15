package com.univ.tours.app.GestionEvent.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.univ.tours.app.GestionEvent.entities.Reservation;


public interface ReservationRepository extends JpaRepository<Reservation, Long> {


}
