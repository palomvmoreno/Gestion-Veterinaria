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

import com.gestionveterinaria.veterinaria.DTO.ProcedimientoInsumoDTO;
import com.gestionveterinaria.veterinaria.service.ProcedimientoInsumoService;

@RestController
@RequestMapping("/api/v1/ProcedimientoInsumo")
public class ProcedimientoInsumoController {
    @Autowired
    private ProcedimientoInsumoService procedimientoInsumoService;

    @GetMapping
    public ResponseEntity<List<ProcedimientoInsumoDTO>> listarProcedimientosInsumos(){
        List<ProcedimientoInsumoDTO> procedimientosInsumos = procedimientoInsumoService.listarRelaciones();
        if(procedimientosInsumos.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }else{
            return ResponseEntity.ok(procedimientosInsumos);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProcedimientoInsumoDTO> obtenerProcedimientoInsumo(@PathVariable Integer id){
        try {
            ProcedimientoInsumoDTO procedimientoInsumo = procedimientoInsumoService.buscarPorId(id);
            return ResponseEntity.ok(procedimientoInsumo);
        } catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping
    public ResponseEntity<?> guardarRelacion(@RequestBody ProcedimientoInsumoDTO procedimientoInsumoDTO){
        try{
            ProcedimientoInsumoDTO relacion = procedimientoInsumoService.guardarRelacion(procedimientoInsumoDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(relacion);
        }catch (RuntimeException e){
            if(e.getMessage().contains("No se pudo encontrar")){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
            }else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
            }
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarRelacion(@PathVariable Integer id, @RequestBody ProcedimientoInsumoDTO dto){
        try{
            ProcedimientoInsumoDTO relacion = procedimientoInsumoService.actualizarRelacion(id, dto);
            return ResponseEntity.ok(relacion);
        }catch (RuntimeException e){
            if(e.getMessage().contains("No se pudo encontrar")){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
            }else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
            }
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarRelacion(@PathVariable Integer id){
        try{
            procedimientoInsumoService.eliminarRelacion(id);
            return ResponseEntity.ok("Relacion eliminada");
        } catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
