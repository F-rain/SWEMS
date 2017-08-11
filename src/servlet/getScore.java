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
 * Created by rick- on 2016/12/1.
 */
@WebServlet(name = "getScore", value = "/getScore")
public class getScore extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StudentDao studentDao = new StudentDaoImpl();
        double score = 0.0;
        if (request.getParameter("num") != null){
            score = studentDao.getScore(request.getParameter("num"));
        }

        response.setContentType("TEXT/JSON");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(new Gson().toJson(score));
        response.getWriter().close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
