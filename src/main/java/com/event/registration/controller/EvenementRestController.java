package com.event.registration.controller;

import com.event.registration.model.Evenement;
import com.event.registration.service.EvenementService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/evenements")
public class EvenementRestController {

    private final EvenementService evenementService;

    public EvenementRestController(EvenementService evenementService) {
        this.evenementService = evenementService;
    }

    @GetMapping
    public List<Evenement> getAllEvenements() {
        return evenementService.getAllEvenements();
    }

    @GetMapping("/{id}")
    public Evenement getEvenement(@PathVariable Long id) {
        return evenementService.getEvenementById(id)
                .orElseThrow(() -> new IllegalArgumentException("Événement introuvable : " + id));
    }
}
