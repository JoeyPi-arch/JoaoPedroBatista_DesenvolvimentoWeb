package com.biblioteca.biblioteca.Controller;

import com.biblioteca.biblioteca.model.Autor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/autores")
public class AutorController {

    private final List<Autor> autores = new ArrayList<>();
    private long proximoId = 1;

    @GetMapping
    public String inicio(Model model) {
        model.addAttribute("autores", autores);
        return "autores/inicio";
    }

    @GetMapping("/novo")
    public String novoAutor(Model model) {
        model.addAttribute("autor", new Autor());
        return "autores/form";
    }

    @PostMapping
    public String cadastrarAutor(@ModelAttribute Autor autor) {
        autor.setId(proximoId++);
        autores.add(autor);

        return "redirect:/autores";
    }

    @GetMapping("/editar/{id}")
    public String editarAutor(@PathVariable Long id, Model model) {

        Autor autor = buscarPorId(id);

        if (autor == null) {
            return "redirect:/autores";
        }

        model.addAttribute("autor", autor);

        return "autores/form";
    }

    @PostMapping("/editar/{id}")
    public String atualizarAutor(
            @PathVariable Long id,
            @ModelAttribute Autor autorAtualizado) {

        Autor autor = buscarPorId(id);

        if (autor == null) {
            return "redirect:/autores";
        }

        autor.setNome(autorAtualizado.getNome());
        autor.setNacionalidade(autorAtualizado.getNacionalidade());
        autor.setEmail(autorAtualizado.getEmail());

        return "redirect:/autores";
    }

    private Autor buscarPorId(Long id) {
        for (Autor autor : autores) {
            if (autor.getId().equals(id)) {
                return autor;
            }
        }

        return null;
    }

    @PostMapping("/excluir/{id}")
    public String excluirAutor(@PathVariable Long id) {
        autores.removeIf(autor -> autor.getId().equals(id));

        return "redirect:/autores";
    }
}