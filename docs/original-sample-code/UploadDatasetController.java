package controller;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import bean.DataSetBean;
import service.ManageDataSetService;
import service.ManageDataSetServiceImpl;
@WebServlet("/uploadDataset")
public class UploadDatasetController extends HttpServlet {
	private static final long serialVersionUID = -6316003392660605616L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		DataSetBean bean = new DataSetBean();
		bean.setFrom_user(req.getParameter("name"));
		bean.setDataset_name(req.getParameter("dataSetName"));
		bean.setDataset(req.getParameter("dataSet"));
		bean.setSubject(req.getParameter("subject"));
		bean.setMessage(req.getParameter("message"));
		HttpSession session = req.getSession();
		bean.setFrom_user((String) session.getAttribute("uname"));
		;
		ManageDataSetService service = new ManageDataSetServiceImpl();
		boolean result = service.uploadDataSet(bean);
		if (result != false) {
			req.setAttribute("alert", "Uploaded Successfull");
		} else {
			req.setAttribute("alert", "Uploaded Failed");
		}
		req.getRequestDispatcher("uploadDataSet.jsp").forward(req, resp);
	}
}
