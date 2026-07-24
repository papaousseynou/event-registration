package com.event.registration.controller;

import com.event.registration.service.InscriptionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    private final InscriptionService inscriptionService;

    public WebController(InscriptionService inscriptionService) {
        this.inscriptionService = inscriptionService;
    }

    @GetMapping("/")
    public String formulaireInscription() {
        return "inscription";
    }

    @GetMapping("/liste")
    public String listeInscriptions(Model model) {
        model.addAttribute("inscriptions", inscriptionService.getAllInscriptions());
        return "liste";
    }
}
