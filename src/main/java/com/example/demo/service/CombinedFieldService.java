package com.example.demo.service;

import com.example.demo.converter.CombinedFieldConverter;
import com.example.demo.entity.CombinedField;
import com.example.demo.entity.CombinedFieldDetail;
import com.example.demo.entity.Facility;
import com.example.demo.entity.SingleField;
import com.example.demo.model.request.CombinedFieldRequest;
import com.example.demo.model.response.CombinedFieldResponse;
import com.example.demo.repository.CombinedFieldRepository;
import com.example.demo.repository.FacilityRepository;
import com.example.demo.repository.SingleFieldRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CombinedFieldService {

    private static final Map<String, Integer> REQUIRED_SINGLE_FIELDS = Map.of(
            "9", 2,
            "11", 4
    );

    private final CombinedFieldRepository combinedFieldRepository;
    private final SingleFieldRepository singleFieldRepository;
    private final FacilityRepository facilityRepository;
    private final CombinedFieldConverter combinedFieldConverter;

    @Transactional
    public CombinedFieldResponse createCombinedField(CombinedFieldRequest request) {
        Facility facility = facilityRepository.findById(request.getFacilityId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy cơ sở với ID: " + request.getFacilityId()));

        List<SingleField> singleFields = validateAndGetSingleFields(request);

        CombinedField combinedField = combinedFieldConverter.toEntity(request, facility);
        CombinedField saved = combinedFieldRepository.save(combinedField);

        List<CombinedFieldDetail> details = singleFields.stream()
                .map(sf -> combinedFieldConverter.toDetailEntity(saved, sf))
                .collect(java.util.stream.Collectors.toCollection(ArrayList::new));

        saved.setDetails(details);

        return combinedFieldConverter.toResponse(combinedFieldRepository.save(saved));
    }

    public List<CombinedFieldResponse> getAllCombinedFields() {
        return combinedFieldRepository.findAll()
                .stream()
                .map(combinedFieldConverter::toResponse)
                .toList();
    }

    public CombinedFieldResponse getCombinedFieldById(Integer id) {
        CombinedField combinedField = combinedFieldRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy sân ghép với ID: " + id));
        return combinedFieldConverter.toResponse(combinedField);
    }

    public List<CombinedFieldResponse> getCombinedFieldsByFacility(Integer facilityId) {
        return combinedFieldRepository.findByFacilityFacilityId(facilityId)
                .stream()
                .map(combinedFieldConverter::toResponse)
                .toList();
    }

    public List<CombinedFieldResponse> getCombinedFieldsByStatus(String status) {
        return combinedFieldRepository.findByStatus(status)
                .stream()
                .map(combinedFieldConverter::toResponse)
                .toList();
    }

    public List<CombinedFieldResponse> getCombinedFieldsByType(String fieldType) {
        return combinedFieldRepository.findByFieldType(fieldType)
                .stream()
                .map(combinedFieldConverter::toResponse)
                .toList();
    }

    @Transactional
    public CombinedFieldResponse updateCombinedField(Integer id, CombinedFieldRequest request) {
        CombinedField combinedField = combinedFieldRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy sân ghép với ID: " + id));

        Facility facility = facilityRepository.findById(request.getFacilityId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy cơ sở với ID: " + request.getFacilityId()));

        List<SingleField> singleFields = validateAndGetSingleFields(request);

        combinedFieldConverter.updateEntity(combinedField, request, facility);

        combinedField.getDetails().clear();
        List<CombinedFieldDetail> newDetails = singleFields.stream()
                .map(sf -> combinedFieldConverter.toDetailEntity(combinedField, sf))
                .collect(java.util.stream.Collectors.toCollection(ArrayList::new));

        combinedField.getDetails().addAll(newDetails);

        return combinedFieldConverter.toResponse(combinedFieldRepository.save(combinedField));
    }

    @Transactional
    public void deleteCombinedField(Integer id) {
        if (!combinedFieldRepository.existsById(id)) {
            throw new RuntimeException("Không tìm thấy sân ghép với ID: " + id);
        }
        combinedFieldRepository.deleteById(id);
    }

    private List<SingleField> validateAndGetSingleFields(CombinedFieldRequest request) {
        String fieldType = request.getFieldType();
        Integer required = REQUIRED_SINGLE_FIELDS.get(fieldType);

        if (required == null) {
            throw new RuntimeException("Loại sân không hợp lệ: " + fieldType + ". Chỉ chấp nhận FIELD_9 hoặc FIELD_11");
        }

        List<Integer> ids = request.getSingleFieldIds();
        if (ids.size() != required) {
            throw new RuntimeException(
                    fieldType + " cần đúng " + required + " sân đơn, nhưng nhận được " + ids.size()
            );
        }

        List<SingleField> singleFields = ids.stream()
                .map(sfId -> singleFieldRepository.findById(sfId)
                        .orElseThrow(() -> new RuntimeException("Không tìm thấy sân đơn với ID: " + sfId)))
                .toList();

        boolean allSameFacility = singleFields.stream()
                .allMatch(sf -> sf.getFacility().getFacilityId().equals(request.getFacilityId()));
        if (!allSameFacility) {
            throw new RuntimeException("Tất cả sân đơn phải thuộc cùng cơ sở với ID: " + request.getFacilityId());
        }

        return singleFields;
    }
}
