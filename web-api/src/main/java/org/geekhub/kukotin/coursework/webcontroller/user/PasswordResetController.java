package org.geekhub.kukotin.coursework.webcontroller.user;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller()
@RequestMapping("/passwordReset")
public class PasswordResetController {

//    private final UserService userService;
//    private final EmailService emailService;
//
//    public PasswordResetController(UserService userService, EmailService emailService) {
//        this.userService = userService;
//        this.emailService = emailService;
//    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public String passwordReset(Model model) {
        UserDTO userDTO = new UserDTO();
        model.addAttribute("user", userDTO);
        return "forgotPasswordForm";
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public String handlePasswordReset(@ModelAttribute("user") UserDTO userDTO, Model model) {
//        String email = userDTO.getEmail();
//        Optional<User> user = userService.findUserByEmail(email);
//
//        if (user.isPresent()) {
//            String resetToken = UUID.randomUUID().toString();
//            userService.createPasswordResetTokenForUser(user.get(), resetToken);
//            emailService.sendSimpleMessage(email, "Password Reset", "Reset token: " + resetToken);
//            return "resetPasswordConfirmation"; // Create a confirmation view
//        } else {
//            model.addAttribute("error", "User not found.");
//            return "forgotPasswordForm";
//        }
        model.addAttribute("error", "Currently not supported");
        return "forgotPasswordForm";
    }
}
