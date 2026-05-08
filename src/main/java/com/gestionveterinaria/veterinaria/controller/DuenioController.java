package com.gestionveterinaria.veterinaria.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestionveterinaria.veterinaria.DTO.DuenioDTO;
import com.gestionveterinaria.veterinaria.model.Duenio;
import com.gestionveterinaria.veterinaria.service.DuenioService;

@RestController
@RequestMapping("/api/v1/duenios")
public class DuenioController {
    @Autowired
    private DuenioService duenioService;

    @GetMapping
    public List<DuenioDTO> ListarDuenios() {
        return duenioService.obtenerTodos();
    }

    @GetMapping("telefono/{telefono}")
    public List<DuenioDTO> buscarPorTelefono(@PathVariable String telefono) {
        return duenioService.buscarPorTelefono(telefono);
    }

    @GetMapping("nombreMascotas/{nombreMascotas}")
    public List<DuenioDTO> buscarPorNombreMascotas(@PathVariable String nombreMascotas) {
        return duenioService.buscarPorNombreMascota(nombreMascotas);
    }

    @PostMapping
    public ResponseEntity<Duenio> crearDuenio(@RequestBody Duenio duenio) {
        Duenio nuevoDuenio = duenioService.guardar(duenio);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoDuenio);
    }

}
