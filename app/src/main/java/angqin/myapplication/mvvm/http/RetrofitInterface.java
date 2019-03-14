package angqin.myapplication.mvvm.http;

import angqin.myapplication.mvvm.bean.NewsBean;
import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by ${lixuebin} on 2018/8/24.
 * 邮箱：2072301410@qq.com
 * TIP：
 */

public interface RetrofitInterface {
    //获取搜索分类中的商品信息
    @GET(Api.URL_PATH)
    Observable<NewsBean> getNewsData();
}
