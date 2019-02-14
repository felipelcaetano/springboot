package br.com.treinaweb.springboot.entitites;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Usuario {

    @Id
    @GeneratedValue
    @Column(name = "usuario_id")
    private Long id;

    @Column(name = "user_name", nullable = false, length = 10)
    @Size(min = 3, max = 10, message = "Usuário deve conter entre 3 e 10 caracteres")
    @NotNull(message = "Usuário é obrigatório")
    @NotBlank(message = "Usuário é obrigatório")
    private String userName;

    @Column(nullable = false, length = 150)
    @NotNull(message = "Senha é obrigatória")
    @NotBlank(message = "Senha é obrigatória")
    private String password;

    @Column(nullable = false, length = 20)
    private String role;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
