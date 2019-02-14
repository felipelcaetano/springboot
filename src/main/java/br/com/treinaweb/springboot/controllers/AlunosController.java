package br.com.treinaweb.springboot.controllers;

import br.com.treinaweb.springboot.entitites.Aluno;
import br.com.treinaweb.springboot.entitites.Instituicao;
import br.com.treinaweb.springboot.repositories.RepositorioAluno;
import br.com.treinaweb.springboot.repositories.RepositorioInstituicao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/alunos")
public class AlunosController {

    @Autowired
    private RepositorioAluno repositorioAluno;

    @Autowired
    private RepositorioInstituicao repositorioInstituicao;

    @GetMapping("/index")
    public ModelAndView index() {

        ModelAndView resultado = new ModelAndView("aluno/index");
        List<Aluno> alunos = repositorioAluno.findAll();
        resultado.addObject("alunos", alunos);
        return resultado;
    }

    @GetMapping("/inserir")
    public ModelAndView inserir() {
        ModelAndView resultado = new ModelAndView("aluno/inserir");
        Aluno aluno = new Aluno();
        aluno.setInstituicao(new Instituicao());
        resultado.addObject("aluno", aluno);
        resultado.addObject("instituicoes", repositorioInstituicao.findAll());
        return resultado;
    }

    @PostMapping("/inserir")
    public String inserir(@Valid Aluno aluno, BindingResult result, Model model) {
        if(result.hasErrors()) {
            model.addAttribute("instituicoes", repositorioInstituicao.findAll());
            return "aluno/inserir";
        }
        repositorioAluno.save(aluno);
        return "redirect:/alunos/index";
    }

    @GetMapping("/editar/{alunoId}")
    public ModelAndView editar(@PathVariable Long alunoId) {
        Aluno aluno = repositorioAluno.getOne(alunoId);
        ModelAndView resultado = new ModelAndView("aluno/editar");
        resultado.addObject("aluno", aluno);
        resultado.addObject("instituicoes", repositorioInstituicao.findAll());
        return resultado;
    }

    @PostMapping("/editar")
    public String editar(@Valid Aluno aluno, BindingResult result, Model model) {
        if(result.hasErrors()) {
            model.addAttribute("instituicoes", repositorioInstituicao.findAll());
            return "aluno/editar";
        }
        repositorioAluno.save(aluno);
        return "redirect:/alunos/index";
    }

    @GetMapping("/excluir/{alunoId}")
    public String excluir(@PathVariable Long alunoId) {
        repositorioAluno.deleteById(alunoId);
        return "redirect:/alunos/index";
    }
}
