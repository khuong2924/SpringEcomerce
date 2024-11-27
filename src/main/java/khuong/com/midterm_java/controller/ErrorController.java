package khuong.com.midterm_java.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorController {

    @RequestMapping("/error-page")
    public String showErrorPage(Model model) {
        // Nhận thông báo lỗi từ request
        String errorMessage = (String) model.asMap().get("error");
        if (errorMessage == null) {
            errorMessage = "Đã xảy ra lỗi không xác định!";
        }
        model.addAttribute("errorMessage", errorMessage);

        return "error";
    }
}
