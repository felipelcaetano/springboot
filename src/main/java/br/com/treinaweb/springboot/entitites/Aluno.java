package br.com.treinaweb.springboot.entitites;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
public class Aluno {

    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 50)
    private String nome;

    @Column
    @NotNull(message = "Idade é obrigatória")
    @NotEmpty(message = "Idade é obrigatória")
    @Min(value = 16, message = "Idade mínima é 16 anos")
    @Max(value = 100, message = "Idade máximo é 100 anos")
    private Integer idade;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "instituicao_id", nullable = false)
    private Instituicao instituicao;

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

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public Instituicao getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(Instituicao instituicao) {
        this.instituicao = instituicao;
    }
}
