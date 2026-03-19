package com.example.demo.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CombinedFieldResponse {

    private Integer combinedFieldId;
    private String fieldType;
    private String name;
    private String status;
    private Integer pricePerHour;
    private Integer facilityId;
    private String facilityName;
    private List<SingleFieldResponse> singleFields;
}
