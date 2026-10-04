package controller;

import bean.UserBean;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import service.UserService;
import service.UserServiceImpl;

@WebServlet("/login")
public class LoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uname = req.getParameter("uname");
        String password = req.getParameter("password");
        UserBean user = (uname == null || password == null) ? null : new UserServiceImpl().login(uname.trim(), password);
        if (user != null) {
            req.getSession().invalidate();                // new session id after login
            HttpSession session = req.getSession(true);
            session.setAttribute("uname", user.getName());
            resp.sendRedirect("home.jsp");
        } else {
            req.setAttribute("alert", "Invalid user name or password");
            req.getRequestDispatcher("Login.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.sendRedirect("Login.jsp");
    }
}
