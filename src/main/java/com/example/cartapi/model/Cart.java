package com.example.cartapi.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //id
    private Long userId; //userId
    private Long productId; //productId
    private int quantity; //quantity
}