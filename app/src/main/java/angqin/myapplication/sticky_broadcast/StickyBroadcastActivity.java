package angqin.myapplication.sticky_broadcast;

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

import angqin.myapplication.R;
import angqin.myapplication.event_bus.EventBusActivityA;

/**
 * 作者：${lixuebin} on 2018/1/23 11:20
 * 邮箱：2072301410@qq.com
 *  tip : sendStickyBroadcast 测试
 *  android  广播分类：普通广播、有序广播、异步广播（粘性广播）
 *  区别：
 *  1、普通广播：
 *          同级别接受先后顺序随机（无序）
 *          级别低的后接受到广播
 *          不能拦截广播传递，也不能处理广播
 *          同级别动态注册高于静态注册
 *  2、有序广播：
 *          同级别接受先后顺序随机
 *          级别低的后接受到广播
 *          能拦截广播传递，能处理广播
 *          同级别动态注册高于静态注册
 *  3、粘性广播
 *          一直存在
 *          可以先发送广播，再注册广播接收器
 *          需要在清单文件中，配置android.permission.BROADCAST_STICKY权限
 *
 */
public class StickyBroadcastActivity extends AppCompatActivity {

    private final String TAG = this.getClass().getSimpleName();
    private Button btn_sticky,btn_normal;
    private TextView tv_result;
    private LocalBroadcastManager localBroadcastManager;
    private LocalReceiver localReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sticky_broadcast);
        sendBroadcast();
        btn_sticky = (Button) findViewById(R.id.btn_sticky);
        btn_normal = (Button) findViewById(R.id.btn_normal);
        tv_result = (TextView) findViewById(R.id.tv_result);
        btn_sticky.setOnClickListener(new View.OnClickListener() {//注册粘性广播
            @Override
            public void onClick(View view) {
                registerReceiver(stickyReceiver,new IntentFilter("com.example.stickybroadcastdemo.stickybrocast"));
            }
        });
        btn_normal.setOnClickListener(new View.OnClickListener() {//注册普通广播
            @Override
            public void onClick(View view) {
                localBroadcastManager.sendBroadcast(new Intent("com.example.stickybroadcastdemo.normalbrocast"));
//                startActivity(new Intent(getApplicationContext(), EventBusActivityA.class));
            }
        });
        mReceiverBroadcast();
    }

    private void sendBroadcast() {
        localBroadcastManager = LocalBroadcastManager.getInstance(this);
        sendStickyBroadcast(new Intent("com.example.stickybroadcastdemo.stickybrocast"));
    }

    BroadcastReceiver stickyReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.e(TAG,"粘性广播");
            tv_result.setText("粘性广播");
        }
    };
    private void mReceiverBroadcast() {
        localReceiver = new LocalReceiver();
        LocalBroadcastManager.getInstance(this)
                .registerReceiver(localReceiver, new IntentFilter("com.example.stickybroadcastdemo.normalbrocast"));
    }


    @Override
    protected void onDestroy() {
        unregisterReceiver(stickyReceiver);
        localBroadcastManager.unregisterReceiver(localReceiver);
        super.onDestroy();
    }

    class LocalReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.e(TAG, "本地广播");
            Toast.makeText(context, "received local broadcast", Toast.LENGTH_SHORT).show();
        }
    }
}
