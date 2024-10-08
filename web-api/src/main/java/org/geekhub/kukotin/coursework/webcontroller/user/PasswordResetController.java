package org.geekhub.kukotin.coursework.webcontroller.user;

import org.geekhub.kukotin.coursework.service.passwordreset.PasswordResetService;
import org.geekhub.kukotin.coursework.service.passwordreset.PasswordResetToken;
import org.geekhub.kukotin.coursework.service.user.User;
import org.geekhub.kukotin.coursework.service.user.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Optional;

@Controller()
@RequestMapping("/password-reset")
public class PasswordResetController {

    private final UserService userService;
    private final PasswordResetService passwordResetService;

    public PasswordResetController(UserService userService, PasswordResetService passwordResetService) {
        this.userService = userService;
        this.passwordResetService = passwordResetService;
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public String showResetPasswordPage(@RequestParam("token") String token, Model model) {
        Optional<PasswordResetToken> resetToken = passwordResetService.getByToken(token);
        if (resetToken.isPresent()) {
            Optional<User> user = userService.getUserByEmail(resetToken.get().getEmail());
            user.ifPresent(value -> model.addAttribute("user", value));
            return "resetPasswordForm";
        } else {
            model.addAttribute("error", "Token not found.");
            return "resetPasswordForm";
        }
    }

    // TODO: implement POST mapping
}
