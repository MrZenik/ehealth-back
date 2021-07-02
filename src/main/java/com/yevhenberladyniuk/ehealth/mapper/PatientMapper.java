package com.yevhenberladyniuk.ehealth.mapper;

import com.yevhenberladyniuk.ehealth.domain.Patient;
import com.yevhenberladyniuk.ehealth.dto.PatientDto;
import org.springframework.stereotype.Component;

@Component
public class PatientMapper {

    public PatientDto toDto(Patient patient) {
        return PatientDto.builder()
                .id(patient.getId())
                .fullName(patient.getFullName())
                .birthday(patient.getBirthday())
                .sex(patient.getSex())
                .country(patient.getCountry())
                .state(patient.getState())
                .address(patient.getAddress())
                .build();
    }

}
