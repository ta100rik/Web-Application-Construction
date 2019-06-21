package Login;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;

import java.security.Key;
import java.sql.*;
import java.util.Calendar;

public class UserPostgresDaoImpl extends PostgresBaseDao implements UserDao {
    @Override
    public String findRoleForUser(String name, String pass, Key key){

        Connection con = super.getConnection();
        try {
            Statement statement = con.createStatement();
            PreparedStatement st = con.prepareStatement("SELECT * FROM useraccount where  username = ? and password = ? limit 1 ");
            st.setString(1,name);
            st.setString(2,pass);
            ResultSet resultSet = st.executeQuery();
            while (resultSet.next()) {
                    String rol = resultSet.getString("role");
                    Calendar expiration = Calendar.getInstance();
                    expiration.add(Calendar.MINUTE,60);
                    String JWT = Jwts.builder()
                            .setIssuer("localhost")
                            .setSubject("userlogin")
                            .setExpiration(expiration.getTime())
                            .claim("role",rol)
                            .signWith(SignatureAlgorithm.HS512,key)
                            .compact();
                    return JWT;
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


}
