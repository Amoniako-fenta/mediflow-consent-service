package com.mediflow.mediflow_consent_service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Consentimiento {
    @Id
    private Long pacienteId; // Usamos el ID del paciente como clave primaria directamente
    private Boolean autorizado;
}