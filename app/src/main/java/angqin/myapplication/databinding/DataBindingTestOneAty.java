package angqin.myapplication.databinding;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

import angqin.myapplication.R;

/**
 * Created by ${lixuebin} on 2018/8/21.
 * 邮箱：2072301410@qq.com
 * TIP： DataBinding 学习
 */

public class DataBindingTestOneAty extends AppCompatActivity {

    private static final String URL_USER_PIC = "http://ww1.sinaimg.cn/large/0065oQSqly1fszxi9lmmzj30f00jdadv.jpg";
    private angqin.myapplication.databinding.ActivityDatabindingTestBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_databinding_test);
        UserBean userBean = new UserBean(URL_USER_PIC, "张鹏", "", 18);
        binding.setUser(userBean);
        //databinding 引入一些高级变量Variable 使用
        //list
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("list1");
        arrayList.add("list2");
        binding.setList(arrayList);
        //map
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("key0", "value_map0");
        hashMap.put("key1", "value_map1");
        hashMap.put("key2", "value_map2");
        binding.setMap(hashMap);
        //array
        String[] strArray = {"array0", "array1", "array2"};
        binding.setArray(strArray);
        //databinding 设置别名  Alias
        angqin.myapplication.databinding.bean.UserBean userBean2 =
                new angqin.myapplication.databinding.bean.UserBean("我是同名不同包下的 userBean2", "", 18);
        binding.setUser2(userBean2);
        // 在include中的使用  ----->>>>>>   Data binding不支持直接包含merge 节点
        // TODO: 2018/8/21   xml中查阅
        // databinding  事件处理
        binding.setHandler(new OnClickHandler());
        //BindingAdapter注解设置自定义属性
        // TODO: 2018/8/22   xml中查阅

        /**
         *  数据对象 (Data Objects)
         * DataBinding动态更新数据的2种方式
         * BaseObservable
         * ObservableFields
         *
         */

    }
}
