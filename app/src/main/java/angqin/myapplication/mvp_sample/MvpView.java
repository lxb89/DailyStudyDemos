package angqin.myapplication.mvp_sample;

import angqin.myapplication.mvp_sample.base.BaseView;

/**
 * Created by ${lixuebin} on 2018/8/1.
 * 邮箱：2072301410@qq.com
 * TIP： View 接口是Activity 与Presenter 之间的中间层，它的作用是根据具体的业务需求，为Presenter 提供调用Actiivty 中具体UI逻辑的操作方法。
 */

public interface MvpView extends BaseView {
    /**
     * 数据请求成功，调用此接口显示数据
     */
    void showData(String data);

//    /**
//     * 显示网络加载框
//     */
//    void showLoading();
//
//    /**
//     * 隐藏网络加载框
//     */
//    void hideLoading();
//
//    /**
//     * 数据请求失败，调用此接口来提示
//     *
//     * @param msg
//     */
//    void showFailureMessage(String msg);
//
//
//    /**
//     * 数据亲求异常，调用此接口来提示
//     */
//    void showErrorMessage();
}
