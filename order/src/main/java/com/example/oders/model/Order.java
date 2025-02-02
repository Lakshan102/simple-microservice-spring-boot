package com.example.oders.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "`order`")
public class Order {
    @Id
    private int id;
    private int itemId;
    private String orderDate;
    private int amount;
}
