package entities;

/**
 * 学生实体类
 * Created by rick- on 2016/11/30.
 */
public class Student {
    private String num;
    private String name;
    private double score;
    private String group_num;
    private boolean isLeader;

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

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getGroup_num() {
        return group_num;
    }

    public void setGroup_num(String group_num) {
        this.group_num = group_num;
    }

    public boolean isLeader() {
        return isLeader;
    }

    public void setLeader(boolean leader) {
        isLeader = leader;
    }
}
