package org.geekhub.kukotin.coursework.webcontroller.user;

import org.geekhub.kukotin.coursework.service.email.EmailService;
import org.geekhub.kukotin.coursework.service.passwordreset.PasswordResetService;
import org.geekhub.kukotin.coursework.service.user.User;
import org.geekhub.kukotin.coursework.service.user.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/forgot-password")
public class ForgotPasswordRequestController {

    private final UserService userService;
    private final EmailService emailService;
    private final PasswordResetService passwordResetService;

    public ForgotPasswordRequestController(UserService userService, EmailService emailService,
                                           PasswordResetService passwordResetService) {
        this.userService = userService;
        this.emailService = emailService;
        this.passwordResetService = passwordResetService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public String showForgotPasswordForm(Model model) {
        UserDTO userDTO = new UserDTO();
        model.addAttribute("user", userDTO);
        return "forgotPasswordForm";
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public String handleForgotPasswordRequest(@ModelAttribute("user") UserDTO userDTO, Model model) {
        String email = userDTO.getEmail();
        Optional<User> user = userService.getUserByEmail(email);

        if (user.isPresent()) {
            String resetToken = UUID.randomUUID().toString();
            passwordResetService.createToken(resetToken, user.get().getUsername());
            String resetLink = "http://localhost:8080/passwordReset?token=" + resetToken;
            emailService.sendSimpleMessage(email, "Password Reset", "Click here to reset your password: "
                + resetLink);
            return "forgotPasswordConfirmation";
        } else {
            model.addAttribute("error", "User not found.");
            return showForgotPasswordForm(model);
        }
    }
}
