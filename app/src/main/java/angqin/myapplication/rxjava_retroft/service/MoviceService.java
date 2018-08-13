package angqin.myapplication.rxjava_retroft.service;

import android.content.Entity;


import java.util.List;

import angqin.myapplication.rxjava_retroft.entity.MoviceEntity;
import angqin.myapplication.rxjava_retroft.entity.Subject;
import angqin.myapplication.rxjava_retroft.request.HttpResult;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 作者：${lixuebin} on 2018/1/24 16:54
 * 邮箱：2072301410@qq.com
 */
public interface MoviceService<T> {

//    //只用retroft请求网络
//    @GET("top250")
//    Call<MoviceEntity> getMovice(@Query("start") int start, @Query("count") int count);
//
//    //Retrofit和Rxjava的结合
//    @GET("top250")
//    Observable<MoviceEntity> getTopMovie(@Query("start") int start, @Query("count") int count);

    //Retrofit和Rxjava的结合,进一步封装
    @GET("top250")
    Observable<HttpResult<List<Subject>>> getTopMovie(@Query("start") int start, @Query("count") int count);
}
