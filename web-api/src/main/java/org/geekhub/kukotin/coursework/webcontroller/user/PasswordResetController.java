package org.geekhub.kukotin.coursework.webcontroller.user;

import org.geekhub.kukotin.coursework.service.passwordreset.PasswordResetService;
import org.geekhub.kukotin.coursework.service.passwordreset.PasswordResetToken;
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
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import java.util.Optional;

@Controller()
@RequestMapping("/password-reset")
@SessionAttributes("tokenCache")
public class PasswordResetController {

    public static final String RESET_PASSWORD_FORM = "resetPasswordForm";
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
            model.addAttribute("error", "Token not found.");
        } else if (passwordResetService.isTokenExpired(resetToken.get().getToken())) {
            model.addAttribute("error", "Token is expired");
        }
        return RESET_PASSWORD_FORM;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.OK)
    public String resetPassword(@SessionAttribute("tokenCache") String token, @ModelAttribute("user") UserDTO userDTO,
                                SessionStatus sessionStatus) {
        userService.changePassword(userDTO.getUsername(), userDTO.getPassword());
        passwordResetService.deleteToken(token);
        sessionStatus.setComplete();
        return "redirect:/login";
    }
}
