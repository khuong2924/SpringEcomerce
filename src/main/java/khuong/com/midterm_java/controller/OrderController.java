package khuong.com.midterm_java.controller;

import khuong.com.midterm_java.dto.OrderDTO;
import khuong.com.midterm_java.dto.OrderItemDTO;
import khuong.com.midterm_java.dto.ResponseDTO;
import khuong.com.midterm_java.entity.Order;
import khuong.com.midterm_java.entity.OrderItem;
import khuong.com.midterm_java.repository.OrderRepository;
import khuong.com.midterm_java.repository.ProductRepository;
import khuong.com.midterm_java.repository.UserRepository;
import khuong.com.midterm_java.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public ResponseDTO<List<OrderDTO>> getOrders() {
        ResponseDTO<List<OrderDTO>> dto = new ResponseDTO<>();
        dto.setData(orderService.getAll());
        dto.setStatus(200);
        return dto;
    }

    @PutMapping("{id}")
    public ResponseDTO<Void> updateOrder(@PathVariable Long id, @RequestBody OrderDTO orderDTO) {
        orderService.update(id, orderDTO);
        return ResponseDTO.<Void>builder()
                .status(201)
                .message("Sửa thành công Order")
                .build();
    }

    @DeleteMapping("id")
    public ResponseDTO<Void> deleteOrder(@PathVariable Long id) {
        orderService.delete(id);
        return ResponseDTO.<Void>builder()
                .status(201)
                .message("Xóa thành công Order")
                .build();
    }

    @PostMapping("/upload")
    public ResponseEntity<ResponseDTO<OrderDTO>> createOrUpdate(
            @RequestParam(value = "id", required = false) Long id,
            @RequestParam(value = "userId") Long userId,
            @RequestBody(required = false) List<OrderItemDTO> items
    ) {
        // Kiểm tra nếu có id, tìm Order nếu tồn tại, ngược lại tạo mới Order
        Order order;
        if (id != null) {
            order = orderRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Order not found"));
        } else {
            order = new Order();
            order.setUser(userRepository.findById(userId)
                    .orElseThrow(() -> new RuntimeException("User not found with id: " + userId)));
        }

        // Xử lý các OrderItemDTO nếu có
        if (items != null) {
            List<OrderItem> orderItems = items.stream().map(dto -> {
                OrderItem orderItem = new OrderItem();
                orderItem.setOrder(order); // Liên kết OrderItem với Order
                orderItem.setProduct(productRepository.findById(dto.getProductId())
                        .orElseThrow(() -> new RuntimeException("Product not found with id: " + dto.getProductId())));
                orderItem.setQuantity(dto.getQuantity());
                return orderItem;
            }).toList();
            order.setItems(orderItems); // Thiết lập danh sách OrderItem cho Order
        } else {
            order.setItems(List.of()); // Nếu không có OrderItemDTO, khởi tạo danh sách rỗng
        }

        orderRepository.save(order);

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(order.getId());
        orderDTO.setUserId(order.getUser().getId());
        orderDTO.setOrderDate(order.getOrderDate());
        orderDTO.setDeliveryAddress(order.getDeliveryAddress());
        orderDTO.setStatus(order.getStatus());

        List<OrderItemDTO> orderItemDTOs = order.getItems().stream().map(orderItem -> {
            return new OrderItemDTO(
                    orderItem.getId(),
                    orderItem.getOrder().getId(),
                    orderItem.getProduct().getId(),
                    orderItem.getQuantity()
            );
        }).toList();

        orderDTO.setItems(orderItemDTOs);

        ResponseDTO<OrderDTO> response = new ResponseDTO<>();
        response.setStatus(200);
        response.setMessage("Order created/updated successfully");
        response.setData(orderDTO);

        return ResponseEntity.ok(response);
    }
}
