package com.univ.tours.app.GestionEvent.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.univ.tours.app.GestionEvent.entities.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long>{

}
