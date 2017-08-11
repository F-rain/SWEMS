package entities;

/**
 * 课设题目实体类
 * Created by rick- on 2016/11/30.
 */
public class Problem {
    private String num;
    private String title;
    private String content;
    private boolean chooseStable;

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isChooseStable() {
        return chooseStable;
    }

    public void setChooseStable(boolean chooseStable) {
        this.chooseStable = chooseStable;
    }
}
