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
public class Patient {

    @Id
    private Long id;

    private String fullName;

    private LocalDate birthday;

    private String sex;

    private String country;

    private String state;

    private String address;

}
