package com.yevhenberladyniuk.ehealth.service;

import com.yevhenberladyniuk.ehealth.domain.MedicalRecord;
import com.yevhenberladyniuk.ehealth.dto.MedicalRecordDto;
import com.yevhenberladyniuk.ehealth.dto.MedicalRecordFormDto;
import com.yevhenberladyniuk.ehealth.exception.MedicalRecordException;
import com.yevhenberladyniuk.ehealth.mapper.MedicalRecordMapper;
import com.yevhenberladyniuk.ehealth.repository.MedicalRecordRepository;
import com.yevhenberladyniuk.ehealth.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MedicalRecordServiceImpl implements MedicalRecordService{

    private final MedicalRecordRepository medicalRecordRepository;

    private final MedicalRecordMapper medicalRecordMapper;

    private final PatientRepository patientRepository;

    @Autowired
    public MedicalRecordServiceImpl(MedicalRecordRepository medicalRecordRepository, MedicalRecordMapper medicalRecordMapper, PatientRepository patientRepository) {
        this.medicalRecordRepository = medicalRecordRepository;
        this.medicalRecordMapper = medicalRecordMapper;
        this.patientRepository = patientRepository;
    }

    @Override
    public List<MedicalRecordDto> findAllByPatientId(Long patientId) {
        return medicalRecordRepository.findAllByPatientId(patientId).stream().map(medicalRecordMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public MedicalRecordDto findById(Long id, Long patientId) {
        return medicalRecordMapper.toDto(findMedicalRecordOrThrowException(id, patientId));
    }

    @Override
    public MedicalRecordDto create(MedicalRecordFormDto medicalRecordFormDto) {

        if(!patientRepository.existsById(medicalRecordFormDto.getPatientId())){
            throw new MedicalRecordException("Такий пацієнт не існує!");
        };

        MedicalRecord medicalRecord = MedicalRecord.builder()
                .patientId(medicalRecordFormDto.getPatientId())
                .addedAt(LocalDate.now())
                .comment(medicalRecordFormDto.getComment())
                .build();

        medicalRecord = medicalRecordRepository.save(medicalRecord);

        return medicalRecordMapper.toDto(medicalRecord);
    }

    @Override
    public MedicalRecordDto update(Long id, MedicalRecordFormDto medicalRecordFormDto) {

        MedicalRecord medicalRecord = findMedicalRecordOrThrowException(id, medicalRecordFormDto.getPatientId());

        MedicalRecord updatedMedicalRecord = medicalRecord.toBuilder()
                .comment(medicalRecordFormDto.getComment())
                .build();

        updatedMedicalRecord = medicalRecordRepository.save(updatedMedicalRecord);

        return medicalRecordMapper.toDto(updatedMedicalRecord);
    }

    @Override
    public void delete(Long id, Long patientId) {
        findMedicalRecordOrThrowException(id, patientId);
        medicalRecordRepository.deleteById(id);
    }

    private MedicalRecord findMedicalRecordOrThrowException(Long id, Long patientId){
        return medicalRecordRepository.findByIdAndPatientId(id, patientId)
                .orElseThrow(() -> new MedicalRecordException("Patient not found!"));
    }

}
