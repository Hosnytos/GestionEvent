package com.univ.tours.app.GestionEvent.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Role implements Serializable{
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_role;
	
	private String nom;
	
	@ManyToMany(mappedBy = "roles")
	private List<Personne> personnes;
	
	public Role() {
		// TODO Auto-generated constructor stub
	}
	
	

	public Role(String nom) {
		super();
		this.nom = nom;
	}



	public int getId_role() {
		return id_role;
	}

	public void setId_role(int id_role) {
		this.id_role = id_role;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public List<Personne> getPersonnes() {
		return personnes;
	}

	public void setPersonnes(List<Personne> personnes) {
		this.personnes = personnes;
	}
	
	
	
}
