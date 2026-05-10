package com.gestionveterinaria.veterinaria.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestionveterinaria.veterinaria.DTO.InsumoDTO;
import com.gestionveterinaria.veterinaria.model.CategoriaInsumo;
import com.gestionveterinaria.veterinaria.model.Insumo;
import com.gestionveterinaria.veterinaria.model.UnidadMedida;
import com.gestionveterinaria.veterinaria.repository.CategoriaInsumoRepository;
import com.gestionveterinaria.veterinaria.repository.InsumoRepository;
import com.gestionveterinaria.veterinaria.repository.UnidadMedidaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class InsumoService {

    @Autowired
    private InsumoRepository insumoRepository;

    @Autowired
    CategoriaInsumoRepository categoriaRepository;

    @Autowired
    UnidadMedidaRepository unidadMedidaRepository;

    public List<InsumoDTO> listarInsumos(){
        return insumoRepository.findAll().stream()
        .map(this::toDTO).toList();
    }

    public List<InsumoDTO> buscarPorCategoria(String categoria){
        List<Insumo> insumos = insumoRepository.buscarPorCategoria(categoria);

        return insumos.stream()
        .map(this::toDTO)
        .toList();
    }

    public List<InsumoDTO> listarStockBajo(){
        List<Insumo> insumos =  insumoRepository.findBajoStock();

        return insumos.stream()
        .map(this::toDTO)
        .toList();
    }

    public InsumoDTO guardarInsumo(Insumo insumo){
        insumoRepository.save(insumo);
        return toDTO(insumo);
    }

    public InsumoDTO actualizarInsumo(Integer id, InsumoDTO insumoDTO){
        Insumo insumo = insumoRepository.findById(id).orElseThrow(() -> new RuntimeException("No se pudo encontrar el Insumo"));

        CategoriaInsumo categoria = categoriaRepository.buscarPorNombre(insumoDTO.getCategoriaNombre())
        .orElseThrow(() -> new RuntimeException("No se pudo encontrar la categoria"));

        UnidadMedida unidadMedida = unidadMedidaRepository.buscarPorAbreviatura(insumoDTO.getUnidadMedidaAbreviatura())
        .orElseThrow(() -> new RuntimeException("No se pudo encontrar la unidad de medida"));

        insumo.setNombre(insumoDTO.getNombre());
        insumo.setDescripcion(insumoDTO.getDescripcion());

        insumo.setCategoriaInsumo(categoria);
        
        insumo.setUnidadMedida(unidadMedida);

        insumo.setStockActual(insumoDTO.getStockActual());
        insumo.setStockMinimo(insumoDTO.getStockMinimo());

        insumo.setPrecioCompra(insumoDTO.getPrecioCompra());

        return toDTO(insumoRepository.save(insumo));
    }

    public InsumoDTO aumentarStock(Integer id, Integer cantidad){
        Insumo insumo = insumoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Insumo no encontrado"));

        if(cantidad <= 0){
            throw new RuntimeException("La cantidad de stock debe ser mayor a 0");
        }else{
            insumo.setStockActual(insumo.getStockActual() + cantidad);
            return toDTO(insumo);
        }
    }

    public InsumoDTO reducirStock(Integer id, Integer cantidad){
        Insumo insumo = insumoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Insumo no encontrado"));

        if(cantidad<=0){
            throw new RuntimeException("La cantidad de stock debe ser mayor a 0");
        }

        if(insumo.getStockActual() < cantidad){
            throw new RuntimeException("Stock insuficiente");
        }else{
            insumo.setStockActual(insumo.getStockActual()-cantidad);
            return toDTO(insumo);
        }
    }

    public void eliminarInsumo(Integer id){
        Insumo insumo = insumoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Insumo no encontrado"));

        insumoRepository.delete(insumo);
    }

    private InsumoDTO toDTO(Insumo insumo){
        InsumoDTO dto = new InsumoDTO();

        dto.setId(insumo.getId());
        dto.setNombre(insumo.getNombre());
        dto.setDescripcion(insumo.getDescripcion());

        dto.setCategoriaNombre(insumo.getCategoriaInsumo().getCategoria());

        dto.setUnidadMedidaNombre(insumo.getUnidadMedida().getNombre());
        dto.setUnidadMedidaAbreviatura(insumo.getUnidadMedida().getAbreviatura());

        dto.setStockMinimo(insumo.getStockMinimo());
        dto.setStockActual(insumo.getStockActual());

        dto.setPrecioCompra(insumo.getPrecioCompra());

        return dto;
    }
}