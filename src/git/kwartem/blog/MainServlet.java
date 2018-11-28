package git.kwartem.blog;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class MainServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext servletContext = getServletContext();

        Connection connection = null;
        connection = ConnectionUtils.getStoredConnection((ServletRequest)request);

        PostWrapper postWrapper = new PostWrapper(connection);
        UserWrapper userWrapper = new UserWrapper(connection);

        //to do:
        //create a global collection like HashMap/Set all of blog users and build to work with it here
        ArrayList<Post> posts = null;
        HashMap<Integer, String> user_logins = null;
        try {
            posts = postWrapper.getAllPosts();
            user_logins = userWrapper.getAllUserLogins();

            request.setAttribute("posts", posts);
            System.out.println("-in mainservlet: posts: " + posts);
            System.out.println("-in mainservlet: user_logins: " + user_logins);
            request.setAttribute("user_logins", user_logins);
        } catch (SQLException e) {
            Message.send("An error occurred while processing the request.");
            e.printStackTrace();
        }

        System.out.println("-------------pre-exit-main-servlet---");
        servletContext.getRequestDispatcher("/main.jsp").forward(request, response);
    }
}
