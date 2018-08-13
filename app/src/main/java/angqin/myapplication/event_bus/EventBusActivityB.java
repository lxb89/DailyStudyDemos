package angqin.myapplication.event_bus;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import org.greenrobot.eventbus.EventBus;

import angqin.myapplication.R;

/**
 * 作者：${lixuebin} on 2018/1/22 14:52
 * 邮箱：2072301410@qq.com
 */
public class EventBusActivityB extends AppCompatActivity {

    private Button btn_notify_event;
    private MessageEvent messageEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_bus_b);
        btn_notify_event = (Button) findViewById(R.id.btn_notify_event);
        messageEvent = new MessageEvent("我是订阅的事件消息！");
        EventBus.getDefault().post(messageEvent);
        setListener();
    }

    private void setListener() {
        btn_notify_event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {//更新订阅者message
                messageEvent.setMsg("订阅的事件消息已更新为最新！");
                EventBus.getDefault().post(messageEvent);
                finish();
            }
        });
    }
}
