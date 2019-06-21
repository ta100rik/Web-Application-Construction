package Login;

import java.security.Key;

public class ServiceProvider {
    private static UserPostgresDaoImpl uPDI = new UserPostgresDaoImpl();
    public static String login(String username, String password, Key key){
        return uPDI.findRoleForUser(username,password,key);
    }
}
