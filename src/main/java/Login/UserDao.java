package Login;

import java.security.Key;

public interface UserDao {
    String findRoleForUser(String name, String pass, Key key);
}
