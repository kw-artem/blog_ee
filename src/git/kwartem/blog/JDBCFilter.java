package git.kwartem.blog;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static git.kwartem.blog.Constants.*;

public class JDBCFilter implements Filter {

    private boolean isDriverLoaded = false;

    @Override
    public void init(FilterConfig filterConfig) {

        try {
            Class.forName(JDBC_DRIVER).getDeclaredConstructor().newInstance();
            isDriverLoaded = true;
            System.out.println("Loading driver " + JDBC_DRIVER + " is loaded success.");
        } catch (Exception e) {
            System.out.println("Loading driver " + JDBC_DRIVER + " has been failed.");
            e.printStackTrace();
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String servletPath = httpServletRequest.getServletPath();

        //to do:
        //create a handler which will to resolve incoming request path
        if(!servletPath.contains("/welcome")){
            Connection connection = null;
            try {
                connection = ConnectionUtils.getConnection();
                connection.setAutoCommit(false);

                ConnectionUtils.storeConnetion(request, connection);
                chain.doFilter(request, response);

                connection.commit();

            } catch (Exception e) {
                ConnectionUtils.rollback(connection);
                System.out.println("An error occurred to work with connection");
                System.out.println("-E---------------------------------------");
                System.out.println(e.getMessage());
                e.printStackTrace();
            } finally {
                ConnectionUtils.close(connection);
            }
        }


    }

}
