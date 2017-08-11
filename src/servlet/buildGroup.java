package servlet;

import com.google.gson.Gson;
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
 * Created by rick- on 2016/12/14.
 */
@WebServlet(name = "buildGroup", value = "/buildGroup")
public class buildGroup extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StudentDao studentDao = new StudentDaoImpl();
        boolean flag = false;
        if (request.getParameter("num") != null && request.getParameter("GroupName") != null){
            flag = studentDao.buildGroup(request.getParameter("num"), request.getParameter("GroupName"));
        }

        response.setContentType("TEXT/JSON");
        response.setCharacterEncoding("UTF-8");
        System.out.println(new Gson().toJson(flag));
        response.getWriter().write(new Gson().toJson(flag));
        response.getWriter().close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
