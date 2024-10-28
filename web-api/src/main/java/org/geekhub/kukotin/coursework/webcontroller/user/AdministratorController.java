package org.geekhub.kukotin.coursework.webcontroller.user;

import org.geekhub.kukotin.coursework.service.email.EmailService;
import org.geekhub.kukotin.coursework.service.user.User;
import org.geekhub.kukotin.coursework.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller()
@RequestMapping("/administrator")
public class AdministratorController {

    private static final Logger logger = LoggerFactory.getLogger(AdministratorController.class);
    public static final String USER_MANAGEMENT_REDIRECT = "redirect:/administrator/users";
    private final UserService userService;
    private final EmailService emailService;

    public AdministratorController(UserService userService, EmailService emailService) {
        this.userService = userService;
        this.emailService = emailService;
    }

    @GetMapping("/users")
    public String listUsers(@RequestParam("page") int page, @RequestParam("size") int size, Model model) {
        List<User> users = userService.getUsersPage(page, size);
        List<String> roles = List.of("ROLE_USER", "ROLE_ADMIN", "ROLE_LIBRARIAN");
        model.addAttribute("users", users);
        model.addAttribute("roles", roles);
        model.addAttribute("totalPages", (int) Math.ceil((double) userService.getTotalUsersCount() / size));
        model.addAttribute("currentPage", page);
        model.addAttribute("size", size);
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
        emailService.sendSimpleMessage("example@gmail.com", "notification",
            username + " account was disabled, lol.");
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

    @PostMapping("/users/resetPassword")
    public String getResetPasswordForm(@RequestParam("username") String username, Model model) {
        Optional<User> userOptional = userService.getUserByUsername(username);
        userOptional.ifPresent(user -> model.addAttribute("user", UserConverter.toDto(user)));
        return "resetPasswordForm";
    }

    @PostMapping("/users/resetPassword/confirm")
    public String processResetPassword(@ModelAttribute("user") UserDTO dto) {
        if (dto.getEmail() == null || dto.getEmail().isBlank()) {
            String log = dto.getUsername() + " has no email address";
            logger.info(log);
        } else {
            String emailNotification = "Your password was changed by admin. Your new password: " + dto.getPassword();
            emailService.sendSimpleMessage(dto.getEmail(), "Your new password in BookWise ILS!", emailNotification);
        }
        userService.changePassword(dto.getUsername(), dto.getPassword());
        return USER_MANAGEMENT_REDIRECT;
    }
}
