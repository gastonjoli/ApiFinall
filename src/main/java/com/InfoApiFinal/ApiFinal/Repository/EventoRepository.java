package com.InfoApiFinal.ApiFinal.Repository;


import com.InfoApiFinal.ApiFinal.Entities.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long> {

}
