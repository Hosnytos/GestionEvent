package com.univ.tours.app.GestionEvent;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.univ.tours.app.GestionEvent.dao.EvenementRepository;
import com.univ.tours.app.GestionEvent.dao.PersonneRepository;
import com.univ.tours.app.GestionEvent.dao.ReservationRepository;
import com.univ.tours.app.GestionEvent.entities.Evenement;
import com.univ.tours.app.GestionEvent.entities.Personne;
import com.univ.tours.app.GestionEvent.entities.Reservation;
import com.univ.tours.app.GestionEvent.metier.GestionEventMetier;

@SpringBootApplication
public class GestionEventApplication implements CommandLineRunner {

	@Autowired
	private PersonneRepository personneRepository;
	@Autowired
	private EvenementRepository evenementRepository;
	@Autowired
	private ReservationRepository reservationRepository;
	@Autowired
	private GestionEventMetier gestionEventMetier;
	
	
	public static void main(String[] args) {
		SpringApplication.run(GestionEventApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Personne p1 = personneRepository.save(new Personne("Jokic","Paulo","pJokic@cia.com",47));
		Personne p2 = personneRepository.save(new Personne("Cruz","Jessica","jCruz@gign.fr",28));
		Personne p3 = personneRepository.save(new Personne("Friedriech","Hansel","fHansel@reich.deu",30));
		
		Evenement e1 = evenementRepository.save(new Evenement("Party in Peace", "Festival","Fête démentielle de 3 jours.", "Miami",new Date(), 300, 700));
		Evenement e2 = evenementRepository.save(new Evenement("Astroworld", "Festival","Travis Scott x Sheck Wes - JackBoys Tour" ,"Houston",new Date(), 175, 3000));
		
		Reservation res = reservationRepository.save(new Reservation("STANDARD",new Date(),p1,e1));
		Reservation res2 = reservationRepository.save(new Reservation("VIP",new Date(),p3,e1));
		Reservation res3 = reservationRepository.save(new Reservation("PREMIUM",new Date(),p2,e2));
		Reservation res4 = reservationRepository.save(new Reservation("STANDARD",new Date(),p1,e2));
		Reservation res5 = reservationRepository.save(new Reservation("STANDARD",new Date(),p3,e1));
		
		gestionEventMetier.annulerRes(res2.getId_res(), p3.getIdPerso(), e1.getId_event());
		gestionEventMetier.nomPerso(p1.getIdPerso());
	}

}
