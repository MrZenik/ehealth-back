package com.yevhenberladyniuk.ehealth.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class MedicalRecord {

    @Id
    private Long id;

    private Long patientId;

    private LocalDate addedAt;

    private String comment;

}
