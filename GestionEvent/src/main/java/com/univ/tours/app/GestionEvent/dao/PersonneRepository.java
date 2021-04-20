package com.univ.tours.app.GestionEvent.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.univ.tours.app.GestionEvent.entities.Personne;


public interface PersonneRepository extends JpaRepository<Personne, Long> {
	
	@Query("select CONCAT(prenom, ' ' ,nom) from Personne where id_perso=:x")
	public String nom(@Param("x") Long id_per);

}
