package angqin.myapplication.mvvm.model;

import angqin.myapplication.mvvm.base.BaseLoadListener;
import angqin.myapplication.mvvm.bean.SimpleNewsBean;

/**
 * Created by ${lixuebin} on 2018/8/24.
 * 邮箱：2072301410@qq.com
 * TIP：
 */

public interface INewsModel {
    /**
     *  获取新闻数据
     * @param page
     * @param loadListener
     */
    void loadNewsData(int page, BaseLoadListener<SimpleNewsBean> loadListener);

}
