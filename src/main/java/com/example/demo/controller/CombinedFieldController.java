package com.example.demo.controller;

import com.example.demo.model.request.CombinedFieldRequest;
import com.example.demo.model.response.CombinedFieldResponse;
import com.example.demo.service.CombinedFieldService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/combined-fields")
@RequiredArgsConstructor
public class CombinedFieldController {

    private final CombinedFieldService combinedFieldService;

    @PostMapping
    public ResponseEntity<CombinedFieldResponse> createCombinedField(@Valid @RequestBody CombinedFieldRequest request) {
        CombinedFieldResponse response = combinedFieldService.createCombinedField(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<CombinedFieldResponse>> getAllCombinedFields() {
        return ResponseEntity.ok(combinedFieldService.getAllCombinedFields());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CombinedFieldResponse> getCombinedFieldById(@PathVariable Integer id) {
        return ResponseEntity.ok(combinedFieldService.getCombinedFieldById(id));
    }

    @GetMapping("/facility/{facilityId}")
    public ResponseEntity<List<CombinedFieldResponse>> getCombinedFieldsByFacility(@PathVariable Integer facilityId) {
        return ResponseEntity.ok(combinedFieldService.getCombinedFieldsByFacility(facilityId));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<CombinedFieldResponse>> getCombinedFieldsByStatus(@PathVariable String status) {
        return ResponseEntity.ok(combinedFieldService.getCombinedFieldsByStatus(status));
    }

    @GetMapping("/type/{fieldType}")
    public ResponseEntity<List<CombinedFieldResponse>> getCombinedFieldsByType(@PathVariable String fieldType) {
        return ResponseEntity.ok(combinedFieldService.getCombinedFieldsByType(fieldType));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CombinedFieldResponse> updateCombinedField(
            @PathVariable Integer id,
            @Valid @RequestBody CombinedFieldRequest request) {
        return ResponseEntity.ok(combinedFieldService.updateCombinedField(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCombinedField(@PathVariable Integer id) {
        combinedFieldService.deleteCombinedField(id);
        return ResponseEntity.noContent().build();
    }
}
