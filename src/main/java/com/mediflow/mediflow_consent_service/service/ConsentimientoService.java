package com.mediflow.mediflow_consent_service.service;

import com.mediflow.mediflow_consent_service.entity.Consentimiento;
import com.mediflow.mediflow_consent_service.repository.ConsentimientoRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ConsentimientoService {

    private final ConsentimientoRepository consentimientoRepository;

    public ConsentimientoService(
            ConsentimientoRepository consentimientoRepository
    ) {
        this.consentimientoRepository = consentimientoRepository;
    }

    public Optional<Consentimiento> obtenerPorPacienteId(Long pacienteId) {
        return consentimientoRepository.findById(pacienteId);
    }

    public Consentimiento actualizar(
            Long pacienteId,
            Consentimiento consentimiento
    ) {
        consentimiento.setPacienteId(pacienteId);

        if (consentimiento.getAutorizado() == null) {
            consentimiento.setAutorizado(false);
        }

        return consentimientoRepository.save(consentimiento);
    }
}