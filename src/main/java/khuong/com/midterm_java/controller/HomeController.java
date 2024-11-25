package khuong.com.midterm_java.controller;

import khuong.com.midterm_java.config.CustomUserDetails;
import khuong.com.midterm_java.config.SecurityConfig;
import khuong.com.midterm_java.dto.ProductDTO;
import khuong.com.midterm_java.entity.Product;
import khuong.com.midterm_java.entity.User;
import khuong.com.midterm_java.repository.ProductRepository;
import khuong.com.midterm_java.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/listProduct")
    public String getProducts(Model model) {
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
    public String returnCart() {
        return "cart";
    }

    @GetMapping("/test")
    public String getUserInfo(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            User user = new User();

            if (principal instanceof CustomUserDetails) {
                user = ((CustomUserDetails) principal).getUser();
            }


            model.addAttribute("username", user.getUsername());

        }
        return "checkUser";
    }


}
