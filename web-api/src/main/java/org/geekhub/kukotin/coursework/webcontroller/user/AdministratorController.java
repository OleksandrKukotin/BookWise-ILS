package org.geekhub.kukotin.coursework.webcontroller.user;

import org.geekhub.kukotin.coursework.service.user.User;
import org.geekhub.kukotin.coursework.service.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller()
@RequestMapping("/administrator")
public class AdministratorController {

    private final UserService userService;

    public AdministratorController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String listUsers(Model model) {
        List<User> users = userService.getAll();
        model.addAttribute("users", users);
        return "userManagement"; //need to fix returning to list
    }

    @PostMapping("/users/delete")
    public String deleteUser(@RequestParam("username") String username) {
        userService.remove(username);
        return "userManagement"; //need to fix returning to list
    }

    @PostMapping("/users/toggle")
    public String toggleUser(@RequestParam("username") String username) {
        userService.toggleUserStatus(username);
        return "userManagement"; //need to fix returning to list
    }
}
