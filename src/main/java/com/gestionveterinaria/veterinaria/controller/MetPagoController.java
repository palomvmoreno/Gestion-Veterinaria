package com.gestionveterinaria.veterinaria.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestionveterinaria.veterinaria.DTO.MetPagoDTO;
import com.gestionveterinaria.veterinaria.model.MetPago;
import com.gestionveterinaria.veterinaria.service.MetPagoService;


@RestController
@RequestMapping("/api/metodos-pago")
public class MetPagoController {

    @Autowired
    private MetPagoService metPagoService; 

    @GetMapping
    public ResponseEntity<List<MetPagoDTO>> listarMetodosPago() {
        List<MetPagoDTO> metodosPago = metPagoService.listarMetodosPago();
        try {
            return ResponseEntity.ok(metodosPago);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<MetPagoDTO> buscarPorId(Integer id) {
        try {
            MetPagoDTO metodoPago = metPagoService.buscarPorId(id);
            return ResponseEntity.ok(metodoPago);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public ResponseEntity<MetPago> agregarMetodoPago(@RequestBody MetPago metPago) {
        try {
            MetPago nuevoMetodoPago = metPagoService.guardarMetodoPago(metPago);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoMetodoPago);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<MetPago> actualizarMetodoPago(Integer id, MetPago metPago) {
        try { 
            MetPago metodoPagoActualizado = metPagoService.actualizar(id, metPago);
            return ResponseEntity.ok(metodoPagoActualizado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarMetodoPago(Integer id) {
        try {
            String mensaje = metPagoService.eliminar(id);
            return ResponseEntity.ok(mensaje);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    
            

    

    


}
