package com.example.oders.controller;

import com.example.oders.common.OrderResponse;
import com.example.oders.dto.OderDTO;
import com.example.oders.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "api/v1/")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/getOders")
    public List<OderDTO> getOders() {
        return orderService.getAllOders();
    }
    @PostMapping("/addOder")
    public OrderResponse addOder(@RequestBody OderDTO oderDTO) {
        return orderService.addOder(oderDTO);
    }

    @PostMapping("/updateOder")
    public OderDTO updateOder(@RequestBody OderDTO oderDTO) {
        return orderService.updateOder(oderDTO);
    }

    @DeleteMapping("/deleteOder/{id}")
    public String deleteOder(@PathVariable Integer id) {
        return orderService.deleteOderbyId(id);
    }
    @GetMapping("/getOderById/{id}")
    public OderDTO getOderById(@PathVariable Integer id) {
        return orderService.getOderById(id);
    }
}
