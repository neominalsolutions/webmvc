package com.akbank.webmvc.services;

import com.akbank.webmvc.models.Person;

public interface PersonService {

  Person FindById(int id);

  void Save(Person person);

}
