package angqin.myapplication.rxjava_retroft.request;

import java.util.List;
import java.util.concurrent.TimeUnit;

import angqin.myapplication.rxjava_retroft.config.HttpConfig;
import angqin.myapplication.rxjava_retroft.entity.Subject;
import angqin.myapplication.rxjava_retroft.service.MoviceService;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * 作者：${lixuebin} on 2018/1/25 10:30
 * 邮箱：2072301410@qq.com
 * TIP：网络请求过程封装类
 */
public class HttpMethods<T> {
    public static final String BASE_URL = HttpConfig.BASEURL;
    private static final int DEFAULT_TIMEOUT = 5;
    private final MoviceService moviceService;
    private final Retrofit retroft;

    //构造方法私有化
    private HttpMethods() {
        //手动创建 一个okhttpclient，并设置延迟
        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
        okHttpClient.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        retroft = new Retrofit.Builder()
                .client(okHttpClient.build())
                // TODO: 2018/1/25  对http请求结果进行统一的预处GosnResponseBodyConvert理
//                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(ResponseConvertFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();
        moviceService = retroft.create(MoviceService.class);
    }

    /**
     * 在访问httpmethods 时创建单例
     */
    private static class SingletopHolder {
        private static final HttpMethods INSTANCE = new HttpMethods();
    }

    //对外提供公共的方法，获取单例
    public static HttpMethods getInstance() {
        return SingletopHolder.INSTANCE;
    }

    /**
     * 获取数据
     *
     * @param subscriber 由调用者传过来的观察者
     * @param start
     * @param count
     */
    public void getTopMovie(Subscriber<List<Subject>> subscriber, int start, int count) {
        // TODO: 2018/1/25 EncapsulationRetroftActivity ,测试使用注释代码
//        moviceService.getTopMovie(start, count)
//                .subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);

        moviceService.getTopMovie(start, count)
                .map(new HttpResultFunc<List<Subject>>())
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    private class HttpResultFunc<T> implements Func1<HttpResult<T>, T> {

        @Override
        public T call(HttpResult<T> tHttpResult) {
            if (tHttpResult.getCount() == 0) {
                throw new ApiException(100);
            }
            return tHttpResult.getSubjects();
        }
    }

}
