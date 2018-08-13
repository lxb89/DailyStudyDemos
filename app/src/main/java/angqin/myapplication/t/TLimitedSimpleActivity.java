package angqin.myapplication.t;

import android.os.Bundle;
import android.renderscript.Sampler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import angqin.myapplication.R;
import angqin.myapplication.builder.Config;
import angqin.myapplication.synchronize.Test;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作者：${lixuebin} on 2018/1/29 17:32
 * 邮箱：2072301410@qq.com
 * tip: 受限泛型 定义及固、规范（设置上限）
 */
public class TLimitedSimpleActivity extends AppCompatActivity {
    private final String TAG = this.getClass().getSimpleName();
    @Bind(R.id.btn_test)
    Button btnTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_t_limited_simple);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.btn_test)
    public void onViewClicked() {
        // 1,泛型 使用通配符
        Info<String> info = new Info<>();  //使用String 为泛型类型
        info.setVar("泛型通配符使用规则");
        mFuncation(info);
        //2、受限泛型--->设置上限
        Info<Integer> info1 = new Info<>();
        info1.setVar(30);
        Info<Float> info2 = new Info<>();
        info2.setVar(20f);
        Info<String> info3 = new Info<>();
        info3.setVar("俺是字符串，不能被受限的FUN组件！");

        fun(info1);
        fun(info2);
//        fun(info3);//受限了，不能调用
        //3、受限泛型--->设置下限
        Info<String> infoStr = new Info<>();
        Info<Object> infoObj = new Info<>();
        mFun(infoStr);
        mFun(infoObj);
        // TODO: 2018/1/30 子类无法使用父类的泛型类型进行接受。
//        infoObj = infoStr;
        //4、泛型类、泛型接口
        //5、泛型方法
        Demo demo = new Demo();
        Integer i = demo.fun(30);
        String str = demo.fun("我是泛型方法哦！");
        Log.d(TAG, "输入内容：" + i);
        Log.d(TAG, "输入内容：" + str);
        //6、通过泛型方法返回泛型类的实例
        InfoTwo<Integer> integerInfoTwo = mFunTwo(30);
        Log.d(TAG, "泛型方法返回泛型类的实例：" + integerInfoTwo.toString());
        //7、泛型数组
        Integer[] array = new Integer[]{1, 2, 3, 4, 5};
        funArray(array);
        //8、泛型嵌套
        InfoValue<String, Integer> infoValue = new InfoValue<>("嵌套泛型", 10);
        InfoValueTest infoValueTest = new InfoValueTest<InfoValue<String, Integer>>(infoValue);
        InfoValue infoValue1 = (InfoValue) infoValueTest.getInfoValue();
        int key = Log.d(TAG, "key：" + infoValue1.getKey());
        int value = Log.d(TAG, "value：" + infoValue1.getValue());
        //创建单例
        Test instance = Test.getInstance();
        //建造模式样板代码
        Config build = new Config.Builder()
                .setName("李大厨")
                .setAge(25)
                .setSex("男")
                .setNumber("18910059825")
                .build();


    }

    public static <T extends Number> InfoTwo<T> mFunTwo(T temp) {
        InfoTwo<T> infoTwo = new InfoTwo<>();
        infoTwo.setVar(temp);
        return infoTwo;
    }

    /**
     * 泛型适用于本类及父类类型上时，必须适用泛型下限
     *
     * @param temp
     */
    private void mFun(Info<? super String> temp) {
        Log.d(TAG, "设置泛型下限：" + temp);
    }

    /**
     * 可以接受任意的泛型对象（只能接受number及number子类）
     * @param temp
     */
    private void fun(Info<? extends Number> temp) {
        Log.d(TAG, "设置泛型上限：" + temp);
    }

    private void mFuncation(Info<?> info) {//可以接受任意泛型的对象
        Log.d(TAG, "--->>" + info);
        System.out.println("内容：" + info);
    }

    /**
     * 泛型方法定义
     * 访问权限 +<泛型标示>+泛型标示 方法名称（泛型标示 参数名称）
     */
    class Demo {
        public <T> T fun(T temp) {
            return temp;
        }
    }

    /**
     * 接受泛型数组
     *
     * @param param
     * @param <T>
     */
    public static <T> void funArray(T param[]) {
        System.out.println("接受泛型数组：" + param);
        for (T t : param) {
            System.out.println(t + "、");
        }
    }
}
