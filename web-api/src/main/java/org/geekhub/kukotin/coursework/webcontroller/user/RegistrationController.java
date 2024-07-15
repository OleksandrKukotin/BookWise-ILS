package org.geekhub.kukotin.coursework.webcontroller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    @GetMapping
    public String showRegistrationForm() {
        return "registration";
    }
}
