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
    public ResponseEntity<?> todasLasClinicas() {
        List<ClinicaDTO> clinicas = clinicaService.obtenerTodas();
        if (!clinicas.isEmpty()) {
            return new ResponseEntity<>(clinicas, HttpStatus.OK);
        }
        return new ResponseEntity<>("No hay clínicas veterinarias", HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> clinicaPorId(@PathVariable Integer id) {
        try {
            ClinicaDTO clinica = clinicaService.buscarPorId(id);
            return new ResponseEntity<>(clinica, HttpStatus.ACCEPTED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>("No se exite la clínica", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<?> agregarClinica(@Valid @RequestBody Clinica clinica) {
        try {
            return new ResponseEntity<>(clinicaService.guardar(clinica), HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>("No se pudo guardar la clínica", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarClinica(@PathVariable Integer id) {
        try {
            String resultado = clinicaService.eliminar(id);
            return new ResponseEntity<>(resultado, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
