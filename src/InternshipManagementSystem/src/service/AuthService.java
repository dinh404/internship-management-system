package service;

public class AuthService {

    public boolean login(String username, String password) {
        if (username == null || password == null) {
            return false;
        }

        username = username.trim();

        return username.equals("admin") && password.equals("123456");
    }
}