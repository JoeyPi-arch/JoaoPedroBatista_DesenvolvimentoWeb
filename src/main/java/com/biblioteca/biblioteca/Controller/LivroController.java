package com.biblioteca.biblioteca.Controller;

import com.biblioteca.biblioteca.model.Livro;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/livros")
public class LivroController {

    private final List<Livro> livros = new ArrayList<>();
    private long proximoId = 1;

    @GetMapping
    public String inicio(Model model) {

        model.addAttribute("livros", livros);

        return "livros/inicio";
    }

    @GetMapping("/novo")
    public String novoLivro(Model model) {

        model.addAttribute("livro", new Livro());

        return "livros/form";
    }

    @PostMapping
    public String cadastrarLivro(@ModelAttribute Livro livro) {

        livro.setId(proximoId++);

        livros.add(livro);

        return "redirect:/livros";
    }

    @PostMapping("/excluir/{id}")
    public String excluirLivro(@PathVariable Long id) {

        livros.removeIf(livro -> livro.getId().equals(id));

        return "redirect:/livros";
    }
}
