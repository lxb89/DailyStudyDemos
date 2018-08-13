package angqin.myapplication.rxjava_retroft.entity;

import java.util.List;

/**
 * 作者：${lixuebin} on 2018/1/24 16:48
 * 邮箱：2072301410@qq.com
 * tip: 数据实体
 */
public class MoviceEntity<T> {
    // TODO: 2018/1/25  用来模仿resultCode和resultMessage
    private int count;
    private int start;
    private String title;
    private int total;
    //    private List<SubjectsBean> subjects;
    // TODO: 2018/1/25  用来模仿data
    private T subjects;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public T getSubjects() {
        return subjects;
    }

    public void setSubjects(T subjects) {
        this.subjects = subjects;
    }

}
