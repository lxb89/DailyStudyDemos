package angqin.myapplication.mvp_sample.base;

import angqin.myapplication.mvp_sample.MvpView;

/**
 * Created by ${lixuebin} on 2018/8/1.
 * 邮箱：2072301410@qq.com
 * TIP：Presenter 中可共用的代码就是对View 的引用，将其抽取到基类中，变为具有通用性；
 */

public class BasePresenter<V extends BaseView> {
    /**
     * 绑定的View
     */
    private V mvpView;


    //绑定view,一般在初始化中调用
    public void attachView(V mvpView) {
        this.mvpView = mvpView;
    }

    /**
     * 断开View ,一般在 onDestory 中调用
     */
    public void detachView() {
        this.mvpView = null;
    }

    /**
     * 是否与View 建立连接
     * 每次调用业务请求的时候，都要先调用此方法，检查是否与View 建立连接
     *
     * @return
     */
    public boolean isViewAttached() {
        return mvpView != null;
    }

    /**
     *  获取连接的View
     * @return
     */
    public V getView() {
        return mvpView;
    }


}
