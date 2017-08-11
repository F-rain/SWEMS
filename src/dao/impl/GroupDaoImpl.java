package dao.impl;

import dao.GroupDao;
import db.DBUtil;
import entities.Group;
import entities.Student;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rick- on 2016/12/1.
 */
public class GroupDaoImpl implements GroupDao {

    /**
     * 选择题目方法
     * 接收小组编号和问题序号两个参数
     * 更新对应小组的ProblemNum数据，
     * 更新对应Problem的chooseStable数据为true
     * @param group_num
     * @param problem_num
     * @return
     */
    @Override
    public boolean choseProblem(String group_num, String problem_num) {
        boolean flag = false;
        Connection conn = DBUtil.getConn();
        Statement statement = null;
        try {
            statement = conn.createStatement();
            Statement stmt1 = conn.createStatement();
            Statement stmt2 = conn.createStatement();
            ResultSet resultSet_group = stmt1.executeQuery("SELECT ProblemNum FROM `group` WHERE GroupNum='"+ group_num +"'");
            ResultSet resultSet_problem = stmt2.executeQuery("SELECT chooseStable FROM problem WHERE ProblemNum='"+ problem_num +"'");
            resultSet_group.next();
            resultSet_problem.next();

            System.out.println("ProblemNum"+ resultSet_group.getString("ProblemNum") + "chooseStable" + resultSet_problem.getBoolean("chooseStable"));
            if (resultSet_group.getString("ProblemNum").equals("") && !resultSet_problem.getBoolean("chooseStable")) {
                statement.executeUpdate("UPDATE `group` SET ProblemNum='" + problem_num + "' WHERE GroupNum='" + group_num + "'");
                statement.executeUpdate("UPDATE problem SET chooseStable='1' WHERE ProblemNum='" + problem_num + "'");
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            DBUtil.closeConn(conn, statement);
        }

        return flag;
    }

    /**
     * 获取小组列表方法
     * 查询数据库中所有的小组数据
     * 放入一个groupList中并返回
     * @return
     */
    @Override
    public List<Group> getGroup() {
        List<Group> groupList = new ArrayList<>();
        Connection conn = DBUtil.getConn();
        Statement statement = null;
        Statement stemt = null;
        try {
            statement = conn.createStatement();
            stemt = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM `group`");
            while (resultSet.next()){
                Group group = new Group();

                group.setNum(resultSet.getString("GroupNum"));
                group.setName(resultSet.getString("GroupName"));
                List<Student> studentList = new ArrayList<>();
                for (int i = 0; i<4; i++){
                    if (resultSet.getString("menberNum"+i) != null) {
                        Student student = new Student();
                        student.setNum(resultSet.getString("menberNum" + i));
                        ResultSet resultSet1 = stemt.executeQuery("SELECT * FROM student WHERE StuNum='" + student.getNum() + "'");
                        while (resultSet1.next()) {
                            student.setName(resultSet1.getString("StuName"));
                            student.setGroup_num(resultSet1.getString("GroupNum"));
                            student.setScore(resultSet1.getDouble("Score"));
                            student.setLeader(resultSet1.getBoolean("IsLeader"));
                            studentList.add(student);
                        }

                    }
                }
                group.setMember(studentList);
                group.setProblem_num(resultSet.getString("ProblemNum"));

                groupList.add(group);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            DBUtil.closeConn(conn, statement);
        }

        return groupList;
    }

    /**
     * 获取单个小组方法
     * 接收一个小组编号座位参数
     * 查询对应的小组数据并返回一个group对象
     * @param group_num
     * @return
     */
    @Override
    public Group getMyGroup(String group_num) {
        Group group = new Group();
        Connection conn = DBUtil.getConn();
        Statement statement = null;
        Statement stemt = null;
        try {
            statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM `group` WHERE GroupNum='"+group_num+"'");
            while (resultSet.next()){
                group.setNum(group_num);
                group.setName(resultSet.getString("GroupName"));
                group.setProblem_num(resultSet.getString("ProblemNum"));

                List<Student> studentList = new ArrayList<>();
                for (int i = 0; i<4; i++){
                    if (!resultSet.getString("menberNum"+i).equals("")) {
                        Student student = new Student();
                        student.setNum(resultSet.getString("menberNum" + i));
                        stemt = conn.createStatement();
                        ResultSet resultSet1 = stemt.executeQuery("SELECT * FROM student WHERE StuNum='" + student.getNum() + "'");
                        while (resultSet1.next()) {
                            student.setName(resultSet1.getString("StuName"));
                            student.setGroup_num(resultSet1.getString("GroupNum"));
                            student.setScore(resultSet1.getDouble("Score"));
                            student.setLeader(resultSet1.getBoolean("IsLeader"));
                        }

                        studentList.add(student);
                    }
                }
                group.setMember(studentList);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            DBUtil.closeConn(conn, statement);
        }

        return group;
    }

}
