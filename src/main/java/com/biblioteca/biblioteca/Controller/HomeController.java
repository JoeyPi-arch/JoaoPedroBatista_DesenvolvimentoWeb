package com.biblioteca.biblioteca.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("nomeSistema", "Sistema de Biblioteca");
        model.addAttribute("mensagemBoasVindas", "Bem-vindo ao Sistema de Biblioteca!");
        return "index";
    }
}
