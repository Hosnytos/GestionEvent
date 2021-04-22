package com.univ.tours.app.GestionEvent.web;

import java.sql.SQLException;
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;

import com.univ.tours.app.GestionEvent.entities.Evenement;
import com.univ.tours.app.GestionEvent.metier.GestionEventMetier;

@Controller
@ControllerAdvice
public class GestionEventController {

	@Autowired
	private GestionEventMetier gestionEventMetier;

	@RequestMapping("/home")
	public String index() {
		return "evenement";
	}
	
	@RequestMapping("/evenement")
	public String listeEvent(Model model){
		//List<Evenement> evenement = gestionEventMetier.listeEvent();
		//model.addAttribute("evenement", evenement);
		model.addAttribute("listeEvent", gestionEventMetier.listeEvent());
		return "evenement";
		
	}


	@RequestMapping("/rechercherEvent")
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
