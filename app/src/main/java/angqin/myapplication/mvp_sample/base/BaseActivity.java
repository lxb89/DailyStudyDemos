package angqin.myapplication.mvp_sample.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import angqin.myapplication.R;

/**
 * Created by ${lixuebin} on 2018/8/1.
 * 邮箱：2072301410@qq.com
 * TIP： 主要负责实现BaseView 通用的UI逻辑方法，避免每一个Activity 都实现一遍
 */

public abstract class BaseActivity extends AppCompatActivity implements BaseView {
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("正在加载数据");
    }

    @Override
    public void showLoading() {
        if (!progressDialog.isShowing()) {
            progressDialog.show();
        }
    }

    @Override
    public void hideLoading() {
        if (progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    @Override
    public void showErr() {
        showtToast("请求数据失败：参数错误！");
    }

    @Override
    public void showtToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public Context getContext() {
        return BaseActivity.this;
    }
}
