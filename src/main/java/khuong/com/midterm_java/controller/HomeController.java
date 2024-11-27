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

    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }

    @GetMapping("/home")
    public String returnHome(Model model) {
        Long userId = SessionUtils.getCurrentUserId();
        model.addAttribute("user", SessionUtils.getCurrentUser());
        model.addAttribute("userId", userId);
        return "home";
    }

    @GetMapping("/ship")
    public String returnShipping() {
        return "ship";
    }

    @GetMapping("/login")
    public String returnLogin() {


        return "login";
    }

    @GetMapping("/cart")
    public String returnCart(Model model) {
        Long userId = SessionUtils.getCurrentUserId();
        if (userId == null) {
            model.addAttribute("error", "Bạn cần đăng nhập để truy cập giỏ hàng.");
            return "error";
        }
        else {


            Cart cart = cartRepository.findByUserId(userId);
            model.addAttribute("cart", cart);
            List<CartItem> cartItemList = cartItemRepository.findByCart(cart);
            model.addAttribute("cartItemList", cartItemList);

            // Tính toán tổng giá trị
            double subtotal = cartItemList.stream()
                    .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
                    .sum();
            double tax = subtotal * 0.08; // Thuế 8%
            double shipping = subtotal > 50 ? 0 : 5.99;
            double total = subtotal + tax + shipping;

            subtotal = Math.floor(subtotal); // Làm tròn xuống và lấy phần nguyên
            tax = Math.floor(tax);           // Làm tròn xuống và lấy phần nguyên
            shipping = Math.floor(shipping); // Làm tròn xuống và lấy phần nguyên
            total = Math.floor(total);

            // Gắn các giá trị vào model
            model.addAttribute("subtotal", subtotal);
            model.addAttribute("tax", tax);
            model.addAttribute("shipping", shipping);
            model.addAttribute("total", total);


            return "cart";
        }
    }

    @GetMapping("/cartItems/cart")
    public String returnCartItem() {
        return "cart";
    }


}
