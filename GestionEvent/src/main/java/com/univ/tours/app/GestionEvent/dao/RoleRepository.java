package com.univ.tours.app.GestionEvent.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.univ.tours.app.GestionEvent.entities.Personne;
import com.univ.tours.app.GestionEvent.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

	@Query("SELECT r from Role r where r.nom like ?1")
	public Role findByName(String nom);
	
}
