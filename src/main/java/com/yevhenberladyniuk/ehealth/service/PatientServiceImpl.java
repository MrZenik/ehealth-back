package com.yevhenberladyniuk.ehealth.service;

import com.yevhenberladyniuk.ehealth.domain.Patient;
import com.yevhenberladyniuk.ehealth.dto.PatientDto;
import com.yevhenberladyniuk.ehealth.dto.PatientFormDto;
import com.yevhenberladyniuk.ehealth.exception.PatientException;
import com.yevhenberladyniuk.ehealth.mapper.PatientMapper;
import com.yevhenberladyniuk.ehealth.repository.MedicalRecordRepository;
import com.yevhenberladyniuk.ehealth.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

@Service
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;

    private final MedicalRecordRepository medicalRecordRepository;

    private final PatientMapper patientMapper;

    @Autowired
    public PatientServiceImpl(PatientRepository patientRepository,
                              MedicalRecordRepository medicalRecordRepository,
                              PatientMapper patientMapper) {
        this.patientRepository = patientRepository;
        this.medicalRecordRepository = medicalRecordRepository;
        this.patientMapper = patientMapper;
    }

    @Override
    public List<PatientDto> findAll(@Nullable String name) {
        if (nonNull(name)) {
            return patientRepository.findAllByFullNameContains(name.trim()).stream().map(patientMapper::toDto).collect(Collectors.toList());
        }
        return patientRepository.findAll().stream().map(patientMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public PatientDto findById(Long id) {
        return patientMapper.toDto(findPatientByIdOrThrowException(id));
    }

    @Override
    public PatientDto create(PatientFormDto patientFormDto) {

        Patient patient = Patient.builder()
                .fullName(patientFormDto.getFullName())
                .birthday(patientFormDto.getBirthday())
                .sex(patientFormDto.getSex())
                .country(patientFormDto.getCountry())
                .state(patientFormDto.getState())
                .address(patientFormDto.getAddress())
                .build();

        patient = patientRepository.save(patient);

        return patientMapper.toDto(patient);
    }

    @Override
    public PatientDto update(PatientFormDto patientFormDto, Long id) {

        Patient patient = findPatientByIdOrThrowException(id);

        Patient updatedPatient = patient.toBuilder()
                .fullName(patientFormDto.getFullName())
                .birthday(patientFormDto.getBirthday())
                .sex(patientFormDto.getSex())
                .country(patientFormDto.getCountry())
                .state(patientFormDto.getState())
                .address(patientFormDto.getAddress())
                .build();

        updatedPatient = patientRepository.save(updatedPatient);

        return patientMapper.toDto(updatedPatient);
    }

    @Override
    public void delete(Long id) {

        Patient patient = findPatientByIdOrThrowException(id);

        if (medicalRecordRepository.existsByPatientId(patient.getId())) {
            throw new PatientException("Неможливо видалити пацієнта!");
        }

        patientRepository.deleteById(patient.getId());
    }

    private Patient findPatientByIdOrThrowException(Long id) {
        return patientRepository.findById(id).orElseThrow(() -> new PatientException("Пацієнт не існує!"));
    }
}
