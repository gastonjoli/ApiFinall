package com.InfoApiFinal.ApiFinal.Service;

import com.InfoApiFinal.ApiFinal.Entities.Evento;
import com.InfoApiFinal.ApiFinal.Repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;
    @Transactional
    public Evento crearEvento(Evento evento){return eventoRepository.save(evento);}
    @Transactional
    public void eliminarEvento(Long id){eventoRepository.deleteById(id);}
    @Transactional
    public Optional<Evento> buscarEventoId(Long id){return eventoRepository.findById(id);}
    @Transactional
    public List<Evento> allEventos(){return eventoRepository.findAll();}
}
