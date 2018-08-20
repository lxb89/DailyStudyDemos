package angqin.myapplication.webview_js;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

import java.util.HashMap;
import java.util.Set;

import angqin.myapplication.R;
import angqin.myapplication.webview_js.webviewjavascriptbridge.JsBridgeSimpleActivity;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ${lixuebin} on 2018/8/16.
 * 邮箱：2072301410@qq.com
 * TIP：js 调用 Android 方法 三
 * 方式3的原理：Android通过 WebChromeClient 的onJsAlert()、onJsConfirm()、onJsPrompt（）方法回调分别拦截JS对话框
 */

public class JsToAndroidThreeActivity extends AppCompatActivity {
    private static final String TAG = JsToAndroidThreeActivity.class.getSimpleName();
    @Bind(R.id.webview)
    WebView webview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_js_to_android_three);
        ButterKnife.bind(this);
        initView();

    }

    private void initView() {
        WebSettings settings = webview.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        //先加载js 代码
        webview.loadUrl("file:///android_asset/javascript4.html");

        webview.setWebChromeClient(new WebChromeClient() {
            // 拦截输入框(原理同方式2)
            // 参数message:代表promt（）的内容（不是url）
            // 参数result:代表输入框的返回值
            @Override
            public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
                // 根据协议的参数，判断是否是所需要的url(原理同方式2)
                // 一般根据scheme（协议格式） & authority（协议名）判断（前两个参数）
                //假定传入进来的 url = "js://webview?arg1=111&arg2=222"（同时也是约定好的需要拦截的）
                Uri uri = Uri.parse(url);
                // 如果url的协议 = 预先约定的 js 协议
                // 就解析往下解析参数
                if (uri.getScheme().equals("js")) {
                    // 如果 authority  = 预先约定协议里的 webview，即代表都符合约定的协议
                    // 所以拦截url,下面JS开始调用Android需要的方法
                    if (uri.getAuthority().equals("webview")) {
                        // 执行JS所需要调用的逻辑
                        System.out.println("js调用了Android的方法");
                        // 可以在协议上带有参数并传递到Android上
                        HashMap<String, String> params = new HashMap<>();
                        Set<String> collection = uri.getQueryParameterNames();
                        //参数result:代表消息框的返回值(输入值)
//                        result.confirm();
                    }
                    return true;
                }
                return super.onJsPrompt(view, url, message, defaultValue, result);
            }

            //通过alert()和confirm()拦截的原理相同，此处不作过多讲述
            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                return super.onJsAlert(view, url, message, result);
            }

            @Override
            public boolean onJsConfirm(WebView view, String url, String message, JsResult result) {
                return super.onJsConfirm(view, url, message, result);
            }
        });

    }

    @OnClick(R.id.btn_to_android)
    public void onViewClicked() {
        startActivity(new Intent(JsToAndroidThreeActivity.this, JsBridgeSimpleActivity.class));
    }
}
