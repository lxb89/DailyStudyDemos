package angqin.myapplication.rxjava_retroft.request;

import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;

/**
 * 作者：${lixuebin} on 2018/1/25 16:45
 * 邮箱：2072301410@qq.com
 */
public class GsonResponseBodyConverter<T> implements Converter<ResponseBody,T> {
    private final Gson gson;
    private final Type type;

    public GsonResponseBodyConverter(Gson gson, Type type) {
        this.gson = gson;
        this.type = type;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        String responseJson = value.string();
        Log.d("Network", "response>>" + responseJson);
        //httpResult 只解析result字段
        HttpResult httpResult = gson.fromJson(responseJson, HttpResult.class);
        //
        if (httpResult.getCount() == 0) {
            throw new ApiException(100);
        }
        return gson.fromJson(responseJson,type);
    }
}
