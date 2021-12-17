package com.InfoApiFinal.ApiFinal.Service;

import com.InfoApiFinal.ApiFinal.Entities.Voto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VotoService {

    @Autowired
    private com.InfoApiFinal.ApiFinal.Repository.VotoRepository votoRepository;

    @Transactional
    public Voto crearVoto(Voto voto){return (Voto) com.InfoApiFinal.ApiFinal.Repository.VotoRepository.save(voto);}

    @Transactional
    public List<Voto> buscarVotoPorUsuario(String username){
        return votoRepository.findAll().stream().filter(x-> x.getUsername().getEmail().equals(username)).collect(Collectors.toList());
    }
    @Transactional
    public List<Voto> buscarVotoPorEvento(Long evento){
        return votoRepository.findAll().stream().filter(x-> x.getEvento().getIdEvento().equals(evento)).collect(Collectors.toList());
    }


}
