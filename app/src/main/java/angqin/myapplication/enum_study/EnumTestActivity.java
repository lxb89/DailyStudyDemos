package angqin.myapplication.enum_study;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.Map;

import angqin.myapplication.R;

/**
 * Created by ${lixuebin} on 2018/7/10.
 * 邮箱：2072301410@qq.com
 * TIP： enum 探究类
 */

public class EnumTestActivity extends AppCompatActivity {

    private static final String TAG = "EnumTestActivity";
    private Toolbar toolbar;
    private Button btn_test;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enum_test);
        initToolBar();
    }

    private void initToolBar() {
        toolbar = findViewById(R.id.toolbar);
        btn_test = findViewById(R.id.btn_test);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btn_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (EnumTest enumTest : EnumTest.values()) {
//                    Toast.makeText(getApplication(), "遍历出来的每个枚举是：--》》" +enumTest , Toast.LENGTH_SHORT).show();
                    Log.d(TAG, enumTest.toString());
                }
            }
        });
        System.out.println("---------------我是分割线-----------------");
        EnumTest em = EnumTest.TUS;
        switch (em) {
            case MON:
                Log.d(TAG, "今天是星期一");
                break;
            case TUS:
                Log.d(TAG, "今天是星期二");
                break;
            case WED:
                Log.d(TAG, "今天是星期三");
                break;
            case THU:
                Log.d(TAG, "今天是星期四");
                break;
            case FRI:
                Log.d(TAG, "今天是星期五");
                break;
            case SAT:
                Log.d(TAG, "今天是星期六");
                break;
            case SUN:
                Log.d(TAG, "今天是星期日");
                break;
        }
        //比较枚举与其他对象的顺序
        switch (em.compareTo(EnumTest.MON)) {
            case -1:
                Log.d(TAG, "TUS 在 MON 之前");
                break;
            case 1:
                Log.d(TAG, "TUS 在 MON 之后");
                break;
            case 0:
                Log.d(TAG, "TUS 与 MON 同意位置上");
                break;
            default:
                Log.d(TAG, "TUS 在 MON 不清楚");
                break;
        }
        //枚举的常见方法使用
        Log.d(TAG, "getDeclaringClass----->>" + em.getDeclaringClass());
        Log.d(TAG, "name----->>" + em.name());
        Log.d(TAG, "ordinal----->>" + em.ordinal());
        Log.d(TAG, "toString----->>" + em.toString());
        Log.d(TAG, "valueOf----->>" + em.valueOf("TUS"));

        System.out.println("---------------EnumSet-----------------");

        //EnumSet 使用
        EnumSet<EnumTest> enumTests = EnumSet.allOf(EnumTest.class);
        for (EnumTest day : enumTests) {
            System.out.println(TAG + "===========" + day);
        }

        System.out.println("---------------EnumMap-----------------");
        //EnumMap 使用
        EnumMap<EnumTest, String> enumMap = new EnumMap(EnumTest.class);
        enumMap.put(EnumTest.MON,"星期一");
        enumMap.put(EnumTest.TUS,"星期二");
        enumMap.put(EnumTest.WED,"星期三");
//        enumMap.put(EnumTest.THU,"星期四");
//        enumMap.put(EnumTest.FRI,"星期五");
//        enumMap.put(EnumTest.SAT,"星期六");
//        enumMap.put(EnumTest.SUN,"星期日");
        Iterator<Map.Entry<EnumTest, String>> iterator = enumMap.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<EnumTest, String> entry = iterator.next();
            System.out.println("Key=========:" + entry.getKey());
            System.out.println("Value=========:" + entry.getValue());
        }

        Button btn_instance = (Button) findViewById(R.id.btn_instance);
        btn_instance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),EnumInstanceActivity.class);
                startActivity(intent);
            }
        });
    }
}
