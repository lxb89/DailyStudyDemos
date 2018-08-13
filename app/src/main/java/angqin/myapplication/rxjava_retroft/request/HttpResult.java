package angqin.myapplication.rxjava_retroft.request;

/**
 * 作者：${lixuebin} on 2018/1/25 11:57
 * 邮箱：2072301410@qq.com
 */
public class HttpResult<T> {
    private int count;
    private int start;
    private int total;
    private String title;

    //用来模仿Data
    private T subjects;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

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
