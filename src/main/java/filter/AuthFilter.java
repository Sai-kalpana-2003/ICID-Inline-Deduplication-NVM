package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/** Redirects to the login page if the user is not logged in. */
@WebFilter(urlPatterns = {"/home.jsp", "/uploadDataSet.jsp", "/myPicked.jsp", "/similarValue.jsp",
        "/uploadDataset", "/myPicked", "/similarityValue"})
public class AuthFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession s = req.getSession(false);
        if (s == null || s.getAttribute("uname") == null) {
            ((HttpServletResponse) response).sendRedirect(req.getContextPath() + "/Login.jsp");
            return;
        }
        chain.doFilter(request, response);
    }
}
