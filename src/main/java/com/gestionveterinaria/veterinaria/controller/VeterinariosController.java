package com.gestionveterinaria.veterinaria.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestionveterinaria.veterinaria.DTO.VeterinariosDTO;
import com.gestionveterinaria.veterinaria.model.Veterinarios;
import com.gestionveterinaria.veterinaria.service.VeterinariosService;

@RestController
@RequestMapping("/api/v1/veterinarios")
public class VeterinariosController {
    @Autowired
    private VeterinariosService veterinariosService;

    @GetMapping
    public ResponseEntity<List<VeterinariosDTO>> listarTodos() {
        List<VeterinariosDTO> lista = veterinariosService.obtenerTodos();
        if (lista.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VeterinariosDTO> obtenerPorId(@PathVariable Integer id) {
        try {
            VeterinariosDTO v = veterinariosService.buscarPorId(id);
            return new ResponseEntity<>(v, HttpStatus.OK);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Veterinarios> crear(@RequestBody Veterinarios asignacion) {
        try {
            Veterinarios nuevaAsignacion = veterinariosService.guardar(asignacion);
            return new ResponseEntity<>(nuevaAsignacion, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id) {
        String mensaje = veterinariosService.eliminar(id);
        if (mensaje.contains("exitosamente")) {
            return new ResponseEntity<>(mensaje, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
        }
    }

}
