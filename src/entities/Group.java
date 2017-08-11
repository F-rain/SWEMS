package entities;

import java.util.List;

/**
 * 小组实体类
 * Created by rick- on 2016/11/30.
 */
public class Group {
    private String num;
    private String name;
    private String problem_num;
    private List<Student> member;

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProblem_num() {
        return problem_num;
    }

    public void setProblem_num(String problem_num) {
        this.problem_num = problem_num;
    }

    public List<Student> getMember() {
        return member;
    }

    public void setMember(List<Student> member) {
        this.member = member;
    }
}
