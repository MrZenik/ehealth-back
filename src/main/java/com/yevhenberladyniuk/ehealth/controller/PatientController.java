package com.yevhenberladyniuk.ehealth.controller;

import com.yevhenberladyniuk.ehealth.dto.PatientDto;
import com.yevhenberladyniuk.ehealth.dto.PatientFormDto;
import com.yevhenberladyniuk.ehealth.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.lang.Nullable;
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
@RequestMapping(value = "/api/patients", produces = MediaType.APPLICATION_JSON_VALUE)
public class PatientController {

    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    public List<PatientDto> findAll(@Nullable @RequestParam(name = "name") String searchByName) {
        return patientService.findAll(searchByName);
    }

    @GetMapping("/{id}")
    public PatientDto findById(@PathVariable Long id) {
        return patientService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PatientDto create(@RequestBody @Validated PatientFormDto patientFormDto) {
        return patientService.create(patientFormDto);
    }

    @PutMapping("/{id}")
    public PatientDto update(@PathVariable Long id, @RequestBody @Validated PatientFormDto patientFormDto) {
        return patientService.update(patientFormDto, id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        patientService.delete(id);
    }

}
