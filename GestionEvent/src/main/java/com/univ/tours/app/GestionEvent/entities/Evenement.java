package com.univ.tours.app.GestionEvent.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;



@Entity
public class Evenement implements Serializable {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_event;

	private String nom_event;
	private String type_event;
	private String description;
	private LocalDate date_event;
	private String localisation;
	private double prix;
	private double quantite;



	@OneToMany(cascade = CascadeType.ALL , mappedBy = "evenement")
	Collection<Reservation> reservations;



	//CONSTRUCTORS
	public Evenement() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Evenement(String nom_event, String type_event, String description,String localisation, LocalDate date_event, 
			double prix, double quantite) {
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

	public LocalDate getDate_event() {
		return date_event;
	}

	public void setDate_event(LocalDate date_event) {
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

	public double getQuantite() {
		return quantite;
	}

	public void setQuantite(double quantite) {
		this.quantite = quantite;
	}

	public Collection<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(Collection<Reservation> reservations) {
		this.reservations = reservations;
	}







}
