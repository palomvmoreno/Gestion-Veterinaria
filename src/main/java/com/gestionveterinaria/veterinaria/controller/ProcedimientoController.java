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

import com.gestionveterinaria.veterinaria.DTO.ProcedimientoDTO;
import com.gestionveterinaria.veterinaria.model.Procedimiento;
import com.gestionveterinaria.veterinaria.service.ProcedimientoService;

@RestController
@RequestMapping("/api/v1/Procedimiento")
public class ProcedimientoController {
    @Autowired
    private ProcedimientoService procedimientoService;

    @GetMapping
    public ResponseEntity<List<ProcedimientoDTO>> listarProcedimientos(){
        List<ProcedimientoDTO> procedimientos = procedimientoService.listarProcedimientos();
        if(procedimientos.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }else{
            return ResponseEntity.ok(procedimientos);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProcedimientoDTO> obtenerProcedimiento(@PathVariable Integer id){
        try {
            ProcedimientoDTO procedimiento = procedimientoService.buscarPorId(id);
            return ResponseEntity.ok(procedimiento);
        } catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping
    public ResponseEntity<ProcedimientoDTO> guardarProcedimiento(@RequestBody Procedimiento procedimiento){
        try{
            ProcedimientoDTO procedimiento2 = procedimientoService.guardarProcedimiento(procedimiento);
            return ResponseEntity.status(HttpStatus.CREATED).body(procedimiento2);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarProcedimiento(@PathVariable Integer id, @RequestBody ProcedimientoDTO dto){
        try{
            ProcedimientoDTO procedimiento = procedimientoService.actualizarProcedimiento(id, dto);
            return ResponseEntity.ok(procedimiento);
        }catch (RuntimeException e){
            if(e.getMessage().contains("No se pudo encontrar")){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
            }else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
            }
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarProcedimiento(@PathVariable Integer id){
        try{
            procedimientoService.eliminarProcedimiento(id);
            return ResponseEntity.ok("Procedimiento eliminado");
        } catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}