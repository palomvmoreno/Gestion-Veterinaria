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

import com.gestionveterinaria.veterinaria.DTO.UnidadMedidaDTO;
import com.gestionveterinaria.veterinaria.model.UnidadMedida;
import com.gestionveterinaria.veterinaria.service.UnidadMedidaService;

@RestController
@RequestMapping("/api/v1/unidadmedida")
public class UnidadMedidaController {
    @Autowired
    private UnidadMedidaService unidadMedidaService;

    @GetMapping
    public ResponseEntity<List<UnidadMedidaDTO>> obtenenrUnidadesMedida(){
        List<UnidadMedidaDTO> unidades = unidadMedidaService.obtenerUnidadMedida();
        if(unidades.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }else{
            return ResponseEntity.ok(unidades);
        }
    }

    @GetMapping("/{abrev}")
    public ResponseEntity<UnidadMedidaDTO> obtenerNombre(@PathVariable String abrev){
        try {
            UnidadMedidaDTO unidad = unidadMedidaService.buscarNombreAbreviatura(abrev);
            return ResponseEntity.ok(unidad);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping
    public ResponseEntity<UnidadMedida> guardarUnidadMedida(@RequestBody UnidadMedida UM){
        try{
            UnidadMedida uni = unidadMedidaService.saveUnimed(UM);
            return ResponseEntity.status(HttpStatus.CREATED).body(uni);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @DeleteMapping("/id")
    public ResponseEntity<String> eliminarUnidadMedida(@PathVariable Integer id){
        try{
            unidadMedidaService.eliminarUnimed(id);
            return ResponseEntity.ok("Unidad Medida eliminada");
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


}