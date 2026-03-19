package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "combined_field")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CombinedField {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "combined_field_id")
    private Integer combinedFieldId;

    @Column(name = "field_type", length = 20, nullable = false)
    private String fieldType; // FIELD_9 / FIELD_11

    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "status", length = 20)
    private String status; // AVAILABLE / BOOKED / MAINTENANCE

    @Column(name = "price_per_hour")
    private Integer pricePerHour;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "facility_id")
    private Facility facility;

    @OneToMany(mappedBy = "combinedField", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CombinedFieldDetail> details;

    @PrePersist
    protected void onCreate() {
        if (status == null) status = "AVAILABLE";
    }
}
