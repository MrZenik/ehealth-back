package com.yevhenberladyniuk.ehealth.mapper;

import com.yevhenberladyniuk.ehealth.domain.MedicalRecord;
import com.yevhenberladyniuk.ehealth.dto.MedicalRecordDto;
import org.springframework.stereotype.Component;

@Component
public class MedicalRecordMapper {

    public MedicalRecordDto toDto(MedicalRecord medicalRecord) {
        return MedicalRecordDto.builder()
                .id(medicalRecord.getId())
                .patientId(medicalRecord.getPatientId())
                .addedAt(medicalRecord.getAddedAt())
                .comment(medicalRecord.getComment())
                .build();
    }

}
