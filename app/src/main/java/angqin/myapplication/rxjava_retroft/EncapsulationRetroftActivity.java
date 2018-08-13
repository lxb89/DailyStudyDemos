package angqin.myapplication.rxjava_retroft;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import angqin.myapplication.R;
import angqin.myapplication.rxjava_retroft.entity.MoviceEntity;
import angqin.myapplication.rxjava_retroft.request.HttpMethods;
import angqin.myapplication.rxjava_retroft.service.MoviceService;
import rx.Subscriber;

/**
 * 作者：${lixuebin} on 2018/1/25 10:10
 * 邮箱：2072301410@qq.com
 * TIP :进一步优化
 * 把创建Retrofit的过程封装一下，然后希望Activity创建Subscriber对象传进来。
 */
public class EncapsulationRetroftActivity extends AppCompatActivity implements View.OnClickListener {
    private final String TAG = this.getClass().getSimpleName();
    private Button btn_request;
    private TextView tv_result;
    private Subscriber<MoviceEntity> subscriber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encapsulation_retroft);
        initView();
        setListener();
    }


    private void initView() {
        btn_request = (Button) findViewById(R.id.btn_request);
        tv_result = (TextView) findViewById(R.id.tv_result);

    }

    private void setListener() {
        btn_request.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {//请求网络数据
        subscriber = new Subscriber<MoviceEntity>() {
            @Override
            public void onCompleted() {
                Log.e(TAG,"onCompleted-->>>");
                Toast.makeText(EncapsulationRetroftActivity.this, "Get Top Movie Completed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, e.getMessage());
                tv_result.setText(e.getMessage());
            }

            @Override
            public void onNext(MoviceEntity moviceEntity) {
                Log.e(TAG,moviceEntity.getTitle());
                tv_result.setText(moviceEntity.getCount());
            }
        };
//        HttpMethods.getInstance().getMovice(subscriber, 0, 10);

    }
}
