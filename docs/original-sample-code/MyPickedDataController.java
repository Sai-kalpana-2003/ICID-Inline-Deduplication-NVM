package controller;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import bean.DataSetBean;
import service.ManageDataSetService;
import service.ManageDataSetServiceImpl;
@WebServlet("/myPicked")
public class MyPickedDataController extends HttpServlet {
	private static final long serialVersionUID = -7814006161653354225L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String uname = (String) session.getAttribute("uname");

		ManageDataSetService service = new ManageDataSetServiceImpl();
		List<DataSetBean> list = service.getMyPicked(uname);

		if (list != null) {
			req.setAttribute("list", list);
		}
		req.getRequestDispatcher("myPicked.jsp").forward(req, resp);
	}
}
