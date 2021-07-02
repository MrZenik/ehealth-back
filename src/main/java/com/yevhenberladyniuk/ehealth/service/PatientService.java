package com.yevhenberladyniuk.ehealth.service;

import com.yevhenberladyniuk.ehealth.dto.PatientDto;
import com.yevhenberladyniuk.ehealth.dto.PatientFormDto;

import java.util.List;

public interface PatientService {

    List<PatientDto> findAll(String name);

    PatientDto findById(Long id);

    PatientDto create(PatientFormDto patientFormDto);

    PatientDto update(PatientFormDto patientFormDto, Long id);

    void delete(Long id);

}
