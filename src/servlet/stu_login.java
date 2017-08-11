package servlet;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import dao.StudentDao;
import dao.impl.StudentDaoImpl;
import entities.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 处理学生登录的servlet
 * Created by rick- on 2016/12/1.
 */
@WebServlet(name = "Student_login", value = "/stu_login")
public class stu_login extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Student student = null;
        StudentDao studentDao = new StudentDaoImpl();
        if (request.getParameter("num") != null && request.getParameter("password") != null){
            student = studentDao.getStudent(request.getParameter("num"), request.getParameter("password"));
        }

        response.setContentType("TEXT/JSON");
        response.setCharacterEncoding("UTF-8");
        System.out.println(new Gson().toJson(student));
        response.getWriter().write(new Gson().toJson(student == null?new JsonObject():student));
        response.getWriter().close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
