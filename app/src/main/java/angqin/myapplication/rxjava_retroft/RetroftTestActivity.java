package angqin.myapplication.rxjava_retroft;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import angqin.myapplication.R;

/**
 * 作者：${lixuebin} on 2018/1/24 15:25
 * 邮箱：2072301410@qq.com
 * TIP : Rxjava + Retroft 网络框架测试
 * <p/>
 * 学习之前，从以下几个角度分析：
 * RxJava如何与Retrofit结合
 * 相同格式的Http请求数据该如何封装
 * 相同格式的Http请求数据统一进行预处理
 * 如何取消一个Http请求 -- 观察者之间的对决，Oberver VS Subscriber
 * 一个需要ProgressDialog的Subscriber该有的样子
 */
public class RetroftTestActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_1;
    private TextView tv_2;
    private TextView tv_3;
    private TextView tv_4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http_retroft);
        initView();
        setListener();
    }

    private void setListener() {
        tv_1.setOnClickListener(this);
        tv_2.setOnClickListener(this);
        tv_3.setOnClickListener(this);
        tv_4.setOnClickListener(this);
    }

    private void initView() {
        tv_1 = (TextView) findViewById(R.id.tv_1);
        tv_2 = (TextView) findViewById(R.id.tv_2);
        tv_3 = (TextView) findViewById(R.id.tv_3);
        tv_4 = (TextView) findViewById(R.id.tv_4);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_1:
                startActivity(new Intent(this, SingleRetroftActivity.class));//单独使用retroft,请求网络
                break;
            case R.id.tv_2:
                startActivity(new Intent(this, RxjavaRetroftActivity.class));//Retrofit和Rxjava的结合，请求网络
                break;
            case R.id.tv_3:
                startActivity(new Intent(this,EncapsulationRetroftActivity.class));//对创建retroft 过程封装，请求网络
                break;
            case R.id.tv_4:
                startActivity(new Intent(this,HttpResultActivity.class));//对相同格式的Http请求数据该如何封装
                break;
        }
    }
}
