package servlet;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import dao.TeacherDao;
import dao.impl.TeacherDaoImpl;
import entities.Teacher;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by rick- on 2016/12/1.
 */
@WebServlet(name = "Teacher_login", value = "/teac_login")
public class teac_login extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Teacher teacher = null;
        TeacherDao teacherDao = new TeacherDaoImpl();
        if (request.getParameter("num")!=null && request.getParameter("password")!=null){
            teacher = teacherDao.getTeacher(request.getParameter("num"), request.getParameter("password"));
        }

        response.setContentType("TEXT/JSON");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(new Gson().toJson(teacher == null?new JsonObject():teacher));
        response.getWriter().close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
