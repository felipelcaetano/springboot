package br.com.treinaweb.springboot.entitites;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.*;

@Entity
public class Instituicao {

    @Id
    @GeneratedValue
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
}
