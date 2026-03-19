package com.example.demo.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CombinedFieldRequest {

    @NotBlank(message = "Loại sân không được để trống")
    private String fieldType; // FIELD_9 / FIELD_11

    @NotBlank(message = "Tên sân không được để trống")
    private String name;

    private String status; // AVAILABLE / BOOKED / MAINTENANCE — mặc định AVAILABLE nếu không truyền

    @NotNull(message = "Giá thuê không được để trống")
    @Positive(message = "Giá thuê phải lớn hơn 0")
    private Integer pricePerHour;

    @NotNull(message = "Facility ID không được để trống")
    private Integer facilityId;

    @NotEmpty(message = "Danh sách sân đơn không được để trống")
    private List<Integer> singleFieldIds;
}
