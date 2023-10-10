package com.akbank.webmvc.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor // Boş contructor
@AllArgsConstructor // name ve surname değerlerini constructor üzerinden kullanılabilir yapar
@Data // tüm class daki field yapılarını alıp getter setter ekler, getHashCode
public class User {

  // @Setter @Getter
  private String name;
  private String surname;

  // @Getter
  // private String fullname;

}
