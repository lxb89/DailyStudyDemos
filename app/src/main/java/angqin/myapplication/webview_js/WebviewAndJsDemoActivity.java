package angqin.myapplication.webview_js;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;

import angqin.myapplication.R;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ${lixuebin} on 2018/8/15.
 * 邮箱：2072301410@qq.com
 * TIP：android webview js 交互 原生方法实现流程
 * 对于Android调用JS代码的方法有2种：
 * 1. 通过WebView的loadUrl（）
 * 2. 通过WebView的evaluateJavascript（）
 */

public class WebviewAndJsDemoActivity extends AppCompatActivity {
    private final String TAG = WebviewAndJsDemoActivity.class.getSimpleName();
    @Bind(R.id.webview)
    WebView webview;
    @Bind(R.id.btn_to_js)
    Button btnToJs;
    final int version = Build.VERSION.SDK_INT;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview_and_js);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        WebSettings settings = webview.getSettings();
        //设置与js 交互权限
        settings.setJavaScriptEnabled(true);
        //设置允许js 弹窗
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        //加载本地js 代码
        webview.loadUrl("file:///android_asset/javascript.html");

        /**
         *  由于设置了弹窗校验调用结果，所以需要支持js 对话框
         *  webview只是载体，而内容的渲染需要 WebViewChromClient 类去是实现
         *  通过设置webviewChromClient 对象来处理javacript 的对话框
         *  设置响应js 的Alert()函数
         */
        webview.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onJsAlert(WebView view, String url, String message, final JsResult result) {
                AlertDialog.Builder b = new AlertDialog.Builder(WebviewAndJsDemoActivity.this);
                b.setTitle("Alert");
                b.setMessage(message);
                b.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        result.confirm();
                    }
                });
                b.setCancelable(false);
                b.create().show();
                return true;
            }
        });
    }

    @OnClick({R.id.btn_to_js, R.id.btn_to_android})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_to_js:// Android 调 js
                mAndroidToJs();
                break;
            case R.id.btn_to_android: //js 调 android
                mJsToAndroid();
                break;
        }
    }

    private void mJsToAndroid() {
        startActivity(new Intent(WebviewAndJsDemoActivity.this,JsToAndroidActivity.class));
    }

    private void mAndroidToJs() {
        webview.post(new Runnable() {
            @Override
            public void run() {
//                //方法一  注意调用的方法名称要一致
//                //调用javascrip 的callJS()方法
//                webview.loadUrl("javascript:callJS()");
//                //方式二 仅Android 4.4 以上才能使用，缺点：向下兼容性差
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//                    webview.evaluateJavascript("javascript:callJS()", new ValueCallback<String>() {
//                        @Override
//                        public void onReceiveValue(String value) {
//                            //此处为 js 返回的结果
//
//                        }
//                    });
//                }
                //------------->>>>>>  建议两种混合使用 即Android 4.4以下使用方法1，Android 4.4以上方法2
                // 因为该方法在 Android 4.4 版本才可使用，所以使用时需进行版本判断
                if (version < 18) {
                    webview.loadUrl("javascript:callJS()");
                } else {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                        webview.evaluateJavascript("javascript:callJS()", new ValueCallback<String>() {
                            @Override
                            public void onReceiveValue(String value) {
                                //此处为 js 返回的结果
                                Log.i(TAG, "js 返回的结果:\n" + value);

                            }
                        });
                    }
                }
            }
        });
    }
}
