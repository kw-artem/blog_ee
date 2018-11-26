package git.kwartem.blog;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;

import static git.kwartem.blog.Constants.DB_CONN_PASSWORD;
import static git.kwartem.blog.Constants.DB_CONN_URL_PATH;
import static git.kwartem.blog.Constants.DB_CONN_USER;

public class MainServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext servletContext = getServletContext();

        //to do:
        //to move plugging jdbc_driver and establishing connection into JDBCFilter
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            System.out.println("Failed 0");
        }

        try(Connection connection = DriverManager.
                getConnection(DB_CONN_URL_PATH, DB_CONN_USER, DB_CONN_PASSWORD)) {

            PostWrapper postWrapper =  new PostWrapper(connection);
            ArrayList<Post> posts = postWrapper.getAllPosts();

            request.setAttribute("posts", posts);

        } catch (Exception e) {
            System.out.println("An error occurred to work with DB connection");
            System.out.println("-E----------------------------------------------");
            System.out.println(e.fillInStackTrace());
            System.out.println(e.getLocalizedMessage());
            System.out.println(e.getMessage());
            System.out.println("------------------------------------------------");
        }

        servletContext.getRequestDispatcher("/main.jsp").forward(request, response);
    }



}
