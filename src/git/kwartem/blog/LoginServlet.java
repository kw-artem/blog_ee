package git.kwartem.blog;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;

import static git.kwartem.blog.Constants.DB_CONN_PASSWORD;
import static git.kwartem.blog.Constants.DB_CONN_URL_PATH;
import static git.kwartem.blog.Constants.DB_CONN_USER;

public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        //to do:
        //to move plugging jdbc_driver and establishing connection into JDBCFilter
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            System.out.println("Failed 0");
        }

        String raw_login = request.getParameter("login");
        String user_pass = request.getParameter("password");

        try(Connection connection = DriverManager.
                getConnection(DB_CONN_URL_PATH, DB_CONN_USER, DB_CONN_PASSWORD)) {

            UserWrapper userWrapper = new UserWrapper(connection);
            User raw_user = new User(raw_login, user_pass);
            boolean result = userWrapper.toLoginUser(raw_user);

            if(result){
                Message.send("You have logged in!");
                raw_user.setPassword(null);
                request.getSession().setAttribute("user", raw_user);
                //raw_user = null;
                response.sendRedirect(request.getContextPath() + "/main");
            } else {
                Message.send("Login or password is not correct.");
                response.sendRedirect(request.getContextPath() + "/login");
            }

        } catch (Exception e) {
            System.out.println("An error occurred to work with connection");
            System.out.println("-E----------------------------------------------");
            System.out.println(e.fillInStackTrace());
            System.out.println(e.getLocalizedMessage());
            System.out.println(e.getMessage());
            System.out.println("------------------------------------------------");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/login.jsp");
        dispatcher.forward(request, response);
    }
}
