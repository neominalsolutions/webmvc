package com.akbank.webmvc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.akbank.webmvc.models.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

}
