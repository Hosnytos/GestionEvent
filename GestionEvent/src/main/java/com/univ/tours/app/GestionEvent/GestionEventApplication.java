package com.univ.tours.app.GestionEvent;

import java.time.LocalDate;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.univ.tours.app.GestionEvent.dao.EvenementRepository;
import com.univ.tours.app.GestionEvent.dao.PersonneRepository;
import com.univ.tours.app.GestionEvent.dao.ReservationRepository;
import com.univ.tours.app.GestionEvent.dao.RoleRepository;
import com.univ.tours.app.GestionEvent.entities.Evenement;
import com.univ.tours.app.GestionEvent.entities.Personne;
import com.univ.tours.app.GestionEvent.entities.Reservation;
import com.univ.tours.app.GestionEvent.entities.Role;
import com.univ.tours.app.GestionEvent.metier.GestionEventMetier;
import com.univ.tours.app.GestionEvent.securingweb.CustomUserDetails;

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
	@Autowired
	private RoleRepository roleRepository;
	
	private CustomUserDetails customUser;


	public static void main(String[] args) {
		SpringApplication.run(GestionEventApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		Role role1 = roleRepository.save(new Role("USER"));
		Role role2 = roleRepository.save(new Role("ADMIN"));
		
		Personne p1 = personneRepository.save(new Personne("Jokic","Paulo","pJokic@cia.com",47, encoder.encode("mdp")));
		//p1.getRoles().add(role1);
		//p1.getRoles().add(role2);
		Personne p2 = personneRepository.save(new Personne("Cruz","Jessica","jCruz@gign.fr",28, encoder.encode("mdp")));
		//p2.getRoles().add(role2);
		Personne p3 = personneRepository.save(new Personne("Friedriech","Hansel","fHansel@reich.deu",30, encoder.encode("mdp")));
		//p3.getRoles().add(role1);
		gestionEventMetier.saveRole("jCruz@gign.fr", "ADMIN");
		gestionEventMetier.saveRole("jCruz@gign.fr", "USER");
		gestionEventMetier.saveRole("pJokic@cia.com", "USER");
		gestionEventMetier.saveRole("fHansel@reich.deu", "USER");

		Evenement e1 = evenementRepository.save(new Evenement("Party in Peace", "Festival","Fête démentielle de 3 jours", "Miami",LocalDate.of(2019, 5, 11), 300, 700));
		Evenement e2 = evenementRepository.save(new Evenement("Astroworld", "Festival","Travis Scott x Sheck Wes - JackBoys Tour" ,"Houston",LocalDate.of(2018, 7, 18), 175, 3000));
		Evenement e3 = evenementRepository.save(new Evenement("Barcelona FC - Real Madrid CF", "Match","Finale de la Supercopa organisée par les Émirats arabes unis", "Dubai",LocalDate.of(2021, 10, 21), 340, 60000));
		Evenement e4 = evenementRepository.save(new Evenement("Black Panther 2 : T'challa's Legacy", "Film","Suite du film Black Panther", "Los Angeles",LocalDate.of(2022, 2, 9), 50, 600));
		Evenement e5 = evenementRepository.save(new Evenement("Pirates of the Caribbean", "Film","JackyBoy is back !!!", "Los Angeles",LocalDate.of(2024, 5, 16), 35, 550));
		Evenement e6 = evenementRepository.save(new Evenement("John Cena - Randy Orton", "Catch","Wrestlemania XXIX", "Massachusetts",LocalDate.of(2021, 12, 31), 100, 1500));
		Evenement e7 = evenementRepository.save(new Evenement("El Perro - El Lobo", "Combat","Combat de prison illégal", "Guantánamo",LocalDate.of(2016, 8, 10), 750, 100));
		//Evenement e1 = evenementRepository.save(new Evenement("", "","", "",new Date(), 300, 700));
		Evenement e8 = evenementRepository.save(new Evenement("Lakers - Warriors", "Match","Finale NBA entre les champions en titre de Lebron James et les Warriors de Stephen Curry", "Staples Center",LocalDate.of(2021, 6, 15), 650, 5700));
		Evenement e9 = evenementRepository.save(new Evenement("Nadal - Djokovic", "Match","Finale du tournoi de Roland-Garros entre deux légendes", "Paris",LocalDate.of(2019, 4, 27), 1000, 12000));
		Evenement e10 = evenementRepository.save(new Evenement("Vikings : The movie", "Film","Retrouvez vos héros préférés au Valhalla !", "Canada",LocalDate.of(2022, 10, 22), 15, 1300));
		Evenement e11 = evenementRepository.save(new Evenement("Je suis Alvin", "Manifestation","Rejoignez la marche pour lutter contre le massacre des écureuils en Picardie !", "Paris",LocalDate.of(2021, 11, 1), 0, 200000));
		Evenement e12 = evenementRepository.save(new Evenement("Ezio’s Family Concert Suite", "Concert","Opéra tirée du jeu vidéo Assassin's Creed II et réalisé par le WDR Funkhausorchester", "Cologne",LocalDate.of(2018, 5, 11), 120, 7000));
	
		Reservation res = reservationRepository.save(new Reservation(p1,e1));
		Reservation res2 = reservationRepository.save(new Reservation(p3,e1));
		Reservation res3 = reservationRepository.save(new Reservation(p2,e2));
		Reservation res4 = reservationRepository.save(new Reservation(p1,e2));
		Reservation res5 = reservationRepository.save(new Reservation(p3,e1));

		gestionEventMetier.annulerRes(res2.getId_res(), p3.getIdPerso(), e1.getId_event());
		gestionEventMetier.nomPerso(p1.getIdPerso()); 
		//System.out.println(customUser.getUsername());
	}

}
