package api;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class PostgresBaseDao {
    public Connection getConnection(){
        Connection con = null;
        try{

            Connection connection = DriverManager.getConnection("jdbc:postgresql://ec2-79-125-4-72.eu-west-1.compute.amazonaws.com:5432/db5blpva23p33c", "jyzecnyhigmoai", "455c0db18f825c96097f9c4dba9eeab57dcc94d2e55f4c36d910822e55169cc8");
            // When this class first attempts to establish a connection, it automatically loads any JDBC 4.0 drivers found within
            // the class path. Note that your application must manually load any JDBC drivers prior to version 4.0.
//          Class.forName("org.postgresql.Driver");

        return connection;

        } /*catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL JDBC driver not found.");
            e.printStackTrace();
        }*/ catch (SQLException e) {
            System.out.println("Connection failure.");
            e.printStackTrace();
        }
        return con;
    }
}
