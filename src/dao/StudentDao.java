package dao;

import entities.Group;
import entities.Student;

/**
 * Created by rick- on 2016/11/30.
 */
public interface StudentDao {
    public Student getStudent(String num, String password);//登录时，成功则返回一个学生对象，失败返回空
    public boolean buildGroup(String num, String GroupName);//创建小组接口， 成功返回true，失败返回false
    public boolean choseGroup(String groupNum, String num);//选择加入小组接口，成功返回true， 失败返回false
    public Group getMyGroup(String num);
    public double getScore(String stu_num);//查看分数接口
}
