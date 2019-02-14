package br.com.treinaweb.springboot.controllers;

import br.com.treinaweb.springboot.entitites.Usuario;
import br.com.treinaweb.springboot.repositories.RepositorioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/usuarios")
public class UsuariosController {

    @Autowired
    private RepositorioUsuario repositorioUsuario;

    @GetMapping("/index")
    public ModelAndView index() {

        ModelAndView resultado = new ModelAndView("usuario/index");
        List<Usuario> usuarios = repositorioUsuario.findAll();
        resultado.addObject("usuarios", usuarios);
        return resultado;
    }

    @GetMapping("/inserir")
    public ModelAndView inserir() {
        ModelAndView resultado = new ModelAndView("usuario/inserir");
        resultado.addObject("usuario", new Usuario());
        return resultado;
    }

    @PostMapping("/inserir")
    public String inserir(@Valid Usuario usuario, BindingResult result) {
        if (result.hasErrors()) {
            return "usuario/inserir";
        }
        usuario.setPassword(new BCryptPasswordEncoder().encode(usuario.getPassword()));
        repositorioUsuario.save(usuario);
        return "redirect:/usuarios/index";
    }

    @GetMapping("/excluir/{usuarioId}")
    public String excluir(@PathVariable Long usuarioId) {
        repositorioUsuario.deleteById(usuarioId);
        return "redirect:/usuarios/index";
    }

    @GetMapping(value = "/pesquisarPorNome", produces = "application/json")
    public @ResponseBody
    Usuario pesquisarPorNome(@RequestParam String userName) {
        return repositorioUsuario.findByUserName(userName);
    }
}
