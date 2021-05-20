
package com.univ.tours.app.GestionEvent.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.univ.tours.app.GestionEvent.entities.Evenement;
import com.univ.tours.app.GestionEvent.entities.Personne;
import com.univ.tours.app.GestionEvent.entities.Reservation;


public interface ReservationRepository extends JpaRepository<Reservation, Long> {


	@Query("select r from Reservation r where r.id_res=:x")
	public Page<Reservation> listResPage(@Param("x") Long id_res, Pageable pageable);
	
/*	@Query("select r from Reservation r inner join Personne p on p.id_perso = r.id_perso where r.id_perso=:x")
	public Page<Reservation> mesReservations(@Param("x") Long idPerso, Pageable pageable); */
	
}
