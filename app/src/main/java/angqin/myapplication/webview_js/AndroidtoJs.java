package angqin.myapplication.webview_js;

import android.util.Log;
import android.webkit.JavascriptInterface;

/**
 * Created by ${lixuebin} on 2018/8/15.
 * 邮箱：2072301410@qq.com
 * TIP：JS对象映射关系对应的类
 */

public class AndroidtoJs extends Object {

    /**
     * 定义js 需要调用的方法
     * 被js 调用的方法，必须加入 @JavascriptInterface 注解
     * @param msg
     */
    @JavascriptInterface
    public void hello(String msg) {
        Log.i("AndroidtoJs", "JS调用了Android的hello方法");
    }
}
