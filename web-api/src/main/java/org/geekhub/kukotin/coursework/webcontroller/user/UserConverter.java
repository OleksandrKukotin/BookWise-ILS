package org.geekhub.kukotin.coursework.webcontroller.user;

import org.geekhub.kukotin.coursework.service.user.User;

public class UserConverter {

    private UserConverter() {
    }

    public static User fromDto(UserDTO dto) {
        return new User(dto.getUsername(), dto.getPassword(), true);
    }
}
