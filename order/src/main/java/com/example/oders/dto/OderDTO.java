package com.example.oders.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OderDTO {
    private int id;
    private int itemId;
    private String orderDate;
    private int amount;
}
