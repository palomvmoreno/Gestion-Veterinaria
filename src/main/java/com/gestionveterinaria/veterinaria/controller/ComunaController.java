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

import com.gestionveterinaria.veterinaria.DTO.ComunaDTO;
import com.gestionveterinaria.veterinaria.model.Comuna;
import com.gestionveterinaria.veterinaria.service.ComunaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/comunas")
public class ComunaController {
   @Autowired
    private ComunaService comunaService;

    @GetMapping
    public ResponseEntity<?> todasLasComunas() {
        List<ComunaDTO> comunas = comunaService.obtenerTodas();
        if (!comunas.isEmpty()) {
            return new ResponseEntity<>(comunas, HttpStatus.OK);
        }
        return new ResponseEntity<>("No hay comunas", HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> comunaPorId(@PathVariable Integer id) {
        try {
            ComunaDTO comuna = comunaService.buscarPorId(id);
            return new ResponseEntity<>(comuna, HttpStatus.ACCEPTED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>("No se encontró la comuna", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<?> agregarComuna(@Valid @RequestBody Comuna comuna) {
        try {
            return new ResponseEntity<>(comunaService.guardar(comuna), HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>("No se pudo guardar la comuna", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarComuna(@PathVariable Integer id) {
        try {
            String resultado = comunaService.eliminar(id);
            return new ResponseEntity<>(resultado, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
