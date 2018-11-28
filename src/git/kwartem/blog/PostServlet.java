package git.kwartem.blog;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class PostServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Integer post_id = Integer.parseInt(request.getParameter("id"));
        System.out.println(">>>>>post-id = " + post_id);

        if(post_id != null){

            Connection connection = ConnectionUtils.getConnection();
            PostWrapper postWrapper = new PostWrapper(connection);

            try {
                RawPost raw_post = postWrapper.isPostExist(post_id);
                if(raw_post != null){
                    Post post = postWrapper.getPostDetail(raw_post);
                    request.setAttribute("post", post);
                    Message.send(">+++>>>Post detail has loaded success");
                    System.out.println(post);

                    getServletContext().getRequestDispatcher("/post-detail.jsp").forward(request, response);
                } else {
                    Message.send(">!!!>>>There is not post with id=" + post_id);
                    response.sendRedirect(request.getContextPath() + "/main");
                }
            } catch (SQLException e) {
                Message.send("An error occured in page openning process.");
                e.printStackTrace();
                response.sendRedirect(request.getContextPath() + "/main");
            }
        }

    }
}
