package com.gestionveterinaria.veterinaria.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    public ResponseEntity<List<DuenioDTO>> ListarDuenios() {
        List<DuenioDTO> duenios = duenioService.obtenerTodos();
        return ResponseEntity.ok(duenios);
    }

    @GetMapping("telefono/{telefono}")
    public ResponseEntity<List<DuenioDTO>> buscarPorTelefono(@PathVariable String telefono) {
        try {
            List<DuenioDTO> duenios = duenioService.buscarPorTelefono(telefono);
            if (duenios.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
            }
            return ResponseEntity.ok(duenios);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("nombreMascotas/{nombreMascotas}")
    public ResponseEntity<List<DuenioDTO>> buscarPorNombreMascotas(@PathVariable String nombreMascotas) {
        try {
            List<DuenioDTO> duenios = duenioService.buscarPorNombreMascota(nombreMascotas);
            if (duenios.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
            }
            return ResponseEntity.ok(duenios);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<DuenioDTO> buscarDuenioPorId(@PathVariable Integer id) {
        try {
            DuenioDTO duenio = duenioService.buscarPorId(id);
            return new  ResponseEntity<>(duenio, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping
    public ResponseEntity<Duenio> crearDuenio(@RequestBody Duenio duenio) {
        Duenio nuevoDuenio = duenioService.guardar(duenio);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoDuenio);
    }
    
    @PatchMapping("/{id}")
    public ResponseEntity<Duenio> editarDuenio(@PathVariable Integer id, @RequestBody Duenio duenio) {
        try {
            Duenio duenioEditado = duenioService.actualizar(id, duenio);
            return ResponseEntity.ok(duenioEditado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarDuenio(@PathVariable Integer id, @RequestBody Duenio duenio) {
        try {
            duenioService.actualizar(id, duenio);
            return ResponseEntity.ok("Dueño actualizado correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Dueño no encontrado");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarDuenio(@PathVariable Integer id) {
        try {
            duenioService.eliminar(id);
            return ResponseEntity.ok("Dueño eliminado correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Dueño no encontrado");
        }
    }

}
