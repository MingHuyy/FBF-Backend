package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "combined_field_detail")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CombinedFieldDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer combinedFieldDetailId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "combined_field_id", nullable = false)
    private CombinedField combinedField;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "single_field_id", nullable = false)
    private SingleField singleField;
}
