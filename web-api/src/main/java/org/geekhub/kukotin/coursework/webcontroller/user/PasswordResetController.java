package org.geekhub.kukotin.coursework.webcontroller.user;

import org.geekhub.kukotin.coursework.service.passwordreset.PasswordResetService;
import org.geekhub.kukotin.coursework.service.passwordreset.PasswordResetToken;
import org.geekhub.kukotin.coursework.service.user.User;
import org.geekhub.kukotin.coursework.service.user.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Optional;

@Controller()
@RequestMapping("/passwordReset")
public class PasswordResetController {

    public static final String RESET_PASSWORD_FORM = "resetPasswordForm";
    public static final String ERROR_PAGE = "errorPage";
    public static final String ERROR_MESSAGE_ATTRIBUTE = "errorMessage";

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
        if (resetToken.isEmpty()) {
            model.addAttribute(ERROR_MESSAGE_ATTRIBUTE, "Token not found.");
            return ERROR_PAGE;
        } else if (passwordResetService.isTokenExpired(resetToken.get().getToken())) {
            model.addAttribute(ERROR_MESSAGE_ATTRIBUTE, "Token is expired");
            return ERROR_PAGE;
        }
        Optional<User> user = userService.getUserByUsername(resetToken.get().getEmail());
        if (user.isEmpty()) {
            model.addAttribute(ERROR_MESSAGE_ATTRIBUTE, "User not found");
            return ERROR_PAGE;
        } else {
            model.addAttribute("user", UserConverter.toDto(user.get()));
            return RESET_PASSWORD_FORM;
        }
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.OK)
    public String resetPassword(@ModelAttribute("user") UserDTO userDTO) {
        userService.changePassword(userDTO.getUsername(), userDTO.getPassword());
        passwordResetService.deleteToken(userDTO.getUsername());
        return "/login";
    }
}
