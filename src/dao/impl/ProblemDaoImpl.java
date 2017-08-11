package dao.impl;

import dao.ProblemDao;
import db.DBUtil;
import entities.Problem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * 处理题目的相关方法实现类
 * Created by rick- on 2016/12/8.
 */
public class ProblemDaoImpl implements ProblemDao {

    /**
     * 从数据库中查询所有题目的数据
     * 存到一个list中并返回
     * 用于获取题目列表
     * @return
     */
    @Override
    public List<Problem> getProblems() {
        List<Problem> problemList = new ArrayList<>();
        Connection conn = DBUtil.getConn();
        Statement statement = null;
        try {
            statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM problem");
            while(resultSet.next()){
                Problem problem = new Problem();
                problem.setNum(resultSet.getString("ProblemNum"));
                problem.setTitle(resultSet.getString("ProblemTitle"));
                problem.setContent(resultSet.getString("ProblemContent"));
                problem.setChooseStable(resultSet.getBoolean("chooseStable"));

                problemList.add(problem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            DBUtil.closeConn(conn, statement);
        }

        return problemList;
    }
}
