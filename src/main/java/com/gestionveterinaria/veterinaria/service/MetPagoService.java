package com.gestionveterinaria.veterinaria.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestionveterinaria.veterinaria.DTO.MetPagoDTO;
import com.gestionveterinaria.veterinaria.model.MetPago;
import com.gestionveterinaria.veterinaria.repository.MetPagoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class MetPagoService {

    @Autowired
    private MetPagoRepository metPagoRepository;

    public List<MetPagoDTO> listarMetodosPago() {
        return metPagoRepository.findAll().stream()
                .map(this::convertirADTO)
                .toList();
    }

    public MetPagoDTO buscarPorId(Integer id) {
        MetPago metPago = metPagoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Método de pago no encontrado"));
        return convertirADTO(metPago);
    }

    public MetPago guardarMetodoPago(MetPago metPago) {
        return metPagoRepository.save(metPago);
    }

    public MetPago actualizar(Integer id, MetPago metPagoDetalles) {
        return metPagoRepository.findById(id).map(metPago -> {
            metPago.setNombre(metPagoDetalles.getNombre());
            metPago.setDescripcion(metPagoDetalles.getDescripcion());
            metPago.setRut(metPagoDetalles.getRut());
            return metPagoRepository.save(metPago);
        }).orElseThrow(() -> new RuntimeException("Método de pago no encontrado"));
    }

    public String eliminar(Integer id) {
        try {
            MetPago metPago = metPagoRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Método de pago no encontrado"));
            metPagoRepository.delete(metPago);
            return "El método de pago '" + metPago.getNombre() + "' ha sido eliminado exitosamente.";
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }

    
    public MetPagoDTO convertirADTO(MetPago metPago){
        MetPagoDTO dto = new MetPagoDTO();
        dto.setId(metPago.getId());
        dto.setNombre(metPago.getNombre());
        dto.setDescripcion(metPago.getDescripcion());
        dto.setRut(metPago.getRut());
        return dto;
    }
    
    

}
