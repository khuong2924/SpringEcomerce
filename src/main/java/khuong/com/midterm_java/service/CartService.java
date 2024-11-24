package khuong.com.midterm_java.service;

import khuong.com.midterm_java.dto.CartDTO;
import khuong.com.midterm_java.dto.CartItemDTO;
import khuong.com.midterm_java.dto.OrderItemDTO;
import khuong.com.midterm_java.entity.Cart;
import khuong.com.midterm_java.entity.CartItem;
import khuong.com.midterm_java.entity.OrderItem;
import khuong.com.midterm_java.repository.CartRepository;
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
@RequestMapping("/carts")
public class CartService {
    @Autowired
    private final CartRepository cartRepository;
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;

    public List<CartDTO> mapToDto(List<Cart> carts) {
        List<CartDTO> dtos = new ArrayList<>();
        for (Cart cart : carts) {
            // convert List<CartItem> to List<CartItemDTO>
            List<CartItemDTO> itemDtos = cart.getItems().stream()
                    .map(item -> new CartItemDTO(
                            item.getId(),
                            item.getCart().getId(),
                            item.getProduct().getId(),
                            item.getQuantity()
                    ))
                    .toList();
            dtos.add(new CartDTO(
                    cart.getId(),
                    cart.getUser().getId(),
                    itemDtos
            ));
        }
        return dtos;
    }

    public List<CartDTO> getAll() {
        List<Cart> carts = cartRepository.findAll();
        return mapToDto(carts);
    }

    public void update(Long id, CartDTO cartDTO) {
        // Lấy đối tượng Cart từ database theo id
        Cart cart = cartRepository.findById(id).orElseThrow(() -> new RuntimeException("Cart not found"));

        // Cập nhật thông tin User liên kết với Cart
        cart.setUser(userRepository.findById(cartDTO.getUserId()).orElseThrow(() -> new RuntimeException("User not found")));

        // Chuyển đổi danh sách CartItemDTO sang CartItem
        List<CartItem> items = cartDTO.getItems().stream()
                .map(item -> new CartItem(
                        item.getId(),
                        cart, // Gán Cart cho CartItem
                        productRepository.findById(item.getProductId()).orElseThrow(() -> new RuntimeException("Product not found")),
                        item.getQuantity()
                ))
                .toList();

        // Cập nhật danh sách các CartItem vào Cart
        cart.setItems(items);

        // Lưu lại vào database
        cartRepository.save(cart);
    }


    public void delete(Long id) {
        cartRepository.deleteById(id);
    }
}
