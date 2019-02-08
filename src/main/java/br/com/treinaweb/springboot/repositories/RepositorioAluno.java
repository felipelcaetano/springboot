package br.com.treinaweb.springboot.repositories;

import br.com.treinaweb.springboot.entitites.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositorioAluno extends JpaRepository<Aluno, Long> {
}
