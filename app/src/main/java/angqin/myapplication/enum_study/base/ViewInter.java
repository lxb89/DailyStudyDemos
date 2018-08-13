package angqin.myapplication.enum_study.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by ${lixuebin} on 2018/7/11.
 * 邮箱：2072301410@qq.com
 * TIP： 使用接口，将BaseActivity 中的 填充布局view,分离，暴漏给外部实现
 */

public interface ViewInter {
    /**
     * 填充view
     *
     * @param layoutInflater
     * @param container
     */
    void initView(LayoutInflater layoutInflater, ViewGroup container);

    /**
     * 查找view
     *
     * @param view
     */
    void findView(View view);

    /**
     * 获取view
     */
    View getView();

    /**
     * 初始化数据
     *
     * @param context
     */
    void initData(Context context);


}
