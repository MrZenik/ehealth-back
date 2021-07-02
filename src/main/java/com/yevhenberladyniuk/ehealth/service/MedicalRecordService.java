package com.yevhenberladyniuk.ehealth.service;

import com.yevhenberladyniuk.ehealth.dto.MedicalRecordDto;
import com.yevhenberladyniuk.ehealth.dto.MedicalRecordFormDto;

import java.util.List;

public interface MedicalRecordService {

    List<MedicalRecordDto> findAllByPatientId(Long patientId);

    MedicalRecordDto findById(Long id, Long patientId);

    MedicalRecordDto create(MedicalRecordFormDto medicalRecordFormDto);

    MedicalRecordDto update(Long id, MedicalRecordFormDto medicalRecordFormDto);

    void delete (Long id, Long patientId);

}
