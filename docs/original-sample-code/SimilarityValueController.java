package controller;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.AdminManageService;
import service.AdminManageServiceImpl;
@WebServlet("/similarityValue")
public class SimilarityValueController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		AdminManageService service = new AdminManageServiceImpl();
		int[] i = service.getSimilarityValue();
		System.out.println(i[0]);//print the value
		if (i != null) {
			int Romance = i[0] * 10;
			int Comedy = i[1] * 10;
			int Drama = i[2] * 10;
			int Animation = i[3] * 10;
			int Fantasy = i[4] * 10;
			int Action = i[5] * 10;
			req.setAttribute("Romance", Romance);
			req.setAttribute("Comedy", Comedy);
			req.setAttribute("Drama", Drama);
			req.setAttribute("Animation", Animation);
			req.setAttribute("Fantasy", Fantasy);
			req.setAttribute("Action", Action);
		}		req.getRequestDispatcher("similarValue.jsp").forward(req, resp);
	}

}
