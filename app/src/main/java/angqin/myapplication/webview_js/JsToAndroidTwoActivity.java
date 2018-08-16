package angqin.myapplication.webview_js;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import java.util.HashMap;
import java.util.Set;

import angqin.myapplication.R;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ${lixuebin} on 2018/8/15.
 * 邮箱：2072301410@qq.com
 * TIP：js 调用 Android 方法 二
 * 方式二 原理：通过 WebViewClient 的方法shouldOverrideUrlLoading ()回调拦截 url
 */

public class JsToAndroidTwoActivity extends AppCompatActivity {
    private static final String TAG = JsToAndroidTwoActivity.class.getSimpleName();
    @Bind(R.id.webview)
    WebView webview;
    @Bind(R.id.btn_to_android)
    Button btnToAndroid;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_js_to_android_two);
        ButterKnife.bind(this);
        initView();

    }

    private void initView() {
        WebSettings settings = webview.getSettings();
        //设置与js 交互权限
        settings.setJavaScriptEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        //加载js 代码
        webview.loadUrl("file:///android_asset/javascript3.html");

        webview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // 步骤2：根据协议的参数，判断是否是所需要的url
                // 一般根据scheme（协议格式） & authority（协议名）判断（前两个参数）
                //假定传入进来的 url = "js://webview?arg1=111&arg2=222"（同时也是约定好的需要拦截的）
                Uri uri = Uri.parse(url);
                // 如果url的协议 = 预先约定的 js 协议
                // 就解析往下解析参数
                if (uri.getScheme().equals("js")) {
                    // 如果 authority  = 预先约定协议里的 webview，即代表都符合约定的协议
                    // 所以拦截url,下面JS开始调用Android需要的方法
                    if (uri.getAuthority().equals("webview")) {
                        //  步骤3：
                        // 执行JS所需要调用的逻辑
                        System.out.println("js调用了Android的方法");
                        // 可以在协议上带有参数并传递到Android上
                        HashMap<String, String> params = new HashMap<>();
                        Set<String> collection = uri.getQueryParameterNames();
                        Log.i(TAG, "collection:\n" + collection.toString());
                    }
                    return true;
                }
                return super.shouldOverrideUrlLoading(view, url);
            }
        });
    }

    @OnClick(R.id.btn_to_android)
    public void onViewClicked() {
        startActivity(new Intent(JsToAndroidTwoActivity.this,JsToAndroidThreeActivity.class));
    }
}
