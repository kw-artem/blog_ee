package git.kwartem.blog;

import java.sql.*;
import java.util.ArrayList;

public class User{

    private String login;
    private String email;
    private String password;

    private ArrayList<Post> posts;

    public User(String login){
        this.login = login;
    }

    public User(String login, String password){
        this.login = login;
        this.password = password;
    }

    public User(String login, String password, String email){
        this.login = login;
        this.password = password;
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) { this.email = email; }

    public void setPassword(String password) {
        this.password = password;
    }

    public static boolean doPasswordsMatch(String password, String confirm_password){
        return confirm_password.equals(password);
    }
}
