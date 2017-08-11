package dao;

import entities.Teacher;

/**
 * Created by rick- on 2016/11/30.
 */
public interface TeacherDao {
    public void setScore(String stu_num, double score);
    public Teacher getTeacher(String teac_num, String password);
}
