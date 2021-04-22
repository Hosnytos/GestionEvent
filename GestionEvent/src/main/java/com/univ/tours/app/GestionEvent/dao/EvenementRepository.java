package com.univ.tours.app.GestionEvent.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.univ.tours.app.GestionEvent.entities.Evenement;


public interface EvenementRepository extends JpaRepository<Evenement, Long> {
	
	
	@Query("select e from Evenement e where e.id_event=:x")
	public Page<Evenement> listEvenPage(@Param("x") Long id_event, Pageable pageable);
	
	@Query("select e from Evenement e where e.type_event=:x")
	public Page<Evenement> rechercheType(@Param("x") String type, Pageable pageable);
	
	@Query("select e from Evenement e where e.localisation=:x")
	public Page<Evenement> rechercheLoca(@Param("x") String loca, Pageable pageable);
	
	@Query("select e from Evenement e where e.date_event between :x and :y")
	public Page<Evenement> rechercheDate(@Param("x") Date date1,@Param("y") Date date2, Pageable pageable);
	
	@Query("delete from Evenement e where e.id_event=:x")
	public void deleteEvent(@Param("x") Long id_event);
	
	@Query("select e from Evenement e")
	public List<Evenement> listeEvent();
	
	
	@Query("select e from Evenement e where e.nom_event=:x")
	public Evenement searchEvent(@Param("x") String nom_event);
	
	

}
