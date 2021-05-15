package com.univ.tours.app.GestionEvent.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
public class Personne implements Serializable {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPerso;
	private int age;
	private String nom, prenom, email, mdp;
	
	//private int role;
	/*@ManyToMany(cascade = CascadeType.MERGE)
	@JoinTable(name = "user_role", joinColumns = {@JoinColumn(name = "USER_ID", referencedColumnName = "idPerso")},
	inverseJoinColumns = {@JoinColumn(name = "ROLE_ID", referencedColumnName = "id_role")})
	private List<Role> roles;*/
	@ManyToMany
	@JoinTable(name="USERS_ROLES")
	private Collection<Role> roles;
	

	@OneToMany(cascade = CascadeType.ALL, mappedBy="personne", fetch = FetchType.LAZY)
	private Collection<Reservation> reservations;

	
	//CONSTRUCTORS
	public Personne() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Personne(String nom, String prenom, String email, int age, String mdp) {
		super();
		this.age = age;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.mdp = mdp;
		//this.roles.add(new Role("USER"));
		//role = 0;
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
	
	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	public Collection<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
	
/*
	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}
*/
}
