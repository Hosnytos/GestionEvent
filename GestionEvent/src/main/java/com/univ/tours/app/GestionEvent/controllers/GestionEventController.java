package com.univ.tours.app.GestionEvent.controllers;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

import com.univ.tours.app.GestionEvent.dao.ContactRepository;
import com.univ.tours.app.GestionEvent.dao.EvenementRepository;
import com.univ.tours.app.GestionEvent.dao.PersonneRepository;
import com.univ.tours.app.GestionEvent.dao.ReservationRepository;
import com.univ.tours.app.GestionEvent.entities.Contact;
import com.univ.tours.app.GestionEvent.entities.Evenement;
import com.univ.tours.app.GestionEvent.entities.Personne;
import com.univ.tours.app.GestionEvent.entities.Reservation;
import com.univ.tours.app.GestionEvent.metier.GestionEventMetier;
import com.univ.tours.app.GestionEvent.securingweb.CustomUserDetails;
import com.univ.tours.app.GestionEvent.securingweb.CustomUserDetailsService;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

@Controller
@ControllerAdvice
public class GestionEventController {

	@Autowired
	private GestionEventMetier gestionEventMetier;
	@Autowired
	private EvenementRepository evenementRepo;
	@Autowired
	private PersonneRepository personneRepo;
	@Autowired
	private ReservationRepository reservationRepo;
	@Autowired
	private ContactRepository contactRepository;

	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	private CustomUserDetails customUser;

	private boolean test = false;

	static List<String> listType = null;

	@RequestMapping("/index")
	public String listeEvent(Model model, @RequestParam(name="page", defaultValue = "0") int p, @RequestParam(name="size", defaultValue = "4") int s){
		//List<Evenement> evenement = gestionEventMetier.listeEvent();
		//model.addAttribute("evenement", evenement);
		//model.addAttribute("listeEvent", gestionEventMetier.listeEvent());
		Page<Evenement> pageEvent = evenementRepo.findAll(PageRequest.of(p, s));
		model.addAttribute("listeEvent", pageEvent.getContent());
		int [] pages = new int[pageEvent.getTotalPages()];
		model.addAttribute("pages", pages);
		model.addAttribute("size", s);
		model.addAttribute("pageCourante", p);

		return "index";

	}


	@GetMapping("/register")
	public String showRegistrationForm(WebRequest request, Model model) {
		Personne personne = new Personne();
		model.addAttribute("personne", personne);
		return "register";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(@ModelAttribute("personne") Personne personne) {
		String encodedPas = encoder.encode(personne.getMdp());
		personne.setMdp(encodedPas);
		personneRepo.save(personne);
		return "redirect:/index";
	}

	@RequestMapping("/consulter")
	public String consulter(Model model,Long id_event){

		try {
			Evenement e = gestionEventMetier.consulterEvent(id_event);
			model.addAttribute("evenement",e);

		} catch (Exception e) {
			model.addAttribute("excection",e);
		}

		return "evenement_show";

	}

	@RequestMapping("/rechercheType")
	public String rechercheType(Model model, @RequestParam(name="page", defaultValue = "0") int p, @RequestParam(name="size", defaultValue = "15") int s,String type_event){

		try {

			//Liste des types triée
			listType = evenementRepo.listType();
			listType.sort(null);
			model.addAttribute("listeType",listType);

			//Vue : liste des événements en fonction du type selectionné
			Page<Evenement> pageEvent = evenementRepo.rechercheType(type_event,PageRequest.of(p, s));
			model.addAttribute("listeEvent", pageEvent.getContent());
			int [] pages = new int[pageEvent.getTotalPages()];
			model.addAttribute("pages", pages);
			model.addAttribute("size", s);
			model.addAttribute("pageCourante", p);

		} catch (Exception e) {
			model.addAttribute("excection",e);
		}

		return "rechercheType";
	}


	@RequestMapping("/rechercheLoca")
	public String rechercheLoca(Model model, @RequestParam(name="page", defaultValue = "0") int p, @RequestParam(name="size", defaultValue = "15") int s,String loca_event){

		try {

			//Vue : liste des événements en fonction de la localisation selectionné
			Page<Evenement> pageEvent = evenementRepo.rechercheLoca(loca_event,PageRequest.of(p, s));
			model.addAttribute("listeEvent", pageEvent.getContent());
			int [] pages = new int[pageEvent.getTotalPages()];
			model.addAttribute("pages", pages);
			model.addAttribute("size", s);
			model.addAttribute("pageCourante", p);

		} catch (Exception e) {
			model.addAttribute("excection",e);
		}

		return "rechercheLoca";
	}


	@RequestMapping("/rechercheDate")
	public String rechercheDate(Model model, @RequestParam(name="page", defaultValue = "0") int p, @RequestParam(name="size", defaultValue = "15") int s,String date_event1,String date_event2){

		try {	
			//Conversion en date 
			Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(date_event1); 
			Date date2=new SimpleDateFormat("yyyy-MM-dd").parse(date_event2);

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate dateTime = LocalDate.parse(date_event1, formatter);
			LocalDate dateTime2 = LocalDate.parse(date_event2, formatter);

			//Vue : liste des événements en fonction de la periode selectionné
			Page<Evenement> pageEvent = evenementRepo.rechercheDate(dateTime, dateTime2,PageRequest.of(p, s));
			model.addAttribute("listeEvent", pageEvent.getContent());
			int [] pages = new int[pageEvent.getTotalPages()];
			model.addAttribute("pages", pages);
			model.addAttribute("size", s);
			model.addAttribute("pageCourante", p);

		} catch (Exception e) {
			model.addAttribute("excection",e);
		}

		return "rechercheDate";
	}


	@RequestMapping("/afficherContact")
	public String afficherContact(Model model, @RequestParam(name="page", defaultValue = "0") int p, @RequestParam(name="size", defaultValue = "5") int s){

		try {
			//List<Contact> e = contactRepository.findAll();
			//model.addAttribute("listContact",e);

			Page<Contact> pageEvent = contactRepository.findAll(PageRequest.of(p, s));
			model.addAttribute("listContact", pageEvent.getContent());
			int [] pages = new int[pageEvent.getTotalPages()];
			model.addAttribute("pages", pages);
			model.addAttribute("size", s);
			model.addAttribute("pageCourante", p);

		} catch (Exception e) {
			model.addAttribute("excection",e);
		}

		return "contact_liste";

	}

	@GetMapping("/addContact")
	public String addContact(Model model){
		Contact contact = new Contact();
		model.addAttribute("contact", contact);
		model.addAttribute("test", test);
		test = false;
		return "contact";
	}

	@PostMapping("/saveContact")
	public String saveContact(@ModelAttribute("contact") Contact contact, Model model){
		contactRepository.save(contact);
		test = true;
		model.addAttribute("test", test);
		return "redirect:/addContact";

	}

	@GetMapping("/addEvent")
	public String addEvent(Model model){
		Evenement evenement = new Evenement();
		model.addAttribute("evenement", evenement);
		return "evenement_add";
	}

	@PostMapping("/saveEvent")
	public String saveEvent(@ModelAttribute("evenement") Evenement evenement){
		evenementRepo.save(evenement);
		//gestionEventMetier.addEvent(evenement);
		//model.addAttribute("evenement", evenement);
		return "redirect:/index";

	}
	
	@GetMapping("/updateEvent/{id_event}")
	public String updateEvent(@PathVariable(value="id_event") Long id_event , Model model){

		//On récupère l'événement
		Evenement evenement = evenementRepo.searchEventId(id_event);


		//set événement as model attribute
		model.addAttribute("evenement", evenement);

		this.evenementRepo.deleteById(id_event);

		return "evenement_update";
	}

	@GetMapping("/deleteEvent/{id_event}")
	public String deleteEvent(@PathVariable(value="id_event") Long id_event){

		this.evenementRepo.deleteById(id_event);

		return "redirect:/index";
	}

	@GetMapping("/deleteContact/{id_contact}")
	public String deleteContact(@PathVariable(value="id_contact") Long id_contact){

		this.contactRepository.deleteById(id_contact);

		return "redirect:/afficherContact";
	}
/*
	@GetMapping("/addReservation")
	public String addReservation(@PathVariable(value="id_event") Long id_event, Model model){
		try {
			//ON rcup user
			String emailUser = customUser.getUsername(); 

			Personne personne = personneRepo.findPersonneByEmail(emailUser);
			 
			//On récupère l'événement
			Evenement evenement = evenementRepo.searchEventId(id_event);

			//set événement as model attribute
			//reservationRepo.save(new Reservation(personne, evenement));
			
			Reservation reservation = new Reservation(personne, evenement);
			model.addAttribute("reservation", reservation);
		} catch (Exception e) {
			// TODO: handle exception
			model.addAttribute("exception", e);
		}

		return "reservation_add";

	}

	@PostMapping("/saveReservation")
	public String saveReservation(@ModelAttribute("reservation") Reservation reservation ){
		reservationRepo.save(reservation);
		//gestionEventMetier.addEvent(evenement);
		//model.addAttribute("evenement", evenement);
		return "redirect:/index";

	}
*/
	
	@GetMapping("/saveReservation")
	public String saveReservation(@RequestParam( value="id_event") String id_event, Model model){
		try {
			//ON récupère l'utilisateur connecté
			String emailUser = SecurityContextHolder.getContext().getAuthentication().getName();	

			Personne personne = personneRepo.findPersonneByEmail(emailUser);
			 
			//On récupère l'événement
			Long id = Long.valueOf(id_event).longValue();
			
			Evenement evenement = evenementRepo.searchEventId(id);
			System.out.println("=============" + id + "=========");
			System.out.println("=============" + id_event + "=========");
			//set événement as model attribute
			//reservationRepo.save(new Reservation(personne, evenement));
			
			Reservation reservation = new Reservation(personne, evenement);
			//model.addAttribute("test", test);
			//test = false;
			//reservationRepo.save(reservation);
			gestionEventMetier.reserverEvent(personne.getIdPerso(), evenement.getId_event());
			test = true;
			System.out.println("==========="+ test + "===========");
			model.addAttribute("test", test);
			test = false;
			System.out.println("==========="+ test + "===========");
		} catch (Exception e) {
			// TODO: handle exception
			model.addAttribute("exception", e);
		}

		return "redirect:/consulter?id_event="+ id_event;

	}
	
	@RequestMapping("/consulterCompte")
	public String consulterCompte(Model model){

		try {
			String emailUser = SecurityContextHolder.getContext().getAuthentication().getName();	

			Personne personne = personneRepo.findPersonneByEmail(emailUser);
			personne = gestionEventMetier.consulterPersonne(personne.getIdPerso());
			model.addAttribute("personne", personne);

		} catch (Exception e) {
			model.addAttribute("excection",e);
		}

		return "personne_show";

	}

	@GetMapping("/editCompte/{idPerso}")
	public String editCompte(@PathVariable(value="idPerso") Long idPerso , Model model){

		//On récupère la personne
		String emailUser = SecurityContextHolder.getContext().getAuthentication().getName();	

		Personne personne = personneRepo.findPersonneByEmail(emailUser);
		personne = gestionEventMetier.consulterPersonne(personne.getIdPerso());
		model.addAttribute("personne", personne);


		return "personne_edit";
	}
	
	@RequestMapping("/afficherReservation")
	public String afficherReservation(Model model, @RequestParam(name="page", defaultValue = "0") int p, @RequestParam(name="size", defaultValue = "5") int s){

		try {
			//List<Contact> e = contactRepository.findAll();
			//model.addAttribute("listContact",e);

			Page<Reservation> pageReservation = reservationRepo.findAll(PageRequest.of(p, s));
			model.addAttribute("listReservation", pageReservation.getContent());
			int [] pages = new int[pageReservation.getTotalPages()];
			model.addAttribute("pages", pages);
			model.addAttribute("size", s);
			model.addAttribute("pageCourante", p);

		} catch (Exception e) {
			model.addAttribute("excection",e);
		}

		return "reservation_show";

	}
	
	@RequestMapping(value ="/getLogedUser")
	public Map<String, Object> getLogedUser(HttpServletRequest httpServlet){
		
		HttpSession httpSession = httpServlet.getSession();
		SecurityContext secuContxt = (SecurityContext) httpSession.getAttribute("SPRING_SECURITY_CONTEXT");
		String emailUser = secuContxt.getAuthentication().getName();
		List<String> roles = new ArrayList<>();
		for(GrantedAuthority ga:secuContxt.getAuthentication().getAuthorities()) {
			roles.add(ga.getAuthority());
		}
		Map<String, Object> params = new HashMap<>();
		params.put("email", emailUser);
		params.put("roles", roles);
		
		return params;
		
	}
	
}
