package khuong.com.midterm_java.service;


import khuong.com.midterm_java.dto.OrderDTO;
import khuong.com.midterm_java.dto.OrderItemDTO;
import khuong.com.midterm_java.entity.Order;
import khuong.com.midterm_java.entity.OrderItem;
import khuong.com.midterm_java.repository.OrderRepository;
import khuong.com.midterm_java.repository.ProductRepository;
import khuong.com.midterm_java.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderService {
    @Autowired
    private final OrderRepository orderRepository;
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;

    public List<OrderDTO> mapToDto(List<Order> orders) {
        List<OrderDTO> dtos = new ArrayList<>();
        for (Order order : orders) {
            //convert List<OrderItem> to List<OrderItemDTO>
            List<OrderItemDTO> items = order.getItems().stream()
                    .map(item -> new OrderItemDTO(
                            item.getId(),
                            item.getOrder().getId(),
                            item.getProduct().getId(),
                            item.getQuantity()

                    ))
                    .toList();
            dtos.add(new OrderDTO(
                    order.getId(),
                    order.getUser().getId(),
                    order.getOrderDate(),
                    order.getDeliveryAddress(),
                    order.getStatus(),
                    items
            ));
        }
        return dtos;
    }

    public List<OrderDTO> getAll() {
        List<Order> orders = orderRepository.findAll();
        return mapToDto(orders);
    }

    public void update(Long id, OrderDTO dto) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        order.setUser(
                userRepository.findById(dto.getUserId())
                        .orElseThrow(() -> new RuntimeException("User not found"))
        );

        List<OrderItem> items = dto.getItems().stream()
                .map(item -> new OrderItem(
                        item.getId(),
                        order,
                        productRepository.findById(item.getProductId()).get(),
                        item.getQuantity()
                ))
                .toList();
        order.setItems(items);

        orderRepository.save(order);
    }

    public void delete(Long id) {
        orderRepository.deleteById(id);
    }



}
