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

import com.gestionveterinaria.veterinaria.DTO.ClinicaDTO;
import com.gestionveterinaria.veterinaria.model.Clinica;
import com.gestionveterinaria.veterinaria.service.ClinicaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/clinicas")
public class ClinicaController {
   @Autowired
    private ClinicaService clinicaService;

    @GetMapping
    public ResponseEntity<List<ClinicaDTO>> todasLasClinicas() {
        List<ClinicaDTO> clinicas = clinicaService.obtenerTodas();
        if (clinicas.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(clinicas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClinicaDTO> buscarPorId(@PathVariable Integer id) {
        try {
            ClinicaDTO clinica = clinicaService.buscarPorId(id);
            return new ResponseEntity<>(clinica, HttpStatus.OK);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Clinica> agregarClinica(@Valid @RequestBody Clinica clinica) {
        try {
            Clinica guardada = clinicaService.guardar(clinica);
            return new ResponseEntity<>(guardada, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarClinica(@PathVariable Integer id) {
        String resultado = clinicaService.eliminar(id);
        if (resultado.contains("Eliminado")) {
            return new ResponseEntity<>(resultado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(resultado, HttpStatus.NOT_FOUND);
        }
    }

}
