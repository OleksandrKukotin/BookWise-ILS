package org.geekhub.kukotin.coursework.webcontroller.user;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.request.WebRequest;


public class RegistrationController {
    @GetMapping("/registration")
    public String showRegistrationForm(WebRequest request, Model model) {

        UserDTO userDto = new UserDTO();
        model.addAttribute("user", userDto);
        return "registration";
    }
}
