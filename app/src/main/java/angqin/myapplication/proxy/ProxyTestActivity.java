package angqin.myapplication.proxy;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

import angqin.myapplication.R;

/**
 * Created by ${lixuebin} on 2018/7/12.
 * 邮箱：2072301410@qq.com
 * TIP： 动态代理 study
 */

public class ProxyTestActivity extends AppCompatActivity {

    private final String TAG = getClass().getSimpleName();
    private Button btn_test;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.proxy_test);
        setUpView();
    }

    private void setUpView() {
        btn_test = (Button) findViewById(R.id.btn_test);
        btn_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //用动态代理Proxy ,去生成一个代理对象，来调用 login()
                UserService proxyInstance = (UserService) Proxy.newProxyInstance(getClassLoader(),
                        new Class[]{UserService.class},
                        new InvocationHandler() {
                            @Override
                            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                                Log.d(TAG, "method:------>>>>" + method);
                                Log.d(TAG, "args:------>>>>" + Arrays.toString(args));
                                return null;
                            }
                        });
                System.out.println("-------------分割线----------------");
                Log.d(TAG, "proxyInstance---------->>>>" + proxyInstance.getClass());
                proxyInstance.login("lxb", "123456");
            }
        });
    }
}
