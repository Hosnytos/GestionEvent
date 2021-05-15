package com.univ.tours.app.GestionEvent.metier;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;

import com.univ.tours.app.GestionEvent.entities.Evenement;
import com.univ.tours.app.GestionEvent.entities.Personne;
import com.univ.tours.app.GestionEvent.entities.Reservation;
import com.univ.tours.app.GestionEvent.entities.Role;

public interface GestionEventMetier {

	public Evenement consulterEvent(Long id_event);
	public Personne consulterPersonne(Long id_perso);
	public Reservation consulterReservation(Long id_res);
	
	public Role save(Role role);
	public Personne saveRole(String email, String nom);

	public List<Evenement> listeEvent();

	public void reserverEvent(Long id_perso, Long id_event);
	public void annulerRes(Long id_res,Long id_perso, Long id_event);

	public void nomPerso(Long id_perso);

	public void addEvent(String nom_event, String type_event, String description,String localisation, LocalDate date_event, 
			double prix, double quantite);
	public void updateEvent(Long id_event);
	public void deleteEvent(Long id_event);

	//RECHERCHE
	public Page<Evenement> rechercheType(String type, int page, int taille);
	public Page<Evenement> rechercheLoca(String loca, int page, int taille);
	public Page<Evenement> rechercheDate(Date date1, Date date2, int page, int taille);
	public Evenement rechercherEvent(String nom_event);

}
