package angqin.myapplication.mvp_sample;

/**
 * Created by ${lixuebin} on 2018/8/1.
 * 邮箱：2072301410@qq.com
 * TIP： Callback 是Model层 给Presenter 层反馈请求信息的传递载体，所以需要在Callback 中定义数据请求的各种反馈状态；
 */

public interface MvpCallback<T> {
    /**
     *  数据请求成功
     * @param data
     */
    void onSuccess(T data);

    /**
     * 请求成功，数据获取失败
     * @param msg
     */
    void onFailure(String msg);

    /**
     * 请求数据失败
     */
    void onError();

    /**
     * 请求数据结束后，不管请求数据是否成功/失败/异常，都会执行此方法，在这里做相关逻辑操作处理
     */
    void onComplete();
}
