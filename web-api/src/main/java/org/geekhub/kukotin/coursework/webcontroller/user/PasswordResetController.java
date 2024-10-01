package org.geekhub.kukotin.coursework.webcontroller.user;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller()
@RequestMapping("/passwordReset")
public class PasswordResetController {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public String passwordReset() {
        return "forgotPasswordForm";
    }
}
