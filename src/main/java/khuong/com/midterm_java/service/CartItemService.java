package khuong.com.midterm_java.service;

import khuong.com.midterm_java.dto.CartItemDTO;
import khuong.com.midterm_java.entity.CartItem;
import khuong.com.midterm_java.repository.CartItemRepository;
import khuong.com.midterm_java.repository.CartRepository;
import khuong.com.midterm_java.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@RequestMapping("/cartItems")
public class CartItemService {

    @Autowired
    private final CartItemRepository cartItemRepository;

    @Autowired
    private final ProductRepository productRepository;

    @Autowired
    private final CartRepository cartRepository;

    public List<CartItemDTO> mapToDto(List<CartItem> cartItems) {
        List<CartItemDTO> dtos = new ArrayList<>();
        for (CartItem cartItem : cartItems) {
            dtos.add(new CartItemDTO(
                    cartItem.getId(),
                    cartItem.getCart().getId(),
                    cartItem.getProduct().getId(),
                    cartItem.getQuantity()
            ));
        }
        return dtos;
    }

    public List<CartItemDTO> getAll() {
        List<CartItem> cartItems = cartItemRepository.findAll();
        return mapToDto(cartItems);
    }

    public void create(CartItemDTO cartItemDTO) {
        CartItem cartItem = new CartItem();
        cartItem.setCart(cartRepository.findById(cartItemDTO.getCartId()).get());
        cartItem.setProduct(productRepository.findById(cartItemDTO.getProductId()).get());
        cartItem.setQuantity(cartItemDTO.getQuantity());
        cartItemRepository.save(cartItem);
    }

    public void update(Long id, CartItemDTO cartItemDTO) {
        CartItem cartItem = cartItemRepository.findById(id).get();
        cartItem.setCart(cartRepository.findById(cartItemDTO.getCartId()).get());
        cartItem.setProduct(productRepository.findById(cartItemDTO.getProductId()).get());
        cartItem.setQuantity(cartItemDTO.getQuantity());
        cartItemRepository.save(cartItem);
    }

    public void delete(Long id) {
        cartItemRepository.deleteById(id);
    }
}









