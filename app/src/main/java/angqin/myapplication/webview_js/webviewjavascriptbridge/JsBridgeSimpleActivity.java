package angqin.myapplication.webview_js.webviewjavascriptbridge;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.lzyzsd.jsbridge.BridgeHandler;
import com.github.lzyzsd.jsbridge.BridgeWebView;
import com.github.lzyzsd.jsbridge.CallBackFunction;
import com.github.lzyzsd.jsbridge.DefaultHandler;

import angqin.myapplication.R;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ${lixuebin} on 2018/8/20.
 * 邮箱：2072301410@qq.com
 * TIP： 利用WebViewJavascriptBridge  实现 js 与 Android 交互
 */

public class JsBridgeSimpleActivity extends AppCompatActivity {
    private static final String TAG = JsBridgeSimpleActivity.class.getSimpleName();
    @Bind(R.id.tv_show_result)
    TextView tvShowResult;
    @Bind(R.id.webView)
    BridgeWebView webView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_js_bridge_simple);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        //初始化JSBridge,与 js 建立链接
        webView.setDefaultHandler(new DefaultHandler());
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                return super.onJsAlert(view, url, message, result);
            }
        });
        webView.loadUrl("file:///android_asset/test.html");
        //注册函数，让js 调用
        webView.registerHandler("submitFromWeb", new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                Log.i(TAG, "这是 html 返回的数据：\n" + data);
                Toast.makeText(JsBridgeSimpleActivity.this, data, Toast.LENGTH_LONG).show();
                //对数据处理后，回传给js
                String substring = data.substring(0, 5);
                if (function != null) {
                    function.onCallBack(substring);
                }
            }
        });

    }

    @OnClick({R.id.btn_send_message_to_js, R.id.btn_call_js_method})
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.btn_send_message_to_js: //发消息给js
                mJavaSendToMessage();
                break;
            case R.id.btn_call_js_method: //java 调用js 方法
                mJavaCallJs();
                break;
        }
    }

    private void mJavaSendToMessage() {
        //java 发送给 js 的数据
//                webView.send("hello");
        webView.send("Hello", new CallBackFunction() {
            @Override
            public void onCallBack(String data) {
                Log.i(TAG, "java 发送给 js 数据后的 回调数据：\n" + data);
            }
        });
    }

    private void mJavaCallJs() {
        webView.callHandler("functionInJs", "data from java", new CallBackFunction() {
            @Override
            public void onCallBack(String data) {
                Log.i(TAG, "回调数据来自 js =:\n" + data);
            }
        });
    }
}
