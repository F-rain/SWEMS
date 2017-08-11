package dao.impl;

import dao.TeacherDao;
import db.DBUtil;
import entities.Teacher;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by rick- on 2016/12/1.
 */
public class TeacherDaoImpl implements TeacherDao {

    /**
     * 教师评分的逻辑方法
     * 接收学生学号和成绩两个参数
     * 更新对应学生的Score数据项
     * @param stu_num
     * @param score
     */
    @Override
    public void setScore(String stu_num, double score) {
        Connection conn = DBUtil.getConn();
        Statement statement = null;
        try {
            statement = conn.createStatement();
            //通过statment对象执行一条sql语句更新数据
            statement.executeUpdate("UPDATE student SET Score='"+ score +"' WHERE StuNum='"+ stu_num +"'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            DBUtil.closeConn(conn, statement);
        }
    }

    /**
     * 教师登录的逻辑方法
     * 接收教师工号和密码两个参数
     * 查询匹配结果，匹配成功将数据放入教师对象中
     * 返回一个教师对象
     * @param teac_num
     * @param password
     * @return
     */
    @Override
    public Teacher getTeacher(String teac_num, String password) {
        Teacher teacher = null;
        Connection conn = DBUtil.getConn();
        Statement statement = null;
        try {
            statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM teacher WHERE WorkNum ='"+ teac_num +"' AND password = '"+ password +"'");
        while (resultSet.next()){
            teacher =new Teacher();
            System.out.println(resultSet.getString("WorkNum"));
            teacher.setNum(resultSet.getString("WorkNum"));
            teacher.setName(resultSet.getString("TeacherName"));
        }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            DBUtil.closeConn(conn, statement);
        }
        return teacher;
    }
}
