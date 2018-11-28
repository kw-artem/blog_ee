package git.kwartem.blog;

import com.sun.deploy.net.HttpRequest;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PermissionFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String requestURI = request.getRequestURI();

        boolean isBlocked = (!requestURI.equals("/welcome") && !requestURI.equals("/login") && !requestURI.equals("/sign") &&
                             !requestURI.equals("/favicon.ico"));
        System.out.println(requestURI + " " + request.getMethod() + " " + isBlocked);
        System.out.println("user==null: " + (request.getSession().getAttribute("user") == null));
        System.out.println("user-value: " + (request.getSession().getAttribute("user")));
        System.out.println("getContextPath: '" + request.getContextPath() + "'");

        if(isBlocked && request.getSession().getAttribute("user") == null){
            try {
                Message.send("You don't have permissions enough. You need to log in.");
                response.sendRedirect(request.getContextPath() + "/login");  // to /login
            } catch (IOException e) {
                Message.send("Error.");
                e.printStackTrace();
            }
        } else {
            chain.doFilter(servletRequest, servletResponse);
        }
        System.out.println("...exiting from permission filter");
    }
}
