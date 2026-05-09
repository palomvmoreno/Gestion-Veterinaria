package com.gestionveterinaria.veterinaria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.gestionveterinaria.veterinaria.DTO.MascotaDTO;
import com.gestionveterinaria.veterinaria.model.Mascota;
import com.gestionveterinaria.veterinaria.service.MascotaService;


@RestController
@RequestMapping("/api/v1/mascotas")
public class MascotaController {

    @Autowired
    private MascotaService mascotaService;

    @GetMapping
    public ResponseEntity<List<MascotaDTO>> ListarMascotas() {
        List<MascotaDTO> mascotas = mascotaService.listarMascotas();
        return ResponseEntity.ok(mascotas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MascotaDTO> buscarPorId(@PathVariable Integer id) {
        try {
            MascotaDTO mascota = mascotaService.buscarPorId(id);
            return new  ResponseEntity<>(mascota, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }


    @PostMapping
    public ResponseEntity<Mascota> agregarMascota(@RequestBody Mascota mascota) {
        try {
            Mascota nuevaMascota = mascotaService.guardar(mascota);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevaMascota);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Mascota> actualizarMascota(@PathVariable Integer id, @RequestBody Mascota mascota) {
        try {
            Mascota actualizada = mascotaService.actualizar(id, mascota);
            if (actualizada != null) {
                return ResponseEntity.ok(actualizada);
            }
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarMascota(@PathVariable Integer id) {
        String resultado = mascotaService.eliminar(id);
        if (resultado.contains("Eliminado exitosamente")) {
            return new ResponseEntity<>(resultado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(resultado, HttpStatus.NOT_FOUND);
        }
    }




}
