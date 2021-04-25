package com.univ.tours.app.GestionEvent.controllers;

import java.sql.SQLException;
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.univ.tours.app.GestionEvent.entities.Evenement;
import com.univ.tours.app.GestionEvent.metier.GestionEventMetier;
import org.springframework.context.annotation.ComponentScan;

@Controller
@ControllerAdvice
public class GestionEventController {

	@Autowired
	private GestionEventMetier gestionEventMetier;
	
	@RequestMapping("/home")
	public String listeEvent(Model model){
		//List<Evenement> evenement = gestionEventMetier.listeEvent();
		//model.addAttribute("evenement", evenement);
		model.addAttribute("listeEvent", gestionEventMetier.listeEvent());
		return "home";
		
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
}
