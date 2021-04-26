package com.univ.tours.app.GestionEvent;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.univ.tours.app.GestionEvent.dao.EvenementRepository;
import com.univ.tours.app.GestionEvent.dao.PersonneRepository;
import com.univ.tours.app.GestionEvent.dao.ReservationRepository;
import com.univ.tours.app.GestionEvent.entities.Evenement;
import com.univ.tours.app.GestionEvent.entities.Personne;
import com.univ.tours.app.GestionEvent.entities.Reservation;
import com.univ.tours.app.GestionEvent.metier.GestionEventMetier;

@SpringBootApplication
@ComponentScan({"com.univ.tours.app.GestionEvent"})
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

		Evenement e1 = evenementRepository.save(new Evenement("Party in Peace", "Festival","F�te d�mentielle de 3 jours", "Miami",new Date(), 300, 700));
		Evenement e2 = evenementRepository.save(new Evenement("Astroworld", "Festival","Travis Scott x Sheck Wes - JackBoys Tour" ,"Houston",new Date(), 175, 3000));
		Evenement e3 = evenementRepository.save(new Evenement("Barcelona FC - Real Madrid CF", "Match","Finale de la Supercopa organis�e par les �mirats arabes unis", "Dubai",new Date(), 340, 60000));
		Evenement e4 = evenementRepository.save(new Evenement("Black Panther 2 : T'challa's Legacy", "Film","Suite du film Black Panther", "Los Angeles",new Date(), 50, 600));
		Evenement e5 = evenementRepository.save(new Evenement("Pirates of the Caribbean", "Film","JackyBoy is back !!!", "Los Angeles",new Date(), 35, 550));
		Evenement e6 = evenementRepository.save(new Evenement("John Cena - Randy Orton", "Catch","Wrestlemania XXIX", "Massachusetts",new Date(), 100, 1500));
		Evenement e7 = evenementRepository.save(new Evenement("El Perro - El Lobo", "Combat","Combat de prison ill�gal", "Guant�namo",new Date(), 750, 100));
		//Evenement e1 = evenementRepository.save(new Evenement("", "","", "",new Date(), 300, 700));
		Evenement e8 = evenementRepository.save(new Evenement("Lakers - Warriors", "Match","Finale NBA entre les champions en titre de Lebron James et les Warriors de Stephen Curry", "Staples Center",new Date(), 650, 5700));
		Evenement e9 = evenementRepository.save(new Evenement("Nadal - Djokovic", "Match","Finale du tournoi de Roland-Garros entre deux l�gendes", "Paris",new Date(), 1000, 12000));
		Evenement e10 = evenementRepository.save(new Evenement("Vikings : The movie", "Film","Retrouvez vos h�ros pr�f�r�s au Valhalla !", "Canada",new Date(), 15, 1300));
		Evenement e11 = evenementRepository.save(new Evenement("Je suis Alvin", "Manifestation","Rejoignez la marche pour lutter contre le massacre des �cureuils en Picardie !", "Paris",new Date(), 0, 200000));
		Evenement e12 = evenementRepository.save(new Evenement("Ezio�s Family Concert Suite", "Concert","Op�ra tir�e du jeu vid�o Assassin's Creed II et r�alis� par le WDR Funkhausorchester", "Cologne",new Date(), 120, 7000));
		
		Reservation res = reservationRepository.save(new Reservation("STANDARD",new Date(),p1,e1));
		Reservation res2 = reservationRepository.save(new Reservation("VIP",new Date(),p3,e1));
		Reservation res3 = reservationRepository.save(new Reservation("PREMIUM",new Date(),p2,e2));
		Reservation res4 = reservationRepository.save(new Reservation("STANDARD",new Date(),p1,e2));
		Reservation res5 = reservationRepository.save(new Reservation("STANDARD",new Date(),p3,e1));

		gestionEventMetier.annulerRes(res2.getId_res(), p3.getIdPerso(), e1.getId_event());
		gestionEventMetier.nomPerso(p1.getIdPerso());
	}

}
