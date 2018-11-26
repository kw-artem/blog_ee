package git.kwartem.blog;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static git.kwartem.blog.Constants.*;

public class JDBCFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        Connection connection = null;

        try {
            Class.forName(JDBC_DRIVER).getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            System.out.println("Failed 0");
        }

        try {
            connection = DriverManager.
                    getConnection(DB_CONN_URL_PATH, DB_CONN_USER, DB_CONN_PASSWORD);
            connection.setAutoCommit(false);
            request.setAttribute("DB_CONN", connection);

            chain.doFilter(request, response);

            connection.commit();

        } catch (Exception e) {
            System.out.println("An error occurred to work with connection");
            System.out.println("-E----------------------------------------------");
            System.out.println(e.fillInStackTrace());
            System.out.println(e.getLocalizedMessage());
            System.out.println(e.getMessage());
            System.out.println("------------------------------------------------");

            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
