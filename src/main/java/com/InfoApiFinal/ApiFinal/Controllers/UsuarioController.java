package com.InfoApiFinal.ApiFinal.Controllers;

import com.InfoApiFinal.ApiFinal.Dtos.UsuarioDto;
import com.InfoApiFinal.ApiFinal.Entities.Usuario;
import com.InfoApiFinal.ApiFinal.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping(value = "/")
    public ResponseEntity<?> obtenerTodosUsuarios(){
        List<Usuario> usuarios = usuarioService.obtUsuarios();

        List<com.InfoApiFinal.ApiFinal.Dtos.UsuarioDto> listaDtosUsuario = new ArrayList<>();
        if(!usuarios.isEmpty()) {
            for (Usuario s: usuarios) listaDtosUsuario.add(com.InfoApiFinal.ApiFinal.Dtos.UsuarioDto.UsuarioAUsuarioDto(s));
            return ResponseEntity.ok(listaDtosUsuario);
        }
        return new ResponseEntity<>("No existe ningún usuario agregado", HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/")
    public ResponseEntity<?> guardarUsuario(@Valid @RequestBody Usuario usuario){
        try {
            usuarioService.guardarUsuario(usuario);
            return ResponseEntity.status(HttpStatus.CREATED).body(com.InfoApiFinal.ApiFinal.Dtos.UsuarioDto.UsuarioAUsuarioDto(usuario));
        }catch(Exception e) {
            return new ResponseEntity<>("Error: ya existe se encuentra resgistrado el mail " + usuario.getEmail(), HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping(value = "/{id}")
    ResponseEntity<?> modifiarUsuario(@Valid @RequestBody  Usuario usuarioModif, @PathVariable(value = "id", required = false) @Valid int idUsuario, Errors error) {

        Optional<Usuario> usuario = usuarioService.obtUnUsuarioPorId((long) idUsuario);
        if(!usuario.isPresent()){return new ResponseEntity<>("Error: no se encuentra ningún usuario con el id " + idUsuario, HttpStatus.NOT_FOUND);}

        usuario.get().setApellido(usuarioModif.getApellido());
        usuario.get().setNombre(usuarioModif.getNombre());
        usuario.get().setEmail(usuarioModif.getEmail());
        usuario.get().setPassword(usuarioModif.getPassword());
        usuario.get().setCiudad(usuarioModif.getCiudad());
        usuario.get().setProvincia(usuarioModif.getProvincia());
        usuario.get().setPais(usuarioModif.getPais());
        usuario.get().setTipo(usuarioModif.getTipo());

        try {
        usuarioService.guardarUsuario(usuario.get());
        return new ResponseEntity<>(com.InfoApiFinal.ApiFinal.Dtos.UsuarioDto.UsuarioAUsuarioDto(usuario.get()), HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>("Error: ya existe se encuentra resgistrado el mail: " + usuario.get().getEmail(), HttpStatus.BAD_REQUEST);}
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<Object> eliminarUsuario(@PathVariable(value = "id") int idUsuario){
        if(!usuarioService.obtUnUsuarioPorId((long) idUsuario).isPresent()){
                return new ResponseEntity<>("No se encuentra el usuario con id: " + idUsuario, HttpStatus.NOT_FOUND);
        }
        usuarioService.borrarUsuario((long) idUsuario);
        return ResponseEntity.ok("usuario borrado exitosamente");
    }

    @GetMapping(value = "/buscarPorId/{id}")
    public ResponseEntity<?> obtenerUnUsuarioPorId(@PathVariable(value = "id") int id){
        Optional<Usuario> usuario = usuarioService.obtUnUsuarioPorId((long) id);
        if (!usuario.isPresent()) {
            return new ResponseEntity<>("No se encuentra el usuario por el id ingresado: " + id, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(com.InfoApiFinal.ApiFinal.Dtos.UsuarioDto.UsuarioAUsuarioDto(usuario.get()));
    }
    @GetMapping(value = "/buscarPorCiudad")
    public ResponseEntity<?> obtenerUnUsuarioPorCiudad(@RequestParam(value = "ciudad") String ciudad){
//            @PathVariable(value = "id") String ciudad){
        List<Usuario> usuarioLista = usuarioService.obtUsuariosPorCiudad(ciudad);
        if(!usuarioLista.isEmpty()) {
            List<UsuarioDto> listaDtosUsuario = new ArrayList<>();
            for (Usuario s: usuarioLista) listaDtosUsuario.add(UsuarioDto.UsuarioAUsuarioDto(s));
            return ResponseEntity.ok(listaDtosUsuario);
        }
        return new ResponseEntity<>("No se encuentra el usuario por la ciudad ingresada: " + ciudad, HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/buscarPorFecha/{id}")
    public ResponseEntity<?> obtenerUnUsuarioPorFechaPost(@PathVariable(value = "id") @DateTimeFormat(pattern = "dd-MM-yyyy") Date fecha){
            List<Usuario> usuarioLista = usuarioService.obtUsuariosPorFechaPost(fecha);
            if (!usuarioLista.isEmpty()) {
                List<UsuarioDto> listaDtosUsuario = new ArrayList<>();
                for (Usuario s : usuarioLista) listaDtosUsuario.add(UsuarioDto.UsuarioAUsuarioDto(s));
                return ResponseEntity.ok(listaDtosUsuario);
            }

            return new ResponseEntity<>("No hay ningun usuario creado posteriormente a la fecha ingresada", HttpStatus.NOT_FOUND);
    }
}

