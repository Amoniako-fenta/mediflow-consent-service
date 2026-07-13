package com.mediflow.mediflow_consent_service.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mediflow.mediflow_consent_service.entity.Consentimiento;
import com.mediflow.mediflow_consent_service.repository.ConsentimientoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ConsentimientoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ConsentimientoRepository repository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void cleanDatabase() {
        repository.deleteAll();
    }

    @Test
    void obtener_CuandoElConsentimientoExiste_DebeRetornarObjeto() throws Exception {
        // Se corrigió el método al español de acuerdo a tu entidad: setAutorizado()
        Consentimiento consent = new Consentimiento();
        consent.setPacienteId(99L);
        consent.setAutorizado(true); 
        repository.save(consent);

        mockMvc.perform(get("/consentimientos/99")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.pacienteId").value(99))
                .andExpect(jsonPath("$.autorizado").value(true));
    }

    @Test
    void obtener_CuandoElConsentimientoNoExiste_DebeRetornar404() throws Exception {
        mockMvc.perform(get("/consentimientos/404")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().string(""));
    }

    @Test
    void actualizar_DebeGuardarORemplazarElConsentimientoEnLaBaseDeDatos() throws Exception {
        Consentimiento nuevoConsent = new Consentimiento();
        nuevoConsent.setAutorizado(false);

        mockMvc.perform(put("/consentimientos/99")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(nuevoConsent)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.pacienteId").value(99))
                .andExpect(jsonPath("$.autorizado").value(false));

        // Verificación directa en base de datos H2 para demostrar la persistencia requerida
        Consentimiento guardado = repository.findById(99L).orElseThrow();
        assertEquals(99L, guardado.getPacienteId());
        assertEquals(false, guardado.getAutorizado());
    }
}