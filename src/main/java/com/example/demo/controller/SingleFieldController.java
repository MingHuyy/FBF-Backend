package com.example.demo.controller;

import com.example.demo.model.request.SingleFieldRequest;
import com.example.demo.model.response.SingleFieldResponse;
import com.example.demo.service.SingleFieldService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/single-fields")
@RequiredArgsConstructor
public class SingleFieldController {
    
    private final SingleFieldService singleFieldService;
    
    @PostMapping
    public ResponseEntity<SingleFieldResponse> createSingleField(@Valid @RequestBody SingleFieldRequest request) {
        SingleFieldResponse response = singleFieldService.createSingleField(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
    @GetMapping
    public ResponseEntity<List<SingleFieldResponse>> getAllSingleFields() {
        List<SingleFieldResponse> fields = singleFieldService.getAllSingleFields();
        return ResponseEntity.ok(fields);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<SingleFieldResponse> getSingleFieldById(@PathVariable Integer id) {
        SingleFieldResponse response = singleFieldService.getSingleFieldById(id);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/facility/{facilityId}")
    public ResponseEntity<List<SingleFieldResponse>> getSingleFieldsByFacility(@PathVariable Integer facilityId) {
        List<SingleFieldResponse> fields = singleFieldService.getSingleFieldsByFacility(facilityId);
        return ResponseEntity.ok(fields);
    }
    
    @GetMapping("/status/{status}")
    public ResponseEntity<List<SingleFieldResponse>> getSingleFieldsByStatus(@PathVariable String status) {
        List<SingleFieldResponse> fields = singleFieldService.getSingleFieldsByStatus(status);
        return ResponseEntity.ok(fields);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<SingleFieldResponse> updateSingleField(
            @PathVariable Integer id,
            @Valid @RequestBody SingleFieldRequest request) {
        SingleFieldResponse response = singleFieldService.updateSingleField(id, request);
        return ResponseEntity.ok(response);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSingleField(@PathVariable Integer id) {
        singleFieldService.deleteSingleField(id);
        return ResponseEntity.noContent().build();
    }
}
