package com.mediflow.mediflow_consent_service;

import com.mediflow.mediflow_consent_service.entity.Consentimiento;
import com.mediflow.mediflow_consent_service.repository.ConsentimientoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MediflowConsentServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(
                MediflowConsentServiceApplication.class,
                args
        );
    }

    @Bean
    CommandLineRunner initDatabase(
            ConsentimientoRepository consentimientoRepository
    ) {
        return args -> {
            if (consentimientoRepository.count() == 0) {
                Consentimiento consentimiento = new Consentimiento();

                consentimiento.setPacienteId(1L);
                consentimiento.setAutorizado(true);

                Consentimiento guardado =
                        consentimientoRepository.save(consentimiento);

                System.out.println(
                        "Consentimiento creado para el paciente ID: "
                                + guardado.getPacienteId()
                );
            }
        };
    }
}