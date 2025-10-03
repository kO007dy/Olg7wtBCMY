// 代码生成时间: 2025-10-03 20:50:45
package com.example.clinicaltrial;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import java.util.List;
import java.util.Optional;

@Service
public class ClinicalTrialService {

    // Autowiring the ClinicalTrialRepository to allow data access
    @Autowired
    private ClinicalTrialRepository clinicalTrialRepository;

    // Method to get all clinical trials
    @GetMapping("/clinicaltrials")
    public ResponseEntity<List<ClinicalTrial>> getAllClinicalTrials() {
        return new ResponseEntity<>(clinicalTrialRepository.findAll(), HttpStatus.OK);
    }

    // Method to get a clinical trial by ID
    @GetMapping("/clinicaltrials/{id}")
    public ResponseEntity<ClinicalTrial> getClinicalTrialById(@PathVariable Long id) {
        Optional<ClinicalTrial> trial = clinicalTrialRepository.findById(id);
        if (trial.isPresent()) {
            return ResponseEntity.ok(trial.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Method to create a new clinical trial
    @PostMapping("/clinicaltrials")
    public ResponseEntity<ClinicalTrial> createClinicalTrial(@RequestBody ClinicalTrial clinicalTrial) {
        ClinicalTrial newTrial = clinicalTrialRepository.save(clinicalTrial);
        return new ResponseEntity<>(newTrial, HttpStatus.CREATED);
    }

    // Method to update an existing clinical trial
    @PutMapping("/clinicaltrials/{id}")
    public ResponseEntity<ClinicalTrial> updateClinicalTrial(@PathVariable Long id, @RequestBody ClinicalTrial clinicalTrialDetails) {
        Optional<ClinicalTrial> trial = clinicalTrialRepository.findById(id);
        if (trial.isPresent()) {
            ClinicalTrial updatedTrial = new ClinicalTrial(
                trial.get().getId(),
                clinicalTrialDetails.getName(),
                clinicalTrialDetails.getDescription(),
                clinicalTrialDetails.getStatus()
            );
            return ResponseEntity.ok(clinicalTrialRepository.save(updatedTrial));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Method to delete a clinical trial by ID
    @DeleteMapping("/clinicaltrials/{id}")
    public ResponseEntity<?> deleteClinicalTrial(@PathVariable Long id) {
        Optional<ClinicalTrial> trial = clinicalTrialRepository.findById(id);
        if (trial.isPresent()) {
            clinicalTrialRepository.delete(trial.get());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
