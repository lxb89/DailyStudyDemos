package angqin.myapplication.databinding;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import angqin.myapplication.R;

/**
 * Created by ${lixuebin} on 2018/8/22.
 * 邮箱：2072301410@qq.com
 * TIP： DataBinding 学习入口
 */

public class DataBindingMainAty extends AppCompatActivity implements View.OnClickListener{

    private Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        angqin.myapplication.databinding.ActivityDatabindingMainBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_databinding_main);
        binding.setOnClickListener(this);
        mContext = this;

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn0:
                startActivity(new Intent(mContext,DataBindingTestOneAty.class));
                break;
            case R.id.btn1:
                startActivity(new Intent(mContext,DataBindingTestTwoAty.class));
                break;
            case R.id.btn2:
                startActivity(new Intent(mContext,DataBindingTestThreeAty.class));
                break;
        }

    }
}
