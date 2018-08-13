package angqin.myapplication.mvp_sample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import angqin.myapplication.R;
import angqin.myapplication.mvp_sample.base.BaseActivity;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ${lixuebin} on 2018/7/31.
 * 邮箱：2072301410@qq.com
 * TIP：
 * 一、MVP 架构概述
 * 好处： MVP 模式将Activity 中全部业务逻辑分离处理，只让Activity 操作UI，所有跟Android Api 无关的业务逻辑交给 Presenter 处理，这样将业务逻辑分离出来，方便维护；
 * 缺点是，增加代码量。
 * 二、原理（与mvc 一样分三层架构）
 * 1、View层：将Activity 和 Fragment 视为View层，负责处理UI;
 * 2、Presenter 业务层：既能调用UI 逻辑，又能请求数据，该层为纯java类，不涉及任何Android API;
 * 3、Model层： 包括具体的数据请求，数据源；
 * 三层之间的调用关系： View ---->>> Presenter----->>> Model
 * <p>
 * 三、对MVP 存在的漏洞，进行二次优化，弥补自身缺点
 * 1、架构存在漏洞（调用View可能引发的空指针异常） -----》》解决：每次调用View 都知道Activity 的生命周期状态，与其绑定绑定
 * 2、代码冗余量大  -----》》解决：结合Activity 构建base 层
 * 3、通用性差
 */

public class MvpSampleActivity extends BaseActivity implements MvpView {

    private MvpPresenter mvpPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp_sample);
        ButterKnife.bind(this);
        setUpUi();

    }

    private void setUpUi() {
        //初始化Presenter
//        mvpPresenter = new MvpPresenter(this);
        //调用View可能引发的空指针异常，对此进行优化
        mvpPresenter = new MvpPresenter();
        mvpPresenter.attachView(this);

    }

    @OnClick({R.id.btn0, R.id.btn1, R.id.btn2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn0:
                mvpPresenter.getData("normal");
                break;
            case R.id.btn1:
                mvpPresenter.getData("failure");
                break;
            case R.id.btn2:
                mvpPresenter.getData("error");
                break;
        }
    }

    @Override
    public void showData(String data) {
        Toast.makeText(this, data, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //断开View 引用
        mvpPresenter.detachView();
    }
}
