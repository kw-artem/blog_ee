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
        System.out.println(requestURI + " " + request.getMethod());

        if(!requestURI.contains("/welcome") && !requestURI.contains("/login") && !requestURI.contains("/sign")){
            if(request.getSession().getAttribute("user") == null){
                try {
                    Message.send("You don't have permissions enough. You need to log in.");
                    response.sendRedirect(request.getContextPath() + "/login");  // to /login
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        chain.doFilter(servletRequest, servletResponse);
    }
}
