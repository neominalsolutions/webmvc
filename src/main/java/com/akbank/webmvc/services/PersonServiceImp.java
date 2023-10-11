package com.akbank.webmvc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.akbank.webmvc.models.Person;
import com.akbank.webmvc.repositories.PersonRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class PersonServiceImp implements PersonService {

  @Autowired
  private PersonRepository personRepo;

  @Override
  public Person FindById(int id) {

    // veri tabanından entity bulunamadına dair bir exception
    var entity = this.personRepo.findById(id).orElseThrow(EntityNotFoundException::new);

    return entity;

  }

  @Override
  @Transactional
  public void Save(Person person) {
    // 5 farklı save işlemi üstlenecek bir servis oldu
    // Transactional 5 farklı işlem tek bir transaction yönetilebilecek
    // JPA Transaction yönetmini kendisi üslenip default Save methodu içerisinde
    // yapılan veri tabanı çağırılarından birinde bir unchecked exception olduğu
    // takdirde veri tabınından kaynaklanan ya connection bazlı bir hata durumu
    // oluşursa transaction rollback yapıyor.
  }

}
