package com.univ.tours.app.GestionEvent.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.univ.tours.app.GestionEvent.entities.Evenement;
import com.univ.tours.app.GestionEvent.metier.GestionEventMetier;

@Controller
public class GestionEventController {

	@Autowired
	private GestionEventMetier gestionEventMetier;

	@RequestMapping("/evenement")
	public String index() {
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
