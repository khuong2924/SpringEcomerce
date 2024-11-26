package khuong.com.midterm_java.controller;
import khuong.com.midterm_java.config.SessionUtils;
import khuong.com.midterm_java.entity.Cart;
import khuong.com.midterm_java.entity.CartItem;
import khuong.com.midterm_java.entity.Product;
import khuong.com.midterm_java.entity.User;
import khuong.com.midterm_java.repository.CartItemRepository;
import khuong.com.midterm_java.repository.CartRepository;
import khuong.com.midterm_java.repository.ProductRepository;
import khuong.com.midterm_java.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartItemRepository cartItemRepository;

    @GetMapping("/listProduct")
    public String getProducts(Model model) {
//        model.addAttribute("user", SessionUtils.getCurrentUser());
        List<Product> products = productRepository.findAll();
        model.addAttribute("products", products);
        return "listProduct";
    }

    @GetMapping("/")
    public String index() {
        return "home";
    }

    @GetMapping("/home")
    public String returnHome() {
        return "home";
    }

    @GetMapping("/login")
    public String returnLogin() {


        return "login";
    }

    @GetMapping("/cart")
    public String returnCart(Model model) {
        Long userId = SessionUtils.getCurrentUserId();
        Cart cart = cartRepository.findByUserId(userId);
        model.addAttribute("cart", cart);
        List<CartItem> cartItemList = cartItemRepository.findByCart(cart);
        model.addAttribute("cartItemList", cartItemList);
        return "cart";
    }

    @GetMapping("/cartItems/cart")
    public String returnCartItem() {
        return "cart";
    }


}
