package br.com.treinaweb.springboot.repositories;

import br.com.treinaweb.springboot.entitites.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositorioUsuario extends JpaRepository<Usuario, Long> {

    public Usuario findByUserName(String userName);
}
