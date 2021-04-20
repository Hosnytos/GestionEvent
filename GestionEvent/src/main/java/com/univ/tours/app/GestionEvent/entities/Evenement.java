package com.univ.tours.app.GestionEvent.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Evenement implements Serializable {

	@Id @GeneratedValue
	private Long id_event;

	private String nom_event;
	private String type_event;
	private String description;
	private Date date_event;
	private String localisation;
	private double prix;
	private int quantite;



	@OneToMany(mappedBy = "evenement")
	Collection<Reservation> reservations;



	//CONSTRUCTORS
	public Evenement() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Evenement(String nom_event, String type_event, String description,String localisation, Date date_event, 
			double prix, int quantite) {
		super();
		this.nom_event = nom_event;
		this.type_event = type_event;
		this.description = description;
		this.date_event = date_event;
		this.localisation = localisation;
		this.prix = prix;
		this.quantite = quantite;
	}


	//GETTERS & SETTERS
	public Long getId_event() {
		return id_event;
	}

	public void setId_event(Long id_event) {
		this.id_event = id_event;
	}

	public String getNom_event() {
		return nom_event;
	}

	public void setNom_event(String nom_event) {
		this.nom_event = nom_event;
	}

	public String getType_event() {
		return type_event;
	}

	public void setType_event(String type_event) {
		this.type_event = type_event;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDate_event() {
		return date_event;
	}

	public void setDate_event(Date date_event) {
		this.date_event = date_event;
	}

	public String getLocalisation() {
		return localisation;
	}

	public void setLocalisation(String localisation) {
		this.localisation = localisation;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public Collection<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(Collection<Reservation> reservations) {
		this.reservations = reservations;
	}







}
