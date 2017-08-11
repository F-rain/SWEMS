package entities;

import java.util.Date;

/**
 * 课设报告实体类
 * Created by rick- on 2016/11/30.
 */
public class Report {
    private String title;
    private String time;
    private String url;
    private String GroupNum;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getGroupNum() {
        return GroupNum;
    }

    public void setGroupNum(String groupNum) {
        GroupNum = groupNum;
    }
}
