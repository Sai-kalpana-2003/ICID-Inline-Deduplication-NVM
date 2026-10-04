package controller;

import bean.DataSetBean;
import bean.UploadResult;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import service.ManageDataSetService;
import service.ManageDataSetServiceImpl;

/** Receives a text file (or pasted text), runs inline deduplication, shows the result. */
@WebServlet("/uploadDataset")
@MultipartConfig(maxFileSize = 1024 * 1024)   // 1 MB limit
public class UploadDatasetController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("uploadDataSet.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();

        String name = req.getParameter("dataSetName");
        String text = req.getParameter("dataSet");
        Part file = req.getPart("dataSetFile");
        if (file != null && file.getSize() > 0) {
            text = readAll(file.getInputStream());
            if (name == null || name.trim().isEmpty()) name = file.getSubmittedFileName();
        }

        if (text == null || text.trim().isEmpty()) {
            req.setAttribute("alert", "Please choose a text file or paste some text");
            req.getRequestDispatcher("uploadDataSet.jsp").forward(req, resp);
            return;
        }
        if (name == null || name.trim().isEmpty()) name = "untitled";

        DataSetBean bean = new DataSetBean();
        bean.setFrom_user((String) session.getAttribute("uname"));
        bean.setDataset_name(name.trim());
        bean.setDataset(text);

        ManageDataSetService service = new ManageDataSetServiceImpl();
        UploadResult result = service.uploadDataSet(bean);

        req.setAttribute("result", result);
        req.getRequestDispatcher("uploadDataSet.jsp").forward(req, resp);
    }

    private static String readAll(InputStream in) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buf = new byte[8192];
        int n;
        while ((n = in.read(buf)) != -1) out.write(buf, 0, n);
        return new String(out.toByteArray(), StandardCharsets.UTF_8);
    }
}
