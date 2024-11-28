package khuong.com.midterm_java.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import khuong.com.midterm_java.config.SessionUtils;
import khuong.com.midterm_java.entity.User;
import khuong.com.midterm_java.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

  @Autowired
  private UserService userService;

//  @PostMapping("/login")
//  public String login(@RequestParam String username, @RequestParam String password, Model model) {
//    if (userService.checkCredentials(username, password)) {
//      return "redirect:/home";
//    } else {
//      // Nếu thông tin sai, trả về trang login với thông báo lỗi
//      model.addAttribute("error", "Invalid username or password");
//      return "login";
//    }
//  }
  @PostMapping("/login")
  public String handleLogin(@RequestParam String username, @RequestParam String password, Model model, HttpServletRequest request) {
    // Kiểm tra tài khoản và mật khẩu
    boolean isAuthenticated = userService.validateUser(username, password);

    User getUser = userService.validateGetUser(username, password);

    if (isAuthenticated) {
      // Lưu thông tin người dùng vào session khi đăng nhập thành công
      HttpSession session = request.getSession();
      session.setAttribute("username", username);// Lưu username vào session
      session.setAttribute("user", getUser);
      return "redirect:/home";
    } else {
      model.addAttribute("error", "Tên đăng nhập hoặc mật khẩu không đúng");
      return "login";
    }
  }

  @GetMapping("/checkSession")
  public String checkSession(HttpServletRequest request, Model model) {
    HttpSession session = request.getSession(false);  // false để không tạo mới session nếu không tồn tại

    if (session != null && session.getAttribute("username") != null) {
      String username = (String) session.getAttribute("username");
      User user = (User) session.getAttribute("user");
      model.addAttribute("message", "User logged in: " + username + " and usernameID: " + user.getId());
    } else {
      model.addAttribute("message", "No user logged in.");
    }

    return "checkSession";  // Trang hiển thị kết quả kiểm tra session
  }
}
