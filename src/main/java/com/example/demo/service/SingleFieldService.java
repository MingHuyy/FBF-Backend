package com.example.demo.service;

import com.example.demo.converter.SingleFieldConverter;
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
    private final SingleFieldConverter singleFieldConverter;

    @Transactional
    public SingleFieldResponse createSingleField(SingleFieldRequest request) {
        Facility facility = facilityRepository.findById(request.getFacilityId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy cơ sở với ID: " + request.getFacilityId()));

        SingleField singleField = singleFieldConverter.toEntity(request, facility);
        SingleField saved = singleFieldRepository.save(singleField);

        return singleFieldConverter.toResponse(saved);
    }

    public List<SingleFieldResponse> getAllSingleFields() {
        return singleFieldRepository.findAll()
                .stream()
                .map(singleFieldConverter::toResponse)
                .collect(Collectors.toList());
    }

    public SingleFieldResponse getSingleFieldById(Integer id) {
        SingleField singleField = singleFieldRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy sân với ID: " + id));
        return singleFieldConverter.toResponse(singleField);
    }

    public List<SingleFieldResponse> getSingleFieldsByFacility(Integer facilityId) {
        return singleFieldRepository.findByFacilityFacilityId(facilityId)
                .stream()
                .map(singleFieldConverter::toResponse)
                .collect(Collectors.toList());
    }

    public List<SingleFieldResponse> getSingleFieldsByStatus(String status) {
        return singleFieldRepository.findByStatus(status)
                .stream()
                .map(singleFieldConverter::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public SingleFieldResponse updateSingleField(Integer id, SingleFieldRequest request) {
        SingleField singleField = singleFieldRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy sân với ID: " + id));

        Facility facility = facilityRepository.findById(request.getFacilityId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy cơ sở với ID: " + request.getFacilityId()));

        singleFieldConverter.updateEntity(singleField, request, facility);
        SingleField updated = singleFieldRepository.save(singleField);

        return singleFieldConverter.toResponse(updated);
    }

    @Transactional
    public void deleteSingleField(Integer id) {
        if (!singleFieldRepository.existsById(id)) {
            throw new RuntimeException("Không tìm thấy sân với ID: " + id);
        }
        singleFieldRepository.deleteById(id);
    }
}
