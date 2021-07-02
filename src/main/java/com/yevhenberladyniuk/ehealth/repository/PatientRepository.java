package com.yevhenberladyniuk.ehealth.repository;

import com.yevhenberladyniuk.ehealth.domain.Patient;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PatientRepository extends CrudRepository<Patient, Long> {

    List<Patient> findAll();

    List<Patient> findAllByFullNameContains(String fullName);

}