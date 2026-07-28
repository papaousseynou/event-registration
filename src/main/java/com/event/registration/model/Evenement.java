package com.event.registration.model;

import java.time.LocalDate;

public class Evenement {

    private Long id;
    private String nom;
    private String description;
    private LocalDate dateEvenement;
    private String lieu;

    public Evenement() {
    }

    public Evenement(Long id, String nom, String description, LocalDate dateEvenement, String lieu) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.dateEvenement = dateEvenement;
        this.lieu = lieu;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDateEvenement() {
        return dateEvenement;
    }

    public void setDateEvenement(LocalDate dateEvenement) {
        this.dateEvenement = dateEvenement;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }
}
