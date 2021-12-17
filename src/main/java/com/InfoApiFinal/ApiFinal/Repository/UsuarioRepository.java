package com.InfoApiFinal.ApiFinal.Repository;

import com.InfoApiFinal.ApiFinal.Entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
