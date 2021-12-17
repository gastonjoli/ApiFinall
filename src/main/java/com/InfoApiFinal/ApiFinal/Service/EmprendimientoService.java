package com.InfoApiFinal.ApiFinal.Service;

import com.InfoApiFinal.ApiFinal.Entities.Emprendimiento;
import com.InfoApiFinal.ApiFinal.Entities.Tags;
import com.InfoApiFinal.ApiFinal.Repository.EmprendimientoRepository;
import com.InfoApiFinal.ApiFinal.Repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmprendimientoService {
    @Autowired
    private EmprendimientoRepository emprendimientoRepository;
    @Autowired
    private TagRepository tagRepository;
    @Transactional
    public Emprendimiento guardarEmprend(Emprendimiento emprendimiento){return emprendimientoRepository.save(emprendimiento);}
    @Transactional
    public void borrarEmprend(Long id){emprendimientoRepository.deleteById(id);}
    @Transactional
    public List<Emprendimiento> todosEmprendimientos(){return emprendimientoRepository.findAll();}
//    public Iterable<Emprendimiento> todosEmprendimientos(){return emprendimientoRepository.findAll();}
    @Transactional
    public Optional<Emprendimiento> buscarPorIdEmprend(Long id){return emprendimientoRepository.findById(id);}
    @Transactional
    public List<Emprendimiento> buscarEmprendPorTag(String tags){
        List<Emprendimiento> list = new ArrayList<>();
        for (Emprendimiento x : todosEmprendimientos()) {
            for (Tags j : x.getTags()) {
                if(j.getNombre().toLowerCase(Locale.ROOT).contains(tags.toLowerCase(Locale.ROOT))){
                    list.add(x);
                }
            }
        }
        return list;
    }
    @Transactional
    public List<Emprendimiento> buscarEmpSinPublicar(){
        return emprendimientoRepository.findAll().stream().filter(p -> p.getPublicado().equals(false)).collect(Collectors.toList());
    }
}
