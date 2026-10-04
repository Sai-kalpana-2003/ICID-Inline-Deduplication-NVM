package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.AdminManageService;
import service.AdminManageServiceImpl;

/** Statistics page: how many uploads fell in each similarity band. */
@WebServlet("/similarityValue")
public class SimilarityValueController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AdminManageService service = new AdminManageServiceImpl();
        int[] i = service.getSimilarityValue();
        if (i != null) {
            req.setAttribute("band0", i[0]);   // 0-19%
            req.setAttribute("band1", i[1]);   // 20-39%
            req.setAttribute("band2", i[2]);   // 40-59%
            req.setAttribute("band3", i[3]);   // 60-79%
            req.setAttribute("band4", i[4]);   // 80-99%
            req.setAttribute("band5", i[5]);   // 100% (exact)
        }
        req.getRequestDispatcher("similarValue.jsp").forward(req, resp);
    }
}
