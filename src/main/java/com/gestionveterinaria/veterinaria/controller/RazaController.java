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

import com.gestionveterinaria.veterinaria.DTO.RazaDTO;
import com.gestionveterinaria.veterinaria.model.Raza;
import com.gestionveterinaria.veterinaria.service.RazaService;

@RestController
@RequestMapping("/api/v1/razas")
public class RazaController {
    @Autowired
    private RazaService razaService;

    @GetMapping
    public ResponseEntity<List<RazaDTO>> ListarRazas() {
        List<RazaDTO> razas = razaService.listaRazas();
        if (razas.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(razas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RazaDTO> buscarPorId(@PathVariable Integer id) {
        try {
            RazaDTO raza = razaService.buscarPorId(id);
            return ResponseEntity.ok(raza);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public ResponseEntity<Raza> agregarRaza(@RequestBody Raza raza) {
        try {
            Raza nuevaRaza = razaService.guardar(raza);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevaRaza);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Raza> actualizarRaza(@PathVariable Integer id, @RequestBody Raza raza) {
        try {
            Raza actualizada = razaService.actualizar(id, raza);
            if (actualizada != null) {
                return ResponseEntity.ok(actualizada);
            }
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarRaza(@PathVariable Integer id) {
        try{
            razaService.eliminar(id);
            return ResponseEntity.ok("Raza eliminada");
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }




}
