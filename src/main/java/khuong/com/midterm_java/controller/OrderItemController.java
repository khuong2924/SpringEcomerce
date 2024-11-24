package khuong.com.midterm_java.controller;

import jakarta.websocket.server.PathParam;
import khuong.com.midterm_java.dto.OrderItemDTO;
import khuong.com.midterm_java.dto.ResponseDTO;
import khuong.com.midterm_java.entity.*;
import khuong.com.midterm_java.repository.OrderItemRepository;
import khuong.com.midterm_java.repository.OrderRepository;
import khuong.com.midterm_java.repository.ProductRepository;
import khuong.com.midterm_java.service.OrderItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orderItems")
@RequiredArgsConstructor
public class OrderItemController {
    private final OrderItemService orderItemService;
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public ResponseDTO<List<OrderItemDTO>> getAllOrderItems() {
        ResponseDTO<List<OrderItemDTO>> dto = new ResponseDTO<>();
        dto.setData(orderItemService.getAll());
        dto.setStatus(200);
        return dto;
    }

    @PutMapping
    public ResponseDTO<Void> updateOrderItem(@PathVariable("id") Long id, @RequestBody OrderItemDTO dto) {
        orderItemService.update(id, dto);
        return ResponseDTO.<Void>builder()
                .status(201)
                .message("Sửa thành công order item")
                .build();
    }

    @DeleteMapping("{id}")
    public ResponseDTO<Void> deleteOrderItem(@PathVariable("id") Long id) {
        orderItemService.delete(id);
        return ResponseDTO.<Void>builder()
                .status(201)
                .message("Sửa thành công cart item")
                .build();
    }

    @PostMapping("/upload")
    public ResponseEntity<ResponseDTO<Void>> createOrUpdateCartItem(
            @RequestParam(value = "id", required = false) Long id,
            @RequestParam(value = "order_id") Long orderId,
            @RequestParam(value = "product_id") Long productId,
            @RequestParam(value = "quantity") Integer quantity
    ) {
        OrderItem orderItem;

        if (id != null) {
            orderItem = orderItemRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Order Item not found with id: " + id));
        } else {
            orderItem = new OrderItem();
        }

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + orderId));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + productId));

        orderItem.setOrder(order);
        orderItem.setProduct(product);
        orderItem.setQuantity(quantity);

        orderItemRepository.save(orderItem);

        ResponseDTO<Void> response = new ResponseDTO<>();
        response.setStatus(200);
        response.setMessage(id == null ? "Order Item created successfully" : "Cart Item updated successfully");

        // Trả về kết quả
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }






}







