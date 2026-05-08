package com.gestionveterinaria.veterinaria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestionveterinaria.veterinaria.service.InsumoService;

@RestController
@RequestMapping("api/v1/insumos")
public class InsumoController {
    @Autowired
    private InsumoService insumoService;

    //@GetMapping

}
