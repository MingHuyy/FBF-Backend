package com.example.demo.converter;

import com.example.demo.entity.Facility;
import com.example.demo.entity.SingleField;
import com.example.demo.model.request.SingleFieldRequest;
import com.example.demo.model.response.SingleFieldResponse;
import org.springframework.stereotype.Component;

@Component
public class SingleFieldConverter {

    public SingleFieldResponse toResponse(SingleField singleField) {
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

    public SingleField toEntity(SingleFieldRequest request, Facility facility) {
        SingleField singleField = new SingleField();
        singleField.setName(request.getName());
        singleField.setPosition(request.getPosition());
        singleField.setStatus(request.getStatus());
        singleField.setPricePerHour(request.getPricePerHour());
        singleField.setFacility(facility);
        return singleField;
    }

    public void updateEntity(SingleField singleField, SingleFieldRequest request, Facility facility) {
        singleField.setName(request.getName());
        singleField.setPosition(request.getPosition());
        singleField.setStatus(request.getStatus());
        singleField.setPricePerHour(request.getPricePerHour());
        singleField.setFacility(facility);
    }
}
