package karol.spring.webapp.services;


import java.util.Collection;

public interface SecurityService {
    String findLoggedInUser();

    void  autoLogin(String username, String password);

    String getUsernameOfLoggedUser();

    Collection getLoggerUser();
}
