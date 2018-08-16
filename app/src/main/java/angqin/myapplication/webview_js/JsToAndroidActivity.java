package angqin.myapplication.webview_js;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;

import angqin.myapplication.R;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ${lixuebin} on 2018/8/15.
 * 邮箱：2072301410@qq.com
 * TIP：
 * 对于JS调用Android代码的方法有3种：
 * 1. 通过WebView的addJavascriptInterface（）进行对象映射
 * 2. 通过 WebViewClient 的shouldOverrideUrlLoading ()方法回调拦截 url
 * 3. 通过 WebChromeClient 的onJsAlert()、onJsConfirm()、onJsPrompt（）方法回调拦截JS对话框alert()、confirm()、prompt（） 消息
 */

public class JsToAndroidActivity extends AppCompatActivity {
    @Bind(R.id.webview)
    WebView webview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_js_to_android);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        WebSettings settings = webview.getSettings();
        //设置与js 交互权限
        settings.setJavaScriptEnabled(true);
        //方式一：实现流程
        /**
         * 1、定义一个与js 对象映射关系的Android 类 AndroidtoJs
         * 2、将本地的js 文件放到 asset 文件下
         * 3、在Android 代码里 设置Android类与js 代码映射
         *  param1 :javascript 对象名
         *  param2 :java 对象名
         */
        webview.addJavascriptInterface(new AndroidtoJs(), "test");
        //加载js 代码
        webview.loadUrl("file:///android_asset/javascript2.html");

    }


    @OnClick(R.id.btn_to_android)
    public void onViewClicked() {
        startActivity(new Intent(JsToAndroidActivity.this,JsToAndroidTwoActivity.class));
    }
}
