package com.project.delicioso.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name="products", schema="public")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    private String productName;

    private String description;

    @ManyToOne
    @JoinColumn(name="restaurant_id")
    private Restaurant restaurant;

    private Double price;
}
