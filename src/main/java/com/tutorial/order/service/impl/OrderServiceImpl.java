package com.tutorial.order.service.impl;

import com.tutorial.order.domain.Order;
import com.tutorial.order.domain.OrderLineItems;
import com.tutorial.order.dto.OrderLineItemsDTO;
import com.tutorial.order.dto.OrderRequestDTO;
import com.tutorial.order.repository.OrderRepository;
import com.tutorial.order.service.OrderService;

import java.util.List;
import java.util.UUID;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public void placeOrder(OrderRequestDTO orderRequestDTO) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItems> orderLineItemsList = orderRequestDTO.getOrderLineItemsDTOList()
                .stream()
                .map(this::mapToDTO)
                .toList();

        order.setOrderLineItemsList(orderLineItemsList);
        orderRepository.save(order);
    }

    @Override
    public List<OrderRequestDTO> getAllOrders() {
        List<OrderRequestDTO> orderList = orderRepository.findAll();

        return orderList;
    }

    private OrderLineItems mapToDTO(OrderLineItemsDTO orderLineItemsDTO) {
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setSkuCode(orderLineItemsDTO.getSkuCode());
        orderLineItems.setQuantity(orderLineItemsDTO.getQuantity());
        orderLineItems.setPrice(orderLineItemsDTO.getPrice());
        return orderLineItems;
    }

    //Do a mapping from Order to OrderRequestDTO
    private OrderRequestDTO mapToDTO2(Order order) {
        OrderRequestDTO orderRequestDTO = new OrderRequestDTO();
        orderRequestDTO.setOrderNumber(order.getOrderNumber());
        orderRequestDTO.setOrderLineItemsDTOList(order.getOrderLineItemsList()
                .stream()
                .map(this::mapToDTO)
                .toList());
        return orderRequestDTO;
    }
}
