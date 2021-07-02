package com.yevhenberladyniuk.ehealth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class MedicalRecordDto {

    private Long id;

    private Long patientId;

    private LocalDate addedAt;

    private String comment;

}
