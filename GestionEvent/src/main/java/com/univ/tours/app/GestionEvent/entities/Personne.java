package com.univ.tours.app.GestionEvent.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Personne implements Serializable {

	@Id @GeneratedValue
	private Long idPerso;
	private int age;
	private String nom, prenom, email;


	@OneToMany(mappedBy="personne", fetch = FetchType.LAZY)
	private Collection<Reservation> reservations;




	//CONSTRUCTORS
	public Personne() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Personne(String nom, String prenom, String email, int age) {
		super();
		this.age = age;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
	}

	
	//GETTERS & SETTERS
	public Long getIdPerso() {
		return idPerso;
	}

	public void setIdPerso(Long idPerso) {
		this.idPerso = idPerso;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Collection<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(Collection<Reservation> reservations) {
		this.reservations = reservations;
	}
	
	
	
	
	


	//CONSTRUCTORS
	/*public Personne() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Personne(String nom, String prenom, String email,int age) {
		super();
		this.age = age;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
	}



	//GETTERS & SETTERS

	public Long getIdPerso() {
		return idPerso;
	}



	public void setIdPerso(Long idPerso) {
		this.idPerso = idPerso;
	}



	public int getAge() {
		return age;
	}



	public void setAge(int age) {
		this.age = age;
	}



	public String getNom() {
		return nom;
	}



	public void setNom(String nom) {
		this.nom = nom;
	}



	public String getPrenom() {
		return prenom;
	}



	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}




	public Collection<Evenement> getEvenements() {
		return evenements;
	}



	public void setEvenements(Collection<Evenement> evenements) {
		this.evenements = evenements;
	}
	 */




















}
