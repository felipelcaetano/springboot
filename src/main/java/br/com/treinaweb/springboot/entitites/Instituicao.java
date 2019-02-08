package br.com.treinaweb.springboot.entitites;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Instituicao {

    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 30)
    private String nome;

    @Column(length = 100)
    private String endereco;

    @OneToMany(mappedBy = "instituicao")
    private Set<Aluno> alunos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Set<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(Set<Aluno> alunos) {
        this.alunos = alunos;
    }
}
