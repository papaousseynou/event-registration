package com.event.registration.service;

import com.event.registration.model.Inscription;
import com.event.registration.repository.InscriptionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InscriptionService {

    private final InscriptionRepository inscriptionRepository;

    public InscriptionService(InscriptionRepository inscriptionRepository) {
        this.inscriptionRepository = inscriptionRepository;
    }

    public Inscription inscrire(Inscription inscription) {
        return inscriptionRepository.save(inscription);
    }

    public List<Inscription> getAllInscriptions() {
        return inscriptionRepository.findAll();
    }
}
