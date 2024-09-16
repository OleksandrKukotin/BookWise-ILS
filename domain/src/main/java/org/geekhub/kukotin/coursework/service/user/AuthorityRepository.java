package org.geekhub.kukotin.coursework.service.user;

public interface AuthorityRepository {

    void addAuthority(String username, String authority);
    void removeAuthority(String username, String authority);
    void changeAuthority(String username, String newAuthority);
    boolean isUserHaveAuthority(String username, String authority);
    String getAuthority(String username);
}
