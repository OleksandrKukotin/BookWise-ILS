package org.geekhub.kukotin.coursework.service.user;

public record User (
    String username,
    String password,
    boolean enabled
){
}
