package br.com.treinaweb.springboot.entitites;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
public class Aluno {

    @Id
    @GeneratedValue
    @Column(name = "aluno_id")
    private Long id;

    @Column(length = 50)
    @NotNull(message = "Nome é obrigatório")
    @NotBlank(message = "Nome é obrigatório")
    @Size(min = 3, max = 100, message = "Nome tem que ter entre 3 e 100 caracteres")
    private String nome;

    @Column
    @NotNull(message = "Idade é obrigatória")
    @Min(value = 16, message = "Idade mínima é 16 anos")
    @Max(value = 100, message = "Idade máximo é 100 anos")
    private Integer idade;

    @NotNull(message = "Instituição é obrigatória")
    @ManyToOne(fetch = FetchType.EAGER)
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
