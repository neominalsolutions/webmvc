package com.akbank.webmvc.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.akbank.webmvc.models.User;

// FrontController gelen HttpRequestleri @Controller Anotasyonu sayesinde ilgili controller handler'a aktarıyor
@Controller
@RequestMapping("/") // /about, /home, /contact / anasayfa
public class HomeController {

  // HTTPGET isteklerini bu anotasyon ile karşılarız
  @GetMapping("")
  String Index(Model model) {

    model.addAttribute("message", "mesaj");
    // model ile değerleri view'e bind ediyoruz
    model.addAttribute("isActive", false);

    // html element döndürme
    model.addAttribute("htmlContent", "<p>Html Content Render</p>");

    // template object değer gönderme
    var user = new User("ali", "tan");
    model.addAttribute("user", user);

    var users = new ArrayList<User>();
    var user1 = new User("mustafa", "tan");
    var user2 = new User("ahmet", "can");
    users.add(0, user1);
    users.add(1, user2);

    model.addAttribute("users", users);

    // home klasörü altındaki index html dosyasına yönlendir.
    return "home/index";
  }

  @GetMapping("about") // /about
  String About(Model model) {

    model.addAttribute("message", "from About");

    return "home/about";
  }

  @GetMapping("contact")
  String Contact() {
    return "home/contact";
  }

  @GetMapping("admin")
  String AdminPage() {
    throw new Error("not found object");
    // return "home/admin";
  }

}
