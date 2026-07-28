package com.event.registration.controller;

import com.event.registration.service.EvenementService;
import com.event.registration.service.InscriptionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WebController {

    private final InscriptionService inscriptionService;
    private final EvenementService evenementService;

    public WebController(InscriptionService inscriptionService, EvenementService evenementService) {
        this.inscriptionService = inscriptionService;
        this.evenementService = evenementService;
    }

    @GetMapping("/")
    public String formulaireInscription(Model model) {
        model.addAttribute("evenements", evenementService.getAllEvenements());
        return "inscription";
    }

    @GetMapping("/liste")
    public String listeInscriptions(@RequestParam(required = false) Long evenementId, Model model) {
        model.addAttribute("evenements", evenementService.getAllEvenements());
        model.addAttribute("evenementId", evenementId);
        model.addAttribute("inscriptions", inscriptionService.getInscriptionsByEvenement(evenementId));
        return "liste";
    }
}
