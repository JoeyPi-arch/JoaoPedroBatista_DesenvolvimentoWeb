package com.biblioteca.biblioteca.Controller;

import com.biblioteca.biblioteca.model.Categoria;
import com.biblioteca.biblioteca.service.CategoriaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/categorias")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping
    public String inicio(Model model) {
        model.addAttribute("categorias", categoriaService.listar());

        return "categorias/inicio";
    }

    @GetMapping("/novo")
    public String novaCategoria(Model model) {
        model.addAttribute("categoria", new Categoria());

        return "categorias/form";
    }

    @PostMapping
    public String cadastrar(@ModelAttribute Categoria categoria) {
        categoriaService.cadastrar(categoria);

        return "redirect:/categorias";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {

        Categoria categoria = categoriaService.buscarPorId(id);

        if (categoria == null) {
            return "redirect:/categorias";
        }

        model.addAttribute("categoria", categoria);

        return "categorias/confirmacao";
    }

    @PostMapping("/editar/{id}")
    public String atualizar(
            @PathVariable Long id,
            @ModelAttribute Categoria categoria) {

        if (categoriaService.buscarPorId(id) == null) {
            return "redirect:/categorias";
        }

        categoriaService.atualizar(id, categoria);

        return "redirect:/categorias";
    }

    @PostMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        categoriaService.excluir(id);

        return "redirect:/categorias";
    }
}
