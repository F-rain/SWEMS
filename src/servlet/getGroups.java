package servlet;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import dao.GroupDao;
import dao.impl.GroupDaoImpl;
import entities.Group;

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
@WebServlet(name = "getGroups", value = "/getGroups")
public class getGroups extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        GroupDao groupDao = new GroupDaoImpl();
        List<Group> groupList = groupDao.getGroup();

        response.setContentType("TEXT/JSON");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(new Gson().toJson(groupList == null?new JsonObject():groupList));
        response.getWriter().close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
