package com.anhbui.demosecurity.controller;

import com.anhbui.demosecurity.entity.User;
import com.anhbui.demosecurity.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@AllArgsConstructor
@Controller
public class UserController {
    private UserService userService;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String processRegistrationForm(@ModelAttribute("user") @Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            return "register";
        }

        if (userService.isUserExists(user.getUsername())) {
            result.rejectValue("username", "error.user", "Tên đăng nhập đã tồn tại!");
            return "register";
        }

        userService.saveUser(user);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String getLogin() {
        return "login";
    }
    @GetMapping("/")
    public String index(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName();
            User user = userService.findByUsername(username);
            model.addAttribute("user", user);
        }
        return "index";
    }
    @GetMapping("/user/profile")
    public String showUserProfile(Model model) {
        // Lấy thông tin xác thực (người dùng đã đăng nhập) từ SecurityContextHolder
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Kiểm tra xem người dùng đã đăng nhập hay chưa
        if (authentication != null && authentication.isAuthenticated()) {
            // Lấy tên người dùng từ thông tin xác thực
            String username = authentication.getName();

            // Tạo đối tượng User (hoặc lấy thông tin người dùng từ cơ sở dữ liệu)
            User user = new User();
            user.setId(1L); // Gán ID của người dùng
            user.setUsername(username); // Gán tên người dùng

            // Thêm thông tin người dùng vào Model để hiển thị trên trang
            model.addAttribute("user", user);
        }

        return "profile";
    }
}
