package com.yevhenberladyniuk.ehealth.controller;

import com.yevhenberladyniuk.ehealth.dto.MedicalRecordDto;
import com.yevhenberladyniuk.ehealth.dto.MedicalRecordFormDto;
import com.yevhenberladyniuk.ehealth.service.MedicalRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/medical-records", produces = MediaType.APPLICATION_JSON_VALUE)
public class MedicalRecordController {

    private final MedicalRecordService medicalRecordService;

    @Autowired
    public MedicalRecordController(MedicalRecordService medicalRecordService) {
        this.medicalRecordService = medicalRecordService;
    }

    @GetMapping
    public List<MedicalRecordDto> findAllByPatientId(@RequestParam(required = true) Long patientId) {
        return medicalRecordService.findAllByPatientId(patientId);
    }

    @GetMapping("/{id}")
    public MedicalRecordDto findById(@PathVariable Long id, Long patientId) {
        return medicalRecordService.findById(id, patientId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MedicalRecordDto create(@RequestBody @Validated MedicalRecordFormDto medicalRecordFormDto) {
        return medicalRecordService.create(medicalRecordFormDto);
    }

    @PutMapping("/{id}")
    public MedicalRecordDto update(@PathVariable Long id, @RequestBody @Validated MedicalRecordFormDto medicalRecordFormDto) {
        return medicalRecordService.update(id, medicalRecordFormDto);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id, Long patientId) {
        medicalRecordService.delete(id, patientId);
    }

}
