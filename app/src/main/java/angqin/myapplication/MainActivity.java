package angqin.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import angqin.myapplication.event_bus.EventBusActivityA;
import angqin.myapplication.proxy.ProxyTestActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final String TAG = getClass().getSimpleName();
    private Button evb;
    private Button btn_proxy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // EventBus 使用测试
        findView();
        setListener();
    }

    private void findView() {
        evb = (Button) findViewById(R.id.evb);
        btn_proxy = (Button) findViewById(R.id.btn_proxy);
    }

    private void setListener() {
        evb.setOnClickListener(this);
        btn_proxy.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.evb://测试eventbus
                startActivity(new Intent(this, EventBusActivityA.class));
                break;
            case R.id.btn_proxy:
                startActivity(new Intent(this, ProxyTestActivity.class));
                break;

        }
    }
}
