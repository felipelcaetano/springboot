package br.com.treinaweb.springboot.controllers;

import br.com.treinaweb.springboot.entitites.Instituicao;
import br.com.treinaweb.springboot.repositories.RepositorioInstituicao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

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
    public String inserir(Instituicao instituicao) {
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
    public String editar(Instituicao instituicao) {
        repositorioInstituicao.save(instituicao);
        return "redirect:/instituicoes/index";
    }

    @GetMapping("/excluir/{instituicaoId}")
    public String excluir(@PathVariable Long instituicaoId) {
        repositorioInstituicao.deleteById(instituicaoId);
        return "redirect:/instituicoes/index";
    }

    @GetMapping({"/pesquisarPorNome/{nome}", "/pesquisarPorNome"})
    public @ResponseBody List<Instituicao> pesquisarPorNome(@PathVariable Optional<String> nome) {
        if(nome.isPresent()) {
            return repositorioInstituicao.findByNomeContaining(nome.get());
        } else {
            return repositorioInstituicao.findAll();
        }
    }
}
