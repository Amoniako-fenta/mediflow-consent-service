package com.mediflow.mediflow_consent_service.controller;

import com.mediflow.mediflow_consent_service.entity.Consentimiento;
import com.mediflow.mediflow_consent_service.repository.ConsentimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsentimientoController {

    @Autowired
    private ConsentimientoRepository repository;

    @GetMapping("/consentimientos/{id}")
    public Consentimiento obtener(@PathVariable Long id) {
        return repository.findById(id).orElse(new Consentimiento()); // Retorna vacío si no existe
    }

    @PutMapping("/consentimientos/{id}")
    public Consentimiento actualizar(@PathVariable Long id, @RequestBody Consentimiento nuevo) {
        nuevo.setPacienteId(id);
        return repository.save(nuevo);
    }
}