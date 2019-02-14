package br.com.treinaweb.springboot.entitites;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Set;

@Entity
public class Instituicao {

    @Id
    @GeneratedValue
    @Column(name = "instituicao_id")
    private Long id;

    @Column(length = 30, nullable = false)
    @NotNull(message = "Nome é obrigatório")
    @NotEmpty(message = "Nome é obrigatório")
    @Size(min = 3, max = 30, message = "Nome tem que ter entre 3 e 30 caracteres")
    private String nome;

    @Column(length = 100, nullable = false)
    @NotNull(message = "Endereço é obrigatório")
    @NotEmpty(message = "Endereço é obrigatório")
    @Size(min = 3, max = 100, message = "Endereço tem que ter entre 3 e 100 caracteres")
    private String endereco;

    @Column(name = "data_criacao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCriacao;

    @OneToMany(mappedBy = "instituicao", fetch = FetchType.LAZY, cascade = CascadeType.MERGE, orphanRemoval = true)
    @Transient
    private Set<Aluno> alunos;

    @PrePersist
    void addTimestamp() {
        dataCriacao = new Date();
    }

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

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Set<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(Set<Aluno> alunos) {
        this.alunos = alunos;
    }
}
