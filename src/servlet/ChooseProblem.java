package servlet;

import com.google.gson.Gson;
import dao.GroupDao;
import dao.impl.GroupDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by rick- on 2016/12/14.
 */
@WebServlet(name = "ChooseProblem", value = "/chooseProblem")
public class ChooseProblem extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        GroupDao groupDao = new GroupDaoImpl();
        boolean flag = false;
        if (request.getParameter("Group_num") != null && request.getParameter("Problem_num") != null){
            flag = groupDao.choseProblem(request.getParameter("Group_num"), request.getParameter("Problem_num"));
        }

        response.setContentType("TEXT/JSON");
        response.setCharacterEncoding("UTF-8");
        System.out.println(flag);
        response.getWriter().write(new Gson().toJson(flag));
        response.getWriter().close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
