package dao.impl;

import dao.StudentDao;
import db.DBUtil;
import entities.Group;
import entities.Student;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * Created by rick- on 2016/12/1.
 */
public class StudentDaoImpl implements StudentDao {

    /**
     * 接收学生学号和密码参数，去数据库中查询学生数据
     * 判断学号和密码匹配时，返回一个学生的对象
     * 学号和密码不匹配时，返回一个null
     * @param num
     * @param password
     * @return
     */
    @Override
    public Student getStudent(String num, String password) {
        Student student = null;
        Connection conn = DBUtil.getConn();
        Statement statement = null;
        try {
            //获取statement对象
            statement = conn.createStatement();
            //获取查询结果
            ResultSet resultSet = statement.executeQuery("SELECT * FROM student WHERE StuNum = '" + num + "' AND password= '" + password + "'");

            //循环查询结果
            while(resultSet.next()){
                //匹配成功将数据库中数据放入学生对象里
                student = new Student();
                System.out.println(resultSet.getString("StuName"));
                student.setNum(resultSet.getString("StuNum"));
                student.setName(resultSet.getString("StuName"));
                if (resultSet.getDouble("Score") != 0) {
                    student.setScore(resultSet.getDouble("Score"));
                }
                if (resultSet.getString("GroupNum") != null) {
                    student.setGroup_num(resultSet.getString("GroupNum"));
                }
                student.setLeader(resultSet.getBoolean("IsLeader"));
                System.out.println(student.getName());
                //关闭数据库连接，释放资源
                //resultSet.close();
                //statement.close();
                //conn.close();
                break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            DBUtil.closeConn(conn, statement);
        }

        return student;
    }

    /**
     * 新建小组方法
     * 先检查数据库中小组数量是否超过规定数值
     * 如果没超过就在小组表中插入一条新的数据
     * 成功返回true，否则返回false
     * @return
     */
    @Override
    public boolean buildGroup(String num, String GroupName) {
        List<Group> groupList = new GroupDaoImpl().getGroup();
        if (groupList.size() <= 10){
            Connection conn = DBUtil.getConn();
            Statement statement = null;
            try {
                statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT GroupNum FROM student WHERE StuNum='"+ num +"'");
                resultSet.next();
                System.out.println("判断该成员是否有组号 " + resultSet.getString("GroupNum"));
                if (resultSet.getString("GroupNum").equals("")) {
                    statement.executeUpdate("INSERT INTO `group`(GroupNum, GroupName, menberNum0) VALUE ('" + (groupList.size() + 1) + "', '" + GroupName + "', '" + num + "')");
                    statement.executeUpdate("UPDATE student SET GroupNum='" + (groupList.size() + 1) + "' WHERE StuNum='" + num + "'");
                    statement.executeUpdate("UPDATE student SET IsLeader='1' WHERE StuNum='" + num + "'");
                    return true;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            finally {
                DBUtil.closeConn(conn, statement);
            }
        }
        return false;
    }

    /**
     * 选择小组方法
     * 接收小组编号和学生学号两个参数
     * 通过小组编号更新小组表中对应组的成员数据
     * 然后更新该学生的个人数据中的小组编号数据
     * @param groupNum
     * @return
     */
    @Override
    public boolean choseGroup(String groupNum, String num) {
        Connection conn = DBUtil.getConn();
        Statement statement = null;
        try {
            statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT GroupNum FROM student WHERE StuNum='"+ num +"'");
            resultSet.next();
            System.out.println("判断该成员是否有组号 " + resultSet.getString("GroupNum"));
            if (resultSet.getString("GroupNum").equals("")) {
                Group group = new GroupDaoImpl().getMyGroup(groupNum);
                List<Student> studentList = group.getMember();
                System.out.println("获取了小组的成员列表 "+studentList.size());
                if (studentList.size() < 4) {
                    System.out.println("开始执行更新");
                    statement.executeUpdate("UPDATE `group` SET menberNum" + studentList.size() + "='"+ num +"' WHERE GroupNum='"+ groupNum+"'");
                    statement.executeUpdate("UPDATE student SET GroupNum='" + groupNum + "' WHERE StuNum='" + num + "'");
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            DBUtil.closeConn(conn, statement);
        }

        return false;
    }

    /**
     * 接收学生学号参数，查询该学生对应的小组编号
     * 再通过GroupDaoImpl的getMyGroup方法获取对应小组的详细数据
     * 返回小组对象
     * @param num
     * @return
     */
    @Override
    public Group getMyGroup(String num) {
        Group group = new Group();
        Connection conn = DBUtil.getConn();
        Statement statement = null;
        try {
            statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT GroupNum FROM student WHERE StuNum='"+num+"'");
            while (resultSet.next()) {
                group.setNum(resultSet.getString("GroupNum"));
                group = new GroupDaoImpl().getMyGroup(group.getNum());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            DBUtil.closeConn(conn, statement);
        }

        return group;
    }

    /**
     * 获取学生分数方法
     * 接收一个学生学号为参数
     * 去数据库中查询对应学生的分数并返回
     * @param stu_num
     * @return
     */
    @Override
    public double getScore(String stu_num) {
        double score = 0.0;

        Connection conn = DBUtil.getConn();
        Statement statement = null;
        try {
            statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT Score FROM student WHERE StuNum='"+ stu_num +"'");
            resultSet.next();
            score = resultSet.getDouble("Score");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            DBUtil.closeConn(conn, statement);
        }

        return score;
    }
}
