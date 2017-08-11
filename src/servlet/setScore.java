package servlet;

import dao.TeacherDao;
import dao.impl.TeacherDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by rick- on 2016/12/1.
 */
@WebServlet(name = "setScore", value = "/setScore")
public class setScore extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TeacherDao teacherDao = new TeacherDaoImpl();

        if (request.getParameter("stu_num") != null && request.getParameter("score") != null){
            teacherDao.setScore(request.getParameter("stu_num"), Double.parseDouble(request.getParameter("score")));
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
