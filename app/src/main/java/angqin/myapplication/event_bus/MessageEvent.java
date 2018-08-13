package angqin.myapplication.event_bus;

/**
 * 作者：${lixuebin} on 2018/1/22 14:31
 * 邮箱：2072301410@qq.com
 * tip:消息类
 */
public class MessageEvent {
    private String msg;

    public MessageEvent(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
