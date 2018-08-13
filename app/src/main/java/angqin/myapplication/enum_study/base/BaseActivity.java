package angqin.myapplication.enum_study.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by ${lixuebin} on 2018/7/11.
 * 邮箱：2072301410@qq.com
 * TIP：
 */

public abstract class BaseActivity<V extends ViewInter> extends AppCompatActivity {

    private V mainView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        try {
            mainView = getViewInterClass().newInstance();
            mainView.initView(getLayoutInflater(),null);
            mainView.findView(mainView.getView());
            setContentView(mainView.getView());
            mainView.initData(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onCreate(savedInstanceState);
    }

    public abstract Class<V> getViewInterClass();

    protected void setTitle(String string) {

    }
}
