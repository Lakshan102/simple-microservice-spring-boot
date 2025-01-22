package com.example.oders.common;

import com.example.oders.dto.OderDTO;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.Getter;

@Getter
public class SuccessOrderResponse implements OrderResponse{
    @JsonUnwrapped
    private final OderDTO order;

    public SuccessOrderResponse(OderDTO order) {
        this.order = order;
    }
}
