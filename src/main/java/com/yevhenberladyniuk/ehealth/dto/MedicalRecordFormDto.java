package com.yevhenberladyniuk.ehealth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class MedicalRecordFormDto {

    private Long patientId;

    private String comment;

}
