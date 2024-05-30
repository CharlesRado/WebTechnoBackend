package com.project.delicioso.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@Entity
@Table(name="orders", schema="public")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    private String userEmail;

    @ManyToOne
    @JoinColumn(name="restaurant_id")
    private Restaurant restaurant;

    private Timestamp orderTime;

    private Double temperature;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
