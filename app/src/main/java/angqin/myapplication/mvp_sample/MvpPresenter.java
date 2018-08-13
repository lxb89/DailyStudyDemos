package angqin.myapplication.mvp_sample;

import angqin.myapplication.mvp_sample.base.BasePresenter;

/**
 * Created by ${lixuebin} on 2018/8/1.
 * 邮箱：2072301410@qq.com
 * TIP： 该类为 具体的业务逻辑处理类，纯java 类
 */

public class MvpPresenter extends BasePresenter<MvpView> {

    //获取网络数据
    public void getData(String param) {
        //显示正在加载框
        getView().showLoading();
        //调用model 请求数据
        MvpModel.getNetData(param, new MvpCallback<String>() {
            @Override
            public void onSuccess(String data) {
                //调用view 接口显示数据
                if (isViewAttached()) {
                    getView().showData(data);
                }
            }

            @Override
            public void onFailure(String msg) {
                if (isViewAttached()) {
                    getView().showtToast(msg);
                }

            }

            @Override
            public void onError() {
                if (isViewAttached()) {
                    getView().showErr();
                }

            }

            @Override
            public void onComplete() {
                //隐藏加载框
                if (isViewAttached()){
                    getView().hideLoading();
                }

            }
        });
    }

}
