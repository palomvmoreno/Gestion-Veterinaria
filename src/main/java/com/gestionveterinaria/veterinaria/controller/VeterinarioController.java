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

import com.gestionveterinaria.veterinaria.DTO.VeterinarioDTO;
import com.gestionveterinaria.veterinaria.model.Veterinario;
import com.gestionveterinaria.veterinaria.service.VeterinarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/veterinario")
public class VeterinarioController {
   @Autowired
    private VeterinarioService veterinarioService;

    @GetMapping
    public ResponseEntity<List<VeterinarioDTO>> listarTodos() {
        List<VeterinarioDTO> lista = veterinarioService.obtenerTodos();
        if (lista.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VeterinarioDTO> obtenerPorId(@PathVariable Integer id) {
        try {
            VeterinarioDTO v = veterinarioService.buscarPorId(id);
            return new ResponseEntity<>(v, HttpStatus.OK);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Veterinario> crear(@Valid @RequestBody Veterinario veterinario) {
        try {
            Veterinario nuevo = veterinarioService.guardar(veterinario);
            return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id) {
        String mensaje = veterinarioService.eliminar(id);
        if (mensaje.contains("exitosamente")) {
            return new ResponseEntity<>(mensaje, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
        }
    }

}
