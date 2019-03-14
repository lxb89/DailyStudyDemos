package angqin.myapplication.mvvm.http;

import java.util.concurrent.TimeUnit;

import angqin.myapplication.mvvm.bean.NewsBean;
import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ${lixuebin} on 2018/8/24.
 * 邮箱：2072301410@qq.com
 * TIP： 封装 网络请求工具类
 */

public class HttpUtils {
    private static final int DEFAULT_TIMEOUT = 8;//默认请求超时为8秒
    private static OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS).build();
    private static Retrofit retrofit;
    private static RetrofitInterface retrofitInterface;

    private synchronized static RetrofitInterface getRetrofit() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(Api.URL_BASE)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
            retrofitInterface = retrofit.create(RetrofitInterface.class);
        }
        return retrofitInterface;
    }

    /**
     * 获取新闻数据
     * @return
     */
    public static Observable<NewsBean> getNewsData() {
        return getRetrofit().getNewsData();
    }

}
