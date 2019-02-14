package br.com.treinaweb.springboot.controllers;

import br.com.treinaweb.springboot.entitites.Instituicao;
import br.com.treinaweb.springboot.repositories.RepositorioInstituicao;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/instituicoes")
public class InstituicoesController {

    @Autowired
    private RepositorioInstituicao repositorioInstituicao;

    @GetMapping("/index")
    public ModelAndView index() {

        ModelAndView resultado = new ModelAndView("instituicao/index");
        List<Instituicao> instituicoes = repositorioInstituicao.findAll();
        resultado.addObject("instituicoes", instituicoes);
        return resultado;
    }

    @GetMapping("/inserir")
    public ModelAndView inserir() {
        ModelAndView resultado = new ModelAndView("instituicao/inserir");
        resultado.addObject("instituicao", new Instituicao());
        return resultado;
    }

    @PostMapping("/inserir")
    public String inserir(@Valid Instituicao instituicao, BindingResult result) {
        if (result.hasErrors()) {
            return "instituicao/inserir";
        }
        repositorioInstituicao.save(instituicao);
        return "redirect:/instituicoes/index";
    }

    @GetMapping("/editar/{instituicaoId}")
    public ModelAndView editar(@PathVariable Long instituicaoId) {
        Instituicao instituicao = repositorioInstituicao.getOne(instituicaoId);
        ModelAndView resultado = new ModelAndView("instituicao/editar");
        resultado.addObject("instituicao", instituicao);
        return resultado;
    }

    @PostMapping("/editar")
    public String editar(@Valid Instituicao instituicao, BindingResult result ) {
        if (result.hasErrors()) {
            return "instituicao/editar";
        }
        repositorioInstituicao.save(instituicao);
        return "redirect:/instituicoes/index";
    }

    @GetMapping("/excluir/{instituicaoId}")
    public String excluir(@PathVariable Long instituicaoId) {
        repositorioInstituicao.deleteById(instituicaoId);
        return "redirect:/instituicoes/index";
    }

    @GetMapping(value = "/pesquisarPorNome", produces = "application/json")
    public @ResponseBody List<Instituicao> pesquisarPorNome(@RequestParam(required = false, defaultValue = "") String nome) {
        if(Strings.isNotBlank(nome)) {
            return repositorioInstituicao.findByNomeContaining(nome);
        } else {
            return repositorioInstituicao.findAll();
        }
    }
}
