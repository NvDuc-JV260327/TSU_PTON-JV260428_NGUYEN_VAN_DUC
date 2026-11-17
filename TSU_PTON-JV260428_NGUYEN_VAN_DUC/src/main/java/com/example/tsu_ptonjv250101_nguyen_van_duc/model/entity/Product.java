package com.example.tsu_ptonjv250101_nguyen_van_duc.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_name", columnDefinition = "VARCHAR(100)", nullable = false, unique = true)
    private String productName;

    @Column(columnDefinition = "DOUBLE CHECK(price > 0)", nullable = false)
    private double price;

    @Column(name = "currency_unit", columnDefinition = "VARCHAR(10)", nullable = false)
    private String currencyUnit;

    @Column(name = "import_date", nullable = false)
    private LocalDate importDate;

    @Column(name = "warranty_period", nullable = false)
    private int warrantyPeriod;

    @Column(name = "warranty_unit", nullable = false)
    private String warrantyUnit;

    @Column(name = "product_image", columnDefinition = "VARCHAR(255)")
    private String productImage;

    @Column(nullable = false)
    private String description;

    private Byte status;
}
