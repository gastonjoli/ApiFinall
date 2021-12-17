package com.InfoApiFinal.ApiFinal.Repository;

import com.InfoApiFinal.ApiFinal.Entities.Voto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VotoRepository extends JpaRepository<Voto, Long>{

}
