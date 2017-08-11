package servlet;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import dao.ReportDao;
import dao.impl.ReportDaoImpl;
import entities.Report;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 获取已上传报告的list
 * Created by rick- on 2016/12/17.
 */
@WebServlet(name = "getReportList", value = "/getReportList")
public class getReportList extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Report> reportList = null;
        ReportDao reportDao = new ReportDaoImpl();
        reportList = reportDao.getReportList();

        response.setContentType("TEXT/JSON");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(new Gson().toJson(reportList == null?new JsonObject():reportList));
        response.getWriter().close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
