package servlet;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import dao.ProblemDao;
import dao.impl.ProblemDaoImpl;
import entities.Problem;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by rick- on 2016/12/1.
 */
@WebServlet(name = "getProblems", value = "/getProblems")
public class getProblems extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProblemDao problemDao = new ProblemDaoImpl();
        List<Problem> problemList = problemDao.getProblems();

        response.setContentType("TEXT/JSON");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(new Gson().toJson(problemList==null?new JsonObject():problemList));
        response.getWriter().close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
