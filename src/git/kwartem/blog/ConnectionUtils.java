package git.kwartem.blog;

import javax.servlet.ServletRequest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static git.kwartem.blog.Constants.*;

public class ConnectionUtils {

    public static Connection getConnection(){

        Connection connection = null;

        /*try {
            Class.forName(JDBC_DRIVER).getDeclaredConstructor().newInstance();
            System.out.println("Loading driver " + JDBC_DRIVER + " is loaded success.");
        } catch (Exception e) {
            System.out.println("Loading driver " + JDBC_DRIVER + " has been failed.");
            e.printStackTrace();
        }*/

        try {
            connection = DriverManager.getConnection(DB_CONN_URL_PATH, DB_CONN_USER, DB_CONN_PASSWORD);
            System.out.println("Connection " + connection + " is established success");
        } catch (SQLException e) {
            System.out.println("Connection to database hasn't been establised.");
            e.printStackTrace();
        }

        return connection;
    }

    public static void close(Connection connection){
        try {
            connection.close();
            System.out.println("Connection " + connection + " is closed success");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void rollback(Connection connection){
        try {
            connection.rollback();
            System.out.println("Connection " + connection + " is roll backed success");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void storeConnetion(ServletRequest request, Connection connection){
        request.setAttribute(STORED_DB_CONN, connection);
    }

    public static Connection getStoredConnection(ServletRequest request){
        return (Connection) request.getAttribute(STORED_DB_CONN);
    }
}
