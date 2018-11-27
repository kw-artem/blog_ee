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

        PostWrapper postWrapper =  new PostWrapper(connection);

        ArrayList<Post> posts = null;
        try {
            posts = postWrapper.getAllPosts();
            request.setAttribute("posts", posts);
        } catch (SQLException e) {
            Message.send("An error occurred while processing the request.");
            e.printStackTrace();
        }

        servletContext.getRequestDispatcher("/main.jsp").forward(request, response);
    }
}
