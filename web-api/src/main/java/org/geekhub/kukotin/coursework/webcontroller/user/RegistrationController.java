package org.geekhub.kukotin.coursework.webcontroller.user;

import org.geekhub.kukotin.coursework.service.user.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping("/registration")
public class RegistrationController { //need to be tested

    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping()
    public String showRegistrationForm(Model model) {
        UserDTO userDto = new UserDTO();
        model.addAttribute("user", userDto);
        return "registration";
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/submit")
    public String registerUser(@ModelAttribute("user") UserDTO dto, Model model) {
        if (dto != null) {
            userService.add(UserConverter.fromDto(dto));
        } else {
            model.addAttribute("message", "Something went wrong, please try again!");
        }
        return "redirect:/registration";
    }
}
