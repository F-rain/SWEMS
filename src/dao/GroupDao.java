package dao;

import entities.Group;

import java.util.List;

/**
 * Created by rick- on 2016/11/30.
 */
public interface GroupDao {
    public boolean choseProblem(String group_num, String problem_num);
    public List<Group> getGroup();
    public Group getMyGroup(String group_num);
}
