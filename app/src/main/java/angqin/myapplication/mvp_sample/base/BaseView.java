package angqin.myapplication.mvp_sample.base;

import android.content.Context;

/**
 * Created by ${lixuebin} on 2018/8/1.
 * 邮箱：2072301410@qq.com
 * TIP：将View 接口中定义的Activity UI 逻辑，因为每个Activity 中都有共性，将其抽取到基类，变成具有通用性
 */

public interface BaseView {
    void showLoading();

    void hideLoading();

    void showtToast(String msg);

    void showErr();

    Context getContext();
}
