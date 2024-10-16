package org.geekhub.kukotin.coursework.webcontroller.user;

import org.geekhub.kukotin.coursework.service.user.User;

public class UserConverter {

    private UserConverter() {
    }

    public static User fromDto(UserDTO dto) {
        return new User(dto.getUsername(), dto.getEmail(), "", dto.getPassword(), true);
    }

    public static UserDTO toDto(User user) {
        return new UserDTO(user.getUsername(), user.getEmail(), user.getPassword(), "");
    }
}
