package khuong.com.midterm_java.controller;

import khuong.com.midterm_java.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

  private final UserService userService;

  public LoginController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping("/login")
  public String login(@RequestParam String username, @RequestParam String password, Model model) {
    if (userService.checkCredentials(username, password)) {
      return "redirect:/home";
    } else {
      // Nếu thông tin sai, trả về trang login với thông báo lỗi
      model.addAttribute("error", "Invalid username or password");
      return "login";
    }
  }
}
