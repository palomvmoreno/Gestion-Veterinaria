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

import com.gestionveterinaria.veterinaria.DTO.InsumoDTO;
import com.gestionveterinaria.veterinaria.model.Insumo;
import com.gestionveterinaria.veterinaria.service.InsumoService;

@RestController
@RequestMapping("/api/v1/insumos")
public class InsumoController {
    @Autowired
    private InsumoService insumoService;

    @GetMapping
    public ResponseEntity<List<InsumoDTO>> obtenerInsumos(){
        List<InsumoDTO> insumos = insumoService.listarInsumos();
        if(insumos.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }else{
            return ResponseEntity.ok(insumos);
        }
    }

    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<List<InsumoDTO>> obtenerInsumosCategoria(@PathVariable String categoria){
        try{
            List<InsumoDTO> insumos = insumoService.buscarPorCategoria(categoria);
            if(insumos.isEmpty()){
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
            }else{
                return ResponseEntity.ok(insumos);
            }
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/Stock")
    public ResponseEntity<List<InsumoDTO>> obtenerInsumosBajoStock(){
        List<InsumoDTO> insumos = insumoService.listarStockBajo();
        
        if(insumos.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }else{
            return ResponseEntity.ok(insumos);
        }
    }

    @PostMapping
    public ResponseEntity<InsumoDTO> guardarInsumo(@RequestBody Insumo insumo){
        try{
            InsumoDTO insumo2 = insumoService.guardarInsumo(insumo);
            return ResponseEntity.status(HttpStatus.CREATED).body(insumo2);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarInsumo(@PathVariable Integer id, @RequestBody InsumoDTO dto){
        try{
            InsumoDTO insumo2 = insumoService.actualizarInsumo(id, dto);
            return ResponseEntity.ok(insumo2);
        }catch (RuntimeException e){
            if(e.getMessage().contains("No se pudo encontrar")){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
            }else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
            }
        }
    }

    @PatchMapping("/{id}/aumentar/{cantidad}")
    public ResponseEntity<?> aumentarStock(@PathVariable Integer id, @PathVariable Integer cantidad){
        try{
            InsumoDTO insumo = insumoService.aumentarStock(id, cantidad);
            return ResponseEntity.ok(insumo);
        }catch(RuntimeException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PatchMapping("/{id}/reducir/{cantidad}")
    public ResponseEntity<?> reducirStock(@PathVariable Integer id, @PathVariable Integer cantidad){
        try{
            InsumoDTO insumo = insumoService.reducirStock(id, cantidad);
            return ResponseEntity.ok(insumo);
        }catch(RuntimeException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarInsumo(@PathVariable Integer id){
        try{
            insumoService.eliminarInsumo(id);
            return ResponseEntity.ok("Insumo eliminado");
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
