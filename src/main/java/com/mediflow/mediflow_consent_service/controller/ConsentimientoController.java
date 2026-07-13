package com.mediflow.mediflow_consent_service.controller;

import com.mediflow.mediflow_consent_service.entity.Consentimiento;
import com.mediflow.mediflow_consent_service.service.ConsentimientoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consentimientos")
public class ConsentimientoController {

    private final ConsentimientoService consentimientoService;

    public ConsentimientoController(
            ConsentimientoService consentimientoService
    ) {
        this.consentimientoService = consentimientoService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Consentimiento> obtener(
            @PathVariable Long id
    ) {
        return consentimientoService
                .obtenerPorPacienteId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Consentimiento> actualizar(
            @PathVariable Long id,
            @RequestBody Consentimiento consentimiento
    ) {
        Consentimiento actualizado =
                consentimientoService.actualizar(id, consentimiento);

        return ResponseEntity.ok(actualizado);
    }
}