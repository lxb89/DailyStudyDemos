package angqin.myapplication.event_bus;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import angqin.myapplication.R;

/**
 * 作者：${lixuebin} on 2018/1/22 14:41
 * 邮箱：2072301410@qq.com
 */
public class EventBusActivityA extends AppCompatActivity {
    private final String TAG = this.getClass().getSimpleName();
    private Button btn_register_event;
    private TextView tv_receiver_message;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_bus_a);
        btn_register_event = (Button) findViewById(R.id.btn_register_event);
        tv_receiver_message = (TextView) findViewById(R.id.tv_receiver_message);
        btn_register_event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {//注册事件
                startActivity(new Intent(getApplicationContext(), EventBusActivityB.class));
            }
        });
        EventBus.getDefault().register(this);
    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    public void onShowMessageEvent(MessageEvent messageEvent) {//获取消息
        Log.d(TAG, "====事件消息======" + messageEvent.getMsg());
        Log.e("PostThread", Thread.currentThread().getName());
        tv_receiver_message.setText(messageEvent.getMsg());
        Toast.makeText(getApplicationContext(), messageEvent.getMsg(), Toast.LENGTH_SHORT).show();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEventMainThread(MessageEvent messageEvent) {
        Log.e("MainThread", Thread.currentThread().getName());
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onMessageEventBackgroundThread(MessageEvent messageEvent) {
        Log.e("BackgroundThread", Thread.currentThread().getName());
    }

    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void onMessageEventAsync(MessageEvent messageEvent) {
        Log.e("Async", Thread.currentThread().getName());
    }


    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

}
