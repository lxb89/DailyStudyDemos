package angqin.myapplication.databinding;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableArrayMap;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import angqin.myapplication.R;
import angqin.myapplication.databinding.bean.DoubleBindBean;
import angqin.myapplication.databinding.bean.DoubleBindBean2;

/**
 * Created by ${lixuebin} on 2018/8/22.
 * 邮箱：2072301410@qq.com
 * TIP： 数据双向绑定
 */

public class DataBindingTestTwoAty extends AppCompatActivity implements View.OnClickListener {
    private boolean flag;
    private boolean flag_2;
    private DoubleBindBean doubleBindBean;
    private DoubleBindBean2 doubleBindBean2;
    ObservableArrayList<String> observableArrayList = new ObservableArrayList<>();
    ObservableArrayMap<String, Object> objectObservableArrayMap = new ObservableArrayMap<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        angqin.myapplication.databinding.ActivityDatabindingTestTwoBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_databinding_test_two);

        doubleBindBean = new DoubleBindBean("我是原始数据");
        binding.setDoubleBindBean(doubleBindBean);

        doubleBindBean2 = new DoubleBindBean2();
        doubleBindBean2.observableField.set("我是原始数据2");
        binding.setDoubleBindBean2(doubleBindBean2);

        observableArrayList.add("list1");
        observableArrayList.add("list2");
        binding.setList(observableArrayList);

        objectObservableArrayMap.put("key0", "key0_value0");
        objectObservableArrayMap.put("key1", "key0_value1");
        binding.setMap(objectObservableArrayMap);

        binding.setOnClickListener(this);

        binding.tvId.setText("我是通过view id 设置的数据");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_exchange_content:
                flag = !flag;
                if (flag) {
                    doubleBindBean.setContent("我是更新后的数据");
                } else {
                    doubleBindBean.setContent("我是原始数据");
                }
                break;
            case R.id.btn_exchange_content_2:
                flag_2 = !flag_2;
                if (flag_2) {
                    doubleBindBean2.observableField.set("我是更新后的数据2");
                } else {
                    doubleBindBean2.observableField.set("我是原始数据2");
                }
                break;
            case R.id.btn_exchange_content_3:
                observableArrayList.set(0, "after change list");
                break;
            case R.id.btn_exchange_content_4:
                objectObservableArrayMap.put("key0", "after change key0_value0");
                break;
        }
    }
}
