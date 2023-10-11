package com.akbank.webmvc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.akbank.webmvc.models.Person;
import com.akbank.webmvc.repositories.PersonRepository;
import com.akbank.webmvc.services.PersonService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/person")
public class PersonController {

  @Autowired
  private PersonRepository personRepo; // setter injection

  @Autowired
  private PersonService personService;

  @GetMapping("list")
  public String Get(Model model) {

    var plist = personRepo.findAll();
    model.addAttribute("persons", plist);

    return "person/list";
  }

  // /persons/1 route
  @GetMapping("{id}")
  public String GetById(@PathVariable("id") int id, Model model) throws EntityNotFoundException {

    var person = personRepo.findById(id).orElseThrow(EntityNotFoundException::new);

    model.addAttribute("person", person);

    return "person/detail";
  }

  // veriye querystring üzerinden erişmek için @RequestParam anotasyonu
  // kullanıyoruz
  // persons/query?id=1
  @GetMapping("query")
  public String GetByIdParam(@RequestParam("id") int id) {

    var model = personRepo.findById(id).orElseThrow(EntityNotFoundException::new);

    return "person/detail";
  }

  @GetMapping("create")
  public String Create(Model model) {

    model.addAttribute("person", new Person());
    // modelin boş hali arayüze yansısın

    return "person/create";
  }

  // HTTPOST işleminde kullanıyoruz
  // ModelAttribute anotasyonu ile viewden gönderilecek olan modelin Entity olarak
  // mapping işlemini sağlıyoruz
  // @Valid ile validayon kontrolü yaptık
  // BindingResult result hata durumlarının kontrolü yaptık
  @PostMapping("create")
  public String Create(@Valid @ModelAttribute("person") Person person, BindingResult result, Model model) {

    // isvalid ise hata yoksa db at
    if (result.hasErrors()) {
      return "person/create";
    } else {
      personRepo.save(person);
      model.addAttribute("message", "kayıt işlemi başarılır");
      return "person/create";
    }

  }

  // persons/update/1
  @GetMapping("update/{id}")
  public String Update(@PathVariable("id") int id, Model model) {

    var entity = personRepo.findById(id).orElseThrow(EntityNotFoundException::new);

    model.addAttribute("person", entity);

    return "person/update";
  }

  @PostMapping("update/{id}")
  public String Update(@PathVariable("id") int id, @Valid @ModelAttribute("person") Person person,
      BindingResult result,
      RedirectAttributes attributes) {

    var entity = personRepo.findById(id).orElseThrow(EntityNotFoundException::new); // attached persist, EntityManager
                                                                                    // tarafında takip edili
    if (result.hasErrors()) {
      return "person/update";
    } else {
      entity.setName(person.getName());
      entity.setSurname(person.getSurname());

      personRepo.save(entity);

      // messajın querystring olarak değil post edilerek güvenli yöntemli
      // gönderilmesini sağlar.
      attributes.addFlashAttribute("message", "güncelleme işlem başarılı");

      return "redirect:/person/list";
    }

  }

  @GetMapping("delete/{id}")
  public RedirectView Delete(@PathVariable("id") int id, RedirectAttributes attributes) {
    // redirect işleminde redirect edilecek olan view gönderilecek model değeri
    // varsa bu bilgileri view gönderebilmek için kullanılan bir model

    var entity = personRepo.findById(id).orElseThrow(EntityNotFoundException::new);

    personRepo.delete(entity);
    // personRepo.deleteById(id);

    attributes.addFlashAttribute("message", "silme işlem başarılı");

    // Net de TempData denk geliyor.
    return new RedirectView("/person/list");
  }

}
