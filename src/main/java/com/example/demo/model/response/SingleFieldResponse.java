package com.example.demo.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SingleFieldResponse {
    
    private Integer singleFieldId;
    private String name;
    private String position;
    private String status;
    private Integer pricePerHour;
    private Integer facilityId;
    private String facilityName;
}
