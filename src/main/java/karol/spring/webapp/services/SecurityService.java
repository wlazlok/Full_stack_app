package karol.spring.webapp.services;

public interface SecurityService {
    String findLoggedInUser();

    void  autoLogin(String username, String password);

    String getUsernameOfLoggedUser();
}
