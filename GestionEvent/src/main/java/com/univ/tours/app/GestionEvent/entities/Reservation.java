package com.univ.tours.app.GestionEvent.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Reservation implements Serializable {
	
	@Id @GeneratedValue
	private Long id_res;
	private String type_res;
	private Date date_res;
	
	@ManyToOne
	@JoinColumn(name = "ID_PERSO")
	private Personne personne;
	
	@ManyToOne
	@JoinColumn(name = "ID_EVENT")
	private Evenement evenement;

	
	
	//CONSTRUCTORS
	public Reservation() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Reservation(String type_res, Date date_res, Personne personne, Evenement evenement) {
		super();
		this.type_res = type_res;
		this.date_res = date_res;
		this.personne = personne;
		this.evenement = evenement;
	}

	
	//GETTERS & SETTERS
	public Long getId_res() {
		return id_res;
	}

	public void setId_res(Long id_res) {
		this.id_res = id_res;
	}

	public String getType_res() {
		return type_res;
	}

	public void setType_res(String type_res) {
		this.type_res = type_res;
	}

	public Date getDate_res() {
		return date_res;
	}

	public void setDate_res(Date date_res) {
		this.date_res = date_res;
	}

	public Personne getPersonne() {
		return personne;
	}

	public void setPersonne(Personne personne) {
		this.personne = personne;
	}

	public Evenement getEvenement() {
		return evenement;
	}

	public void setEvenement(Evenement evenement) {
		this.evenement = evenement;
	}
	
	
	
	
	
	
	

	
	
	//CONSTRUCTORS
	/*public Reservation() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Reservation(String type_res, Date date_res, Evenement evenement) {
		super();
		this.type_res = type_res;
		this.date_res = date_res;
		this.evenement = evenement;
	}

	
	//GETTERS & SETTERS
	public Long getId_res() {
		return id_res;
	}

	public void setId_res(Long id_res) {
		this.id_res = id_res;
	}

	public String getType_res() {
		return type_res;
	}

	public void setType_res(String type_res) {
		this.type_res = type_res;
	}

	public Date getDate_res() {
		return date_res;
	}

	public void setDate_res(Date date_res) {
		this.date_res = date_res;
	}

	public Evenement getEvenement() {
		return evenement;
	}

	public void setEvenement(Evenement evenement) {
		this.evenement = evenement;
	}*/
	
	
	
	
	
	
	
}
