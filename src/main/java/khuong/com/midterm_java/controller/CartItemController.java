package khuong.com.midterm_java.controller;

import khuong.com.midterm_java.config.SessionUtils;
import khuong.com.midterm_java.dto.CartItemDTO;
import khuong.com.midterm_java.dto.ResponseDTO;
import khuong.com.midterm_java.entity.Cart;
import khuong.com.midterm_java.entity.CartItem;
import khuong.com.midterm_java.entity.Category;
import khuong.com.midterm_java.entity.Product;
import khuong.com.midterm_java.repository.CartItemRepository;
import khuong.com.midterm_java.repository.CartRepository;
import khuong.com.midterm_java.repository.ProductRepository;
import khuong.com.midterm_java.service.CartItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/cartItems")
@RequiredArgsConstructor
public class CartItemController {
    private final CartItemService cartItemService;

    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public ResponseDTO<List<CartItemDTO>> getCartItems() {
        ResponseDTO<List<CartItemDTO>> response = new ResponseDTO<>();
        response.setData(cartItemService.getAll());
        response.setStatus(200);
        return response;
    }

    @PutMapping
    public ResponseDTO<Void> updateCartItem(@PathVariable("id") Long id, @RequestBody CartItemDTO cartItemDTO) {
        cartItemService.update(id, cartItemDTO);
        return ResponseDTO.<Void>builder()
                .status(201)
                .message("Sửa thành công cart item")
                .build();
    }

//    @DeleteMapping("{id}")
//    public ResponseDTO<Void> deleteCartItem(@PathVariable("id") Long id) {
//        cartItemService.delete(id);
//        return ResponseDTO.<Void>builder()
//                .status(201)
//                .message("Xóa thành công cart item")
//                .build();
//    }

//
@PostMapping("/delete")
public ResponseEntity<Void> deleteCartItem(@RequestParam("cartItemId") Long cartItemId) {
    // Xử lý xóa cartItem
    cartItemService.delete(cartItemId);

    // Trả về HTTP redirect
    HttpHeaders headers = new HttpHeaders();
    headers.add("Location", "/cart"); // Chuyển hướng đến /cart sau khi xóa thành công
    return new ResponseEntity<>(headers, HttpStatus.FOUND); // Mã trạng thái 302 (Found) cho chuyển hướng
}

//    @PostMapping("/upload")
//    public ResponseEntity<ResponseDTO<Void>> createOrUpdateCartItem(
//            @RequestParam(value = "id", required = false) Long id,
//            @RequestParam(value = "product_id") Long productId,
//            @RequestParam(value = "quantity") Integer quantity
//    ) {
//
//        Long userId = SessionUtils.getCurrentUserId();
//        Cart cart = cartRepository.findByUserId(userId);
//        CartItem cartItem;
//
//        if (id != null) {
//            cartItem = cartItemRepository.findById(id)
//                    .orElseThrow(() -> new RuntimeException("Cart Item not found with id: " + id));
//        } else {
//            cartItem = new CartItem();
//        }
//
//        Product product = productRepository.findById(productId)
//                .orElseThrow(() -> new RuntimeException("Product not found with id: " + productId));
//
//        cartItem.setCart(cart);
//        cartItem.setProduct(product);
//        cartItem.setQuantity(quantity);
//
//        cartItemRepository.save(cartItem);
//
//        ResponseDTO<Void> response = new ResponseDTO<>();
//        response.setStatus(200);
//        response.setMessage(id == null ? "Cart Item created successfully" : "Cart Item updated successfully");
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Location", "/listProduct");
//
//        return ResponseEntity.status(HttpStatus.SEE_OTHER).body(response);
//    }

    @PostMapping("/upload")
    public ResponseEntity<Void> createOrUpdateCartItem(
            @RequestParam(value = "id", required = false) Long id,
            @RequestParam(value = "product_id") Long productId,
            @RequestParam(value = "quantity") Integer quantity
    ) {
        Long userId = SessionUtils.getCurrentUserId();
        Cart cart = cartRepository.findByUserId(userId);
        CartItem cartItem;

        if (id != null) {
            cartItem = cartItemRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Cart Item not found with id: " + id));
        } else {
            cartItem = new CartItem();
        }

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + productId));

        cartItem.setCart(cart);
        cartItem.setProduct(product);
        cartItem.setQuantity(quantity);

        cartItemRepository.save(cartItem);

        // Thiết lập header chuyển hướng
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create("/listProduct")); // Đường dẫn chuyển hướng
        return new ResponseEntity<>(headers, HttpStatus.SEE_OTHER); // Mã trạng thái 303
    }


}
