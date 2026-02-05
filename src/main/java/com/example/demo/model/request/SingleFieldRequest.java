package com.example.demo.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SingleFieldRequest {
    
    @NotBlank(message = "Tên sân không được để trống")
    private String name;
    
    private String position;
    
    @NotBlank(message = "Trạng thái không được để trống")
    private String status; // AVAILABLE / BOOKED / MAINTENANCE
    
    @NotNull(message = "Giá thuê không được để trống")
    @Positive(message = "Giá thuê phải lớn hơn 0")
    private Integer pricePerHour;
    
    @NotNull(message = "Facility ID không được để trống")
    private Integer facilityId;
}
