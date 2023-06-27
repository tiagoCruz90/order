package com.tutorial.order.service;

import com.tutorial.order.domain.Order;
import com.tutorial.order.dto.OrderRequestDTO;

import java.util.List;
import java.util.UUID;

public interface OrderService {

    void placeOrder(OrderRequestDTO orderRequestDTO);

    List<OrderRequestDTO> getAllOrders();
}
