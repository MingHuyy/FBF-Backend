package com.example.demo.converter;

import com.example.demo.entity.CombinedField;
import com.example.demo.entity.CombinedFieldDetail;
import com.example.demo.entity.Facility;
import com.example.demo.model.request.CombinedFieldRequest;
import com.example.demo.model.response.CombinedFieldResponse;
import com.example.demo.model.response.SingleFieldResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CombinedFieldConverter {

    private final SingleFieldConverter singleFieldConverter;

    public CombinedFieldResponse toResponse(CombinedField combinedField) {
        CombinedFieldResponse response = new CombinedFieldResponse();
        response.setCombinedFieldId(combinedField.getCombinedFieldId());
        response.setFieldType(combinedField.getFieldType());
        response.setName(combinedField.getName());
        response.setStatus(combinedField.getStatus());
        response.setPricePerHour(combinedField.getPricePerHour());
        response.setFacilityId(combinedField.getFacility().getFacilityId());
        response.setFacilityName(combinedField.getFacility().getName());

        List<SingleFieldResponse> singleFields = combinedField.getDetails().stream()
                .map(detail -> singleFieldConverter.toResponse(detail.getSingleField()))
                .toList();
        response.setSingleFields(singleFields);

        return response;
    }

    public CombinedField toEntity(CombinedFieldRequest request, Facility facility) {
        CombinedField combinedField = new CombinedField();
        combinedField.setFieldType(request.getFieldType());
        combinedField.setName(request.getName());
        combinedField.setStatus(request.getStatus());
        combinedField.setPricePerHour(request.getPricePerHour());
        combinedField.setFacility(facility);
        return combinedField;
    }

    public void updateEntity(CombinedField combinedField, CombinedFieldRequest request, Facility facility) {
        combinedField.setFieldType(request.getFieldType());
        combinedField.setName(request.getName());
        combinedField.setStatus(request.getStatus());
        combinedField.setPricePerHour(request.getPricePerHour());
        combinedField.setFacility(facility);
    }

    public CombinedFieldDetail toDetailEntity(CombinedField combinedField,
                                               com.example.demo.entity.SingleField singleField) {
        CombinedFieldDetail detail = new CombinedFieldDetail();
        detail.setCombinedField(combinedField);
        detail.setSingleField(singleField);
        return detail;
    }
}
