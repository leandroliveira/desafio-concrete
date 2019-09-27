package com.app.desafioconcrete.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.desafioconcrete.api.entities.Phone;

@Repository
public interface PhoneRepository extends JpaRepository<Phone, Long>{

}
