package khuong.com.midterm_java.controller;

import khuong.com.midterm_java.config.GlobalData;
import khuong.com.midterm_java.dto.CartDTO;
import khuong.com.midterm_java.dto.CartItemDTO;
import khuong.com.midterm_java.dto.ResponseDTO;
import khuong.com.midterm_java.entity.Cart;
import khuong.com.midterm_java.entity.CartItem;
import khuong.com.midterm_java.entity.User;
import khuong.com.midterm_java.repository.CartRepository;
import khuong.com.midterm_java.repository.ProductRepository;
import khuong.com.midterm_java.repository.UserRepository;
import khuong.com.midterm_java.service.CartService;
import khuong.com.midterm_java.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/carts")
public class CartController {
    @Autowired
    private final ProductService productService;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CartService cartService;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public ResponseDTO<List<CartDTO>> getCarts() {
        ResponseDTO<List<CartDTO>> dto = new ResponseDTO<>();
        dto.setData(cartService.getAll());
        dto.setStatus(200);
        return dto;
    }

    @PutMapping("{id}")
    public ResponseDTO<Void> update(@PathVariable Long id, @RequestBody CartDTO cartDTO) {
        cartService.update(id, cartDTO);
        return ResponseDTO.<Void>builder()
                .status(201)
                .message("Sửa thành công Cart")
                .build();
    }

    @DeleteMapping("{id}")
    public ResponseDTO<Void> delete(@PathVariable Long id) {
        cartService.delete(id);
        return ResponseDTO.<Void>builder()
                .status(201)
                .message("Xóa thành công Cart")
                .build();
    }

    @PostMapping("/upload")
    public ResponseEntity<ResponseDTO<Cart>> createOrUpdateCart(
            @RequestParam(value = "id", required = false) Long id,
            @RequestParam(value = "userId") Long userId,
            @RequestBody(required = false) List<CartItemDTO> cartItemDTOs) {

        Cart cart;

        // Nếu ID được truyền, tìm kiếm Cart theo ID, nếu không tạo mới
        if (id != null) {
            cart = cartRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Cart not found with id: " + id));
        } else {
            cart = new Cart();
            cart.setUser(userRepository.findById(userId)
                    .orElseThrow(() -> new RuntimeException("User not found with id: " + userId)));
        }

        // Xử lý danh sách CartItemDTO (nếu có)
        if (cartItemDTOs != null && !cartItemDTOs.isEmpty()) {
            List<CartItem> cartItems = cartItemDTOs.stream().map(dto -> {
                CartItem item = new CartItem();
                item.setProduct(productRepository.findById(dto.getProductId())
                        .orElseThrow(() -> new RuntimeException("Product not found with id: " + dto.getProductId())));
                item.setQuantity(dto.getQuantity());
                item.setCart(cart); // Liên kết CartItem với Cart
                return item;
            }).toList();
            cart.setItems(cartItems);
        } else {
            cart.setItems(List.of()); // Khởi tạo danh sách rỗng nếu không có CartItemDTO
        }

        cartRepository.save(cart);

        ResponseDTO<Cart> response = new ResponseDTO<>();
        response.setStatus(200);
        response.setMessage("Cart created/updated successfully");
        response.setData(cart);

        return ResponseEntity.ok(response);
    }







}
