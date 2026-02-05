package com.example.demo.service;

import com.example.demo.entity.Facility;
import com.example.demo.entity.SingleField;
import com.example.demo.model.request.SingleFieldRequest;
import com.example.demo.model.response.SingleFieldResponse;
import com.example.demo.repository.FacilityRepository;
import com.example.demo.repository.SingleFieldRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SingleFieldService {
    
    private final SingleFieldRepository singleFieldRepository;
    private final FacilityRepository facilityRepository;
    
    @Transactional
    public SingleFieldResponse createSingleField(SingleFieldRequest request) {
        // Kiểm tra facility có tồn tại không
        Facility facility = facilityRepository.findById(request.getFacilityId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy cơ sở với ID: " + request.getFacilityId()));
        
        // Tạo single field mới
        SingleField singleField = new SingleField();
        singleField.setName(request.getName());
        singleField.setPosition(request.getPosition());
        singleField.setStatus(request.getStatus());
        singleField.setPricePerHour(request.getPricePerHour());
        singleField.setFacility(facility);
        
        SingleField saved = singleFieldRepository.save(singleField);
        
        return mapToResponse(saved);
    }
    
    public List<SingleFieldResponse> getAllSingleFields() {
        return singleFieldRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }
    
    public SingleFieldResponse getSingleFieldById(Integer id) {
        SingleField singleField = singleFieldRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy sân với ID: " + id));
        return mapToResponse(singleField);
    }
    
    public List<SingleFieldResponse> getSingleFieldsByFacility(Integer facilityId) {
        return singleFieldRepository.findByFacilityFacilityId(facilityId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }
    
    public List<SingleFieldResponse> getSingleFieldsByStatus(String status) {
        return singleFieldRepository.findByStatus(status)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }
    
    @Transactional
    public SingleFieldResponse updateSingleField(Integer id, SingleFieldRequest request) {
        SingleField singleField = singleFieldRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy sân với ID: " + id));
        
        Facility facility = facilityRepository.findById(request.getFacilityId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy cơ sở với ID: " + request.getFacilityId()));
        
        singleField.setName(request.getName());
        singleField.setPosition(request.getPosition());
        singleField.setStatus(request.getStatus());
        singleField.setPricePerHour(request.getPricePerHour());
        singleField.setFacility(facility);
        
        SingleField updated = singleFieldRepository.save(singleField);
        
        return mapToResponse(updated);
    }
    
    @Transactional
    public void deleteSingleField(Integer id) {
        if (!singleFieldRepository.existsById(id)) {
            throw new RuntimeException("Không tìm thấy sân với ID: " + id);
        }
        singleFieldRepository.deleteById(id);
    }
    
    private SingleFieldResponse mapToResponse(SingleField singleField) {
        SingleFieldResponse response = new SingleFieldResponse();
        response.setSingleFieldId(singleField.getSingleFieldId());
        response.setName(singleField.getName());
        response.setPosition(singleField.getPosition());
        response.setStatus(singleField.getStatus());
        response.setPricePerHour(singleField.getPricePerHour());
        response.setFacilityId(singleField.getFacility().getFacilityId());
        response.setFacilityName(singleField.getFacility().getName());
        return response;
    }
}
