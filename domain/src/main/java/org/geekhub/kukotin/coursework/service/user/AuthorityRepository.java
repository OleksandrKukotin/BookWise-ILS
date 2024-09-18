package org.geekhub.kukotin.coursework.service.user;

public interface AuthorityRepository {

    void addAuthority(String username, String authority);
    void removeAuthority(String username);
    void changeAuthority(String username, String newAuthority);
    boolean isUserAnonymous(String username);
    String getAuthority(String username);
}
