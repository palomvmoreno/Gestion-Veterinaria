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

import com.gestionveterinaria.veterinaria.DTO.CategoriaInsumoDTO;
import com.gestionveterinaria.veterinaria.model.CategoriaInsumo;
import com.gestionveterinaria.veterinaria.service.CategoriaInsumoService;

@RestController
@RequestMapping("/api/v1/catinsumos")
public class CategoriaInsumoController {
    @Autowired
    private CategoriaInsumoService catService;

    @GetMapping
    public ResponseEntity<List<CategoriaInsumoDTO>> obtenerCategorias(){
        List<CategoriaInsumoDTO> lista = catService.obtenerTodas();
        if(lista.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }else{
            return ResponseEntity.ok(lista);
        }
    }

    @PostMapping
    public ResponseEntity<CategoriaInsumo> guardarCatInsumo(@RequestBody CategoriaInsumo catIns){
        try {
            CategoriaInsumo guardada = catService.guardarCategoria(catIns);
            return new ResponseEntity<>(guardada, HttpStatus.CREATED);
        }
        catch (Exception error) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/id")
    public ResponseEntity<String> eliminarCatIns(@PathVariable Integer id){
        String resultado = catService.eliminar(id);
        if (resultado.contains("Exitosamente")) {
            return new ResponseEntity<>(resultado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(resultado, HttpStatus.NOT_FOUND);
        }
    }
}
