package com.akbank.webmvc.models;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// code first yöntemi herhangi xml dosyası olmadan anotayonlar üzerinden nesne oluşturma ilişkilendirme bunların databa tarafında code üzerinden generate edilmesi yaklaşımı.

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Person")
public class Person { // POJO

  @Id // PK alan yapması için ekledik
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "PersonId")

  private int Id;

  @Column(name = "Name", nullable = false) // veri tabanında boş geçilemeyen alanlar için nullable tanımlayalım
  @NotEmpty(message = "Name alanı boş geçilemez")
  @NotBlank(message = "Name alanı boş bırakıldı")
  @Size(min = 4, max = 20, message = "4 karakterdan az olamaz 20 karakter falza olmaz")

  private String name;

  @Column(name = "Surname", nullable = true)
  @NotEmpty(message = "Surname alanı boş geçilemez")
  private String surname;

}
