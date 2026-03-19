package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "facility")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Facility {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "facility_id")
    private Integer facilityId;
    
    @Column(name = "name", length = 100)
    private String name;
    
    @Column(name = "address", length = 255)
    private String address;
    
    @Column(name = "area", length = 100)
    private String area;
    
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;
    
    @OneToMany(mappedBy = "facility", cascade = CascadeType.ALL)
    private List<SingleField> singleFields;
}
