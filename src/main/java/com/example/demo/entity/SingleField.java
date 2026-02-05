package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "single_field")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SingleField {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "single_field_id")
    private Integer singleFieldId;
    
    @Column(name = "name", length = 50)
    private String name;
    
    @Column(name = "position", length = 100)
    private String position;
    
    @Column(name = "status", length = 20)
    private String status; // AVAILABLE / BOOKED / MAINTENANCE
    
    @Column(name = "price_per_hour")
    private Integer pricePerHour;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "facility_id")
    private Facility facility;
}
