package controller;

import bean.UserBean;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.UserService;
import service.UserServiceImpl;

@WebServlet("/register")
public class RegisterController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        UserBean u = new UserBean();
        u.setName(trim(req.getParameter("name")));
        u.setEmail(trim(req.getParameter("email")));
        u.setPassword(req.getParameter("password"));
        u.setMobile(trim(req.getParameter("mobile")));
        u.setGender(req.getParameter("gender"));
        u.setAddress(trim(req.getParameter("address")));

        if (u.getName().isEmpty() || u.getEmail().isEmpty() || u.getPassword() == null || u.getPassword().length() < 6) {
            req.setAttribute("alert", "Name, email and a password of at least 6 characters are required");
            req.getRequestDispatcher("register.jsp").forward(req, resp);
            return;
        }
        UserService service = new UserServiceImpl();
        if (service.register(u)) {
            req.setAttribute("alert", "Registration successful. Please login.");
            req.getRequestDispatcher("Login.jsp").forward(req, resp);
        } else {
            req.setAttribute("alert", "Registration failed: name or email already exists");
            req.getRequestDispatcher("register.jsp").forward(req, resp);
        }
    }

    private static String trim(String s) { return s == null ? "" : s.trim(); }
}
