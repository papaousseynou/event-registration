package com.event.registration.service;

import com.event.registration.model.Inscription;
import com.event.registration.repository.EvenementRepository;
import com.event.registration.repository.InscriptionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InscriptionService {

    private final InscriptionRepository inscriptionRepository;
    private final EvenementRepository evenementRepository;

    public InscriptionService(InscriptionRepository inscriptionRepository,
                              EvenementRepository evenementRepository) {
        this.inscriptionRepository = inscriptionRepository;
        this.evenementRepository = evenementRepository;
    }

    public Inscription inscrire(Inscription inscription) {
        evenementRepository.findById(inscription.getEvenementId())
                .orElseThrow(() -> new IllegalArgumentException("Événement introuvable"));
        return inscriptionRepository.save(inscription);
    }

    public List<Inscription> getAllInscriptions() {
        return inscriptionRepository.findAll();
    }

    public List<Inscription> getInscriptionsByEvenement(Long evenementId) {
        if (evenementId == null) {
            return inscriptionRepository.findAll();
        }
        evenementRepository.findById(evenementId)
                .orElseThrow(() -> new IllegalArgumentException("Événement introuvable"));
        return inscriptionRepository.findByEvenementId(evenementId);
    }
}
