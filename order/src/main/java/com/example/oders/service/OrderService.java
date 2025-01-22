package com.example.oders.service;

import com.example.inventory.dto.InventoryDTO;
import com.example.oders.common.ErrorOrderResponse;
import com.example.oders.common.OrderResponse;
import com.example.oders.common.SuccessOrderResponse;
import com.example.oders.dto.OderDTO;
import com.example.oders.model.Order;
import com.example.oders.repository.OrderRepository;
import com.example.product.dto.ProductDTO;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.List;

@Service
@Transactional
public class OrderService {
    private final WebClient inventoryWebClient;
    private final WebClient productWebclient;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ModelMapper modelMapper;

    public OrderService(WebClient inventoryWebClient, WebClient productWebClient,OrderRepository orderRepository,ModelMapper modelMapper){
        this.inventoryWebClient = inventoryWebClient;
        this.productWebclient = productWebClient;
        this.orderRepository= orderRepository;
        this.modelMapper= modelMapper;
    }

    public List<OderDTO> getAllOders(){

        List<Order> orderList = orderRepository.findAll();
        return modelMapper.map(orderList, new TypeToken<List<OderDTO>>() {}.getType());
    }
    public OrderResponse addOder(OderDTO oderDTO){
        Integer itemId = oderDTO.getItemId();

        try{
            InventoryDTO inventoryResponse = inventoryWebClient.get()
                    .uri(uriBuilder -> uriBuilder.path("/getInventoriesById/{itemId}").build(itemId))
                    .retrieve()
                    .bodyToMono(InventoryDTO.class)
                    .block();


            assert inventoryResponse != null;

            Integer productId = inventoryResponse.getProductId();

            ProductDTO productResponse = productWebclient.get()
                    .uri(uriBuilder -> uriBuilder.path("/getProductById/{productId}").build(productId))
                    .retrieve()
                    .bodyToMono(ProductDTO.class)
                    .block();
            assert productResponse != null;

            if(inventoryResponse.getQuantity() >0){
                if(productResponse.getForSale() == 1 ){
                    orderRepository.save(modelMapper.map(oderDTO, Order.class));
                }
                else{
                    return new ErrorOrderResponse("Item not for sale,please try later");
                }
                return new SuccessOrderResponse(oderDTO);
            }
            else{
                return new ErrorOrderResponse("Item not available,please try later");
            }
        }
        catch (WebClientResponseException e){
            if(e.getStatusCode().is5xxServerError()){
                return new ErrorOrderResponse("Item not found,please try later");
            }
        }


        return null;
    }
    public OderDTO updateOder(OderDTO odertDTO){
        orderRepository.save(modelMapper.map(odertDTO, Order.class));
        return odertDTO;
    }
    public String deleteOderbyId(Integer id){
        orderRepository.deleteById(id);
        return "Oder deleted";
    }
    public OderDTO getOderById(Integer id){
        Order order = orderRepository.getOrderById(id);
        return modelMapper.map(order, OderDTO.class);
    }
}
