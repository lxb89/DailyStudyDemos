package angqin.myapplication.mvvm.base;

import java.util.List;

/**
 * Created by ${lixuebin} on 2018/8/24.
 * 邮箱：2072301410@qq.com
 * TIP： Model   中数据加载回调接口
 */

public interface BaseLoadListener<T> {

    void loadSuccess(List<T> list);

    void loadFailure(String msg);

    void loadStart();

    void loadComplete();


}
