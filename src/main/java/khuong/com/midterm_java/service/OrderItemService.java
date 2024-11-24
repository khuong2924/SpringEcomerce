package khuong.com.midterm_java.service;

import khuong.com.midterm_java.dto.CartItemDTO;
import khuong.com.midterm_java.dto.OrderDTO;
import khuong.com.midterm_java.dto.OrderItemDTO;
import khuong.com.midterm_java.entity.Order;
import khuong.com.midterm_java.entity.OrderItem;
import khuong.com.midterm_java.repository.OrderItemRepository;
import khuong.com.midterm_java.repository.OrderRepository;
import khuong.com.midterm_java.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@RequestMapping("/orderItems")
public class OrderItemService {
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductRepository productRepository;

    public List<OrderItemDTO> mapToDto(List<OrderItem> orderItems) {
        List<OrderItemDTO> dtos = new ArrayList<>();
        for (OrderItem orderItem : orderItems) {
            dtos.add(new OrderItemDTO(
                    orderItem.getId(),
                    orderItem.getOrder().getId(),
                    orderItem.getProduct().getId(),
                    orderItem.getQuantity()
            ));
        }
        return dtos;
    }

    public List<OrderItemDTO> getAll() {
        List<OrderItem> orderItems = orderItemRepository.findAll();
        return mapToDto(orderItems);
    }

    public void update(Long id, OrderItemDTO orderItemDTO) {
        OrderItem orderItem = orderItemRepository.findById(id).get();
        orderItem.setOrder(orderRepository.findById(orderItemDTO.getOrderId()).get());
        orderItem.setProduct(productRepository.findById(orderItemDTO.getProductId()).get());
        orderItem.setQuantity(orderItemDTO.getQuantity());
        orderItemRepository.save(orderItem);
    }

    public void delete(Long id) {
        orderItemRepository.deleteById(id);
    }
}
