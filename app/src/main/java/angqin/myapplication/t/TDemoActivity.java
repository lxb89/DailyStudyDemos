package angqin.myapplication.t;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import angqin.myapplication.R;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 作者：${lixuebin} on 2018/1/29 10:24
 * 邮箱：2072301410@qq.com
 */
public class TDemoActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    @Bind(R.id.btn_test)
    Button btnTest;
    @Bind(R.id.lv)
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.t_demo);
         ButterKnife.bind(this);
        setUpUi();
        initData();
    }

    private void initData() {

    }

    private void setUpUi() {
        //模拟数据
        List mData = new ArrayList<TBean>();
        for (int i = 0; i<15;i++){
            mData.add(new TBean("我是标题",R.drawable.ic_launcher));
        }
        TDemoAdapter demoAdapter = new TDemoAdapter(this,R.layout.item_t_demo,mData);
        lv.setAdapter(demoAdapter);
        lv.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        startActivity(new Intent(this,TLimitedSimpleActivity.class));
    }
}
