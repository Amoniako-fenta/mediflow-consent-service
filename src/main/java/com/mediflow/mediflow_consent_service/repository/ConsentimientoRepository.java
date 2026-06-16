package com.mediflow.mediflow_consent_service.repository;

import com.mediflow.mediflow_consent_service.entity.Consentimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsentimientoRepository extends JpaRepository<Consentimiento, Long> {
}