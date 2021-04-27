package com.univ.tours.app.GestionEvent.controllers;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

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

import com.univ.tours.app.GestionEvent.dao.ContactRepository;
import com.univ.tours.app.GestionEvent.dao.EvenementRepository;
import com.univ.tours.app.GestionEvent.entities.Contact;
import com.univ.tours.app.GestionEvent.entities.Evenement;
import com.univ.tours.app.GestionEvent.metier.GestionEventMetier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

@Controller
@ControllerAdvice
public class GestionEventController {

	@Autowired
	private GestionEventMetier gestionEventMetier;
	@Autowired
	private EvenementRepository evenementRepo;
	@Autowired
	private ContactRepository contactRepository;
	
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
		return "contact";
	}

	@PostMapping("/saveContact")
	public String saveContact(@ModelAttribute("contact") Contact contact){
		//evenementRepo.save(evenement);
		contactRepository.save(contact);
		//model.addAttribute("evenement", evenement);
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


}
