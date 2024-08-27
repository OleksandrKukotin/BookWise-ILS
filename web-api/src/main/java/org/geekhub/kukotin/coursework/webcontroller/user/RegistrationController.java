package org.geekhub.kukotin.coursework.webcontroller.user;

import org.geekhub.kukotin.coursework.service.user.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

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
    @PostMapping()
    public void registerUser(Model model) {
        UserDTO dto = (UserDTO) model.getAttribute("user");
        if (dto != null) {
            userService.add(UserConverter.fromDto(dto));
        }
    }
}
