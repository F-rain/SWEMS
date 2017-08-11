package servlet;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import dao.GroupDao;
import dao.StudentDao;
import dao.impl.GroupDaoImpl;
import dao.impl.StudentDaoImpl;
import entities.Group;
import entities.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by rick- on 2016/12/1.
 */
@WebServlet(name = "getMyGroup", value = "/getMyGroup")
public class getMyGroup extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StudentDao studentDao = new StudentDaoImpl();
        Group group = null;
        if (request.getParameter("num") != null) {
            group = studentDao.getMyGroup(request.getParameter("num"));
        }

        response.setContentType("TEXT/JSON");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(new Gson().toJson(group == null?new JsonObject():group));
        response.getWriter().close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
