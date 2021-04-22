package com.univ.tours.app.GestionEvent.metier;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.univ.tours.app.GestionEvent.dao.EvenementRepository;
import com.univ.tours.app.GestionEvent.dao.PersonneRepository;
import com.univ.tours.app.GestionEvent.dao.ReservationRepository;
import com.univ.tours.app.GestionEvent.entities.Evenement;
import com.univ.tours.app.GestionEvent.entities.Personne;
import com.univ.tours.app.GestionEvent.entities.Reservation;

@Service
@Transactional
public class EvenementRepositoryImpl implements GestionEventMetier {

	@Autowired
	private EvenementRepository evenementRepository;
	@Autowired
	private PersonneRepository personneRepository;
	@Autowired
	private ReservationRepository reservationRepository;
	
	//CONSULTER
	@Override
	public Evenement consulterEvent(Long id_event) {
		Evenement e = evenementRepository.findById(id_event).orElse(null);		
		if(e == null ) throw new RuntimeException("Cet événement n'existe pas");
		return e;
	}
	
	
	@Override
	public Personne consulterPersonne(Long id_perso) {
		Personne p = personneRepository.findById(id_perso).orElse(null);
		if(p == null ) throw new RuntimeException("Cette personne n'existe pas");
		return p;
	}
	

	@Override
	public Reservation consulterReservation(Long id_res) {
		Reservation res = reservationRepository.findById(id_res).orElse(null);
		if(res == null ) throw new RuntimeException("Cette réservation n'existe pas");
		return res;
	}


	//RESERVATION
	@Override
	public void reserverEvent(String type_event ,Long id_perso, Long id_event) {
		Personne p = consulterPersonne(id_perso);
		Evenement e = consulterEvent(id_event);
		
		int verifQant = 0;
		verifQant = e.getQuantite();
		
		if(verifQant <= 0) throw new RuntimeException("Il n'y a plus de place pour cet événement");
		Reservation res = reservationRepository.save(new Reservation(type_event, new Date(), p, e));
		e.setQuantite(e.getQuantite()-1);
		
	}

	@Override
	public void annulerRes(Long id_res,Long id_perso, Long id_event) {
		//Personne p = consulterPersonne(id_perso);
		Evenement e = consulterEvent(id_event);
		
		Reservation res = consulterReservation(id_res);	
		reservationRepository.delete(res);
		
		e.setQuantite(e.getQuantite()+1);
		
	}

	
	//GESTION EVENEMENT
	@Override
	public void addEvent(String nom_event, String type_event, String description,String localisation, Date date_event, 
			double prix, int quantite) {
		
		Evenement e = evenementRepository.save(new Evenement(nom_event,type_event,description,localisation,new Date(),prix,quantite));
		
		
	}

	@Override
	public void updateEvent(Long id_event) {
		Evenement e = consulterEvent(id_event);
		
		
	}

	@Override
	public void deleteEvent(Long id_event) {
		
		evenementRepository.deleteEvent(id_event);
		
		
	}
	
	@Override
	public List<Evenement> listeEvent() {
		
		 //return evenementRepository.findAll();
		 return evenementRepository.listeEvent();
		
	}

	@Override
	public void nomPerso(Long id_perso) {
		
		System.out.println("Votre nom est :"+personneRepository.nom(id_perso));
		
	}


	@Override
	public Page<Evenement> rechercheType(String type, int page, int taille) {
		
		return evenementRepository.rechercheType(type, PageRequest.of(page, taille));
	}


	@Override
	public Page<Evenement> rechercheLoca(String loca, int page, int taille) {
	
		return evenementRepository.rechercheLoca(loca, PageRequest.of(page, taille));
	}


	@Override
	public Page<Evenement> rechercheDate(Date date1, Date date2, int page, int taille) {
		
		return null;
	}


	@Override
	public Evenement rechercherEvent(String nom_event) {
		return evenementRepository.searchEvent(nom_event);
		
	}


	

	

}
