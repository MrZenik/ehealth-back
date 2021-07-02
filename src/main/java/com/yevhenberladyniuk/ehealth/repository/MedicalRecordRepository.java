package com.yevhenberladyniuk.ehealth.repository;

import com.yevhenberladyniuk.ehealth.domain.MedicalRecord;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface MedicalRecordRepository extends CrudRepository<MedicalRecord, Long> {

    boolean existsByPatientId(Long patientId);

    Optional<MedicalRecord> findByIdAndPatientId(Long id, Long patientId);

    List<MedicalRecord> findAllByPatientId(Long patientId);
}
