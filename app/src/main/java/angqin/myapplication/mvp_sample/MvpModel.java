package angqin.myapplication.mvp_sample;


import android.os.Handler;

/**
 * Created by ${lixuebin} on 2018/8/1.
 * 邮箱：2072301410@qq.com
 * TIP： 该类为 具体的网络请求操作，模拟真实网络请求
 */

public class MvpModel {
    public static void getNetData(final String params, final MvpCallback<String> callback) {
        //模拟网路请求耗时操作
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                switch (params) {
                    case "normal":
                        callback.onSuccess("根据参数" + params + "请求网络数据成功");
                        break;
                    case "failure":
                        callback.onFailure("请求失败：参数错误！");
                        break;
                    case "error":
                        callback.onError();
                        break;
                    default:
                        break;
                }
                callback.onComplete();

            }
        }, 2000);

    }
}
