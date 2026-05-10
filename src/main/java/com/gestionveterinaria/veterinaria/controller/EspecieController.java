package com.gestionveterinaria.veterinaria.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestionveterinaria.veterinaria.model.Especie;
import com.gestionveterinaria.veterinaria.DTO.EspecieDTO;
import com.gestionveterinaria.veterinaria.service.EspecieService;

@RestController
@RequestMapping("/api/v1/especies")
public class EspecieController {

    @Autowired
    private EspecieService especieService;

    @GetMapping
    public ResponseEntity<List<EspecieDTO>> ListarEspecies() {
        List<EspecieDTO> especies = especieService.listarEspecies();
        return ResponseEntity.ok(especies);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EspecieDTO> buscarPorId(@PathVariable Integer id) {
        try {
            EspecieDTO especie = especieService.buscarPorId(id);
            return new ResponseEntity<>(especie, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping
    public ResponseEntity<Especie> crearEspecie(@RequestBody Especie especie) {
        Especie nuevaEspecie = especieService.guardar(especie);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaEspecie);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Especie> actualizarEspecie(@PathVariable Integer id, @RequestBody Especie especie) {
        Especie actualizada = especieService.actualizar(id, especie);
        if (actualizada != null) {
            return ResponseEntity.ok(actualizada);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarEspecie(@PathVariable Integer id) {
        try {
            String mensaje = especieService.eliminar(id);
            return ResponseEntity.ok("Especie eliminada: " + mensaje);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

}
