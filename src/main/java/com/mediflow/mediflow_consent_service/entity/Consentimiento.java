package com.mediflow.mediflow_consent_service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Consentimiento {

    @Id
    private Long pacienteId;

    private Boolean autorizado;
}