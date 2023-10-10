package com.akbank.webmvc.models;

// Lombook nedir

public class UserNotLombook {

  private String name;
  private String surname;

  public UserNotLombook(String name, String surname) {
    super();
    this.name = name;
    this.surname = surname;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSurname() {
    return surname;
  }

  public void setSurname(String surname) {
    this.surname = surname;
  }

}
