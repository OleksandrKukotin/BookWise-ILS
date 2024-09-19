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

    public static final String USER_MANAGEMENT_REDIRECT = "redirect:/administrator/users";
    private final UserService userService;

    public AdministratorController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String listUsers(Model model) {
        List<User> users = userService.getAll();
        List<String> roles = List.of("ROLE_USER", "ROLE_ADMIN", "ROLE_LIBRARIAN");
        model.addAttribute("users", users);
        model.addAttribute("roles", roles);
        return "userManagement";
    }

    @PostMapping("/users/delete")
    public String deleteUser(@RequestParam("username") String username) {
        userService.remove(username);
        return USER_MANAGEMENT_REDIRECT;
    }

    @PostMapping("/users/toggle")
    public String toggleUser(@RequestParam("username") String username) {
        userService.toggleUserStatus(username);
        return USER_MANAGEMENT_REDIRECT;
    }

    @PostMapping("/users/changeRole")
    public String addRole(@RequestParam("username") String username, @RequestParam("role") String role) {
        userService.changeRole(username, role);
        return USER_MANAGEMENT_REDIRECT;
    }

    @PostMapping("/users/makeAnonymous")
    public String removeRole(@RequestParam("username") String username) {
        userService.changeRole(username, "ROLE_ANONYMOUS");
        return USER_MANAGEMENT_REDIRECT;
    }

}
