package git.kwartem.blog;

import com.mysql.cj.Session;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Connection connection = null;

        String raw_login = request.getParameter("login");
        String user_pass = request.getParameter("password");

        connection = ConnectionUtils.getStoredConnection((ServletRequest)request);

        UserWrapper userWrapper = new UserWrapper(connection);
        User raw_user = new User(raw_login, user_pass);

        boolean result = false;
        try {
            result = userWrapper.toLoginUser(raw_user);

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
        } catch (SQLException e) {
            Message.send("An error occurred while processing the request");
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if(request.getRequestURI().equals("/login/logout")){
            System.out.println("---- logout");
            doDelete(request, response);
        } else {
            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/login.jsp");
            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        session.invalidate();
        response.sendRedirect(request.getContextPath() + "/login");
    }
}
