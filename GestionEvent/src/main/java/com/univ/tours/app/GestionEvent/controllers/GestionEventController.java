package com.univ.tours.app.GestionEvent.controllers;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.univ.tours.app.GestionEvent.dao.EvenementRepository;
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

	@RequestMapping("/index")
	public String listeEvent(Model model, @RequestParam(name="page", defaultValue = "0") int p, @RequestParam(name="size", defaultValue = "10") int s){
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


	@RequestMapping("/rechercheEvent")
	public String rechercheEvent(Model model,String nom_event) {
		try {
			Evenement e = gestionEventMetier.rechercherEvent(nom_event);
			model.addAttribute("evenement",e);

		} catch (Exception e) {
			model.addAttribute("excection",e);
		}

		return "evenement";
	}

	@RequestMapping("/contact")
	public String contact() {

		return "contact";
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
}