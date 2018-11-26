package git.kwartem.blog;

import static git.kwartem.blog.Constants.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;


public class SignUpServlet extends HttpServlet {
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
        String user_email = request.getParameter("email");

        // to do: checking to match pass and pass_confirm here...

        try(Connection connection = DriverManager.
                getConnection(DB_CONN_URL_PATH, DB_CONN_USER, DB_CONN_PASSWORD)) {

            UserWrapper userWrapper = new UserWrapper(connection);
            User raw_user = new User(raw_login, user_pass, user_email);

            if(!userWrapper.isUserExist(raw_user)){
                boolean result = userWrapper.submitUserToDB(raw_user);
                if(result){
                    Message.send("Your account has been created!");
                    raw_user = null;
                    response.sendRedirect(request.getContextPath() + "/login");
                } else {
                    Message.send("Reg-data of new user has not been submitted");
                }
            } else {
                Message.send("This login is already taken!");
            }

        } catch (Exception e) {
            System.out.println("An error occurred to work with connection");
            System.out.println("-E----------------------------------------------");
            System.out.println(e.fillInStackTrace());
            System.out.println(e.getLocalizedMessage());
            System.out.println(e.getMessage());
            System.out.println("------------------------------------------------");
        }

        /*ServletContext servletContext = getServletContext();
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/index.jsp");
        requestDispatcher.forward(request, response);*/
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/sign.jsp");
        dispatcher.forward(request, response);
    }
}
