package com.univ.tours.app.GestionEvent.entities;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Contact {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id_contact;

    private Date date_msg;

     private String nom, email, sujet, message;


    //CONSTRUCTORS
    public Contact() {
        super();
        // TODO Auto-generated constructor stub
    }


    public Contact( String nom, String email, String sujet, String message) {
        super();
        this.date_msg = new Date();
        this.nom = nom;
        this.email = email;
        this.sujet = sujet;
        this.message = message;
    }






    //GETTERS & SETTERS
    public Long getId_contact() {
        return id_contact;
    }


    public void setId_contact(Long id_contact) {
        this.id_contact = id_contact;
    }


    public String getNom() {
        return nom;
    }


    public void setNom(String nom) {
        this.nom = nom;
    }


    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }


    public String getSujet() {
        return sujet;
    }


    public void setSujet(String sujet) {
        this.sujet = sujet;
    }


    public String getMessage() {
        return message;
    }


    public void setMessage(String message) {
        this.message = message;
    }


    public Date getDate_msg() {
        return date_msg;
    }


    public void setDate_msg(Date date_msg) {
        this.date_msg = date_msg;
    }




}