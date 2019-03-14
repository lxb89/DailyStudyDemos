package angqin.myapplication.mvvm.base;

/**
 * Created by ${lixuebin} on 2018/8/24.
 * 邮箱：2072301410@qq.com
 * TIP：
 */

public interface IBaseView {
    /**
     * 开始加载
     * @param loadType  加载的类型 0：第一次记载 1：下拉刷新 2：上拉加载更多
     */
    void loadStart(int loadType);

    /**
     *  加载完成
     */
    void loadComplete();

    /**
     * 加载失败
     */
    void loadFailure(String msg);
}
