package angqin.myapplication.enum_study;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.lang.reflect.Proxy;

import angqin.myapplication.R;
import angqin.myapplication.enum_study.base.BaseActivity;
import angqin.myapplication.enum_study.base.BaseFragment;
import angqin.myapplication.enum_study.base.ViewInter;

/**
 * Created by ${lixuebin} on 2018/7/11.
 * 邮箱：2072301410@qq.com
 * TIP：
 */

public class MainView implements ViewInter {

    private View contentView;
    private Toolbar toolbar;
    private BaseActivity baseActivity;
    private ListView list_view;
    private TitleLeftAdapter adapter;
    private BaseFragment fragment;

    @Override
    public void initView(LayoutInflater layoutInflater, ViewGroup container) {
        contentView = layoutInflater.inflate(R.layout.activity_enum_instance, container);
        toolbar = (Toolbar) contentView.findViewById(R.id.toolbar);
    }

    @Override
    public View getView() {
        return contentView;
    }

    @Override
    public void findView(View view) {
        list_view = view.findViewById(R.id.list_view);
    }

    @Override
    public void initData(@NonNull Context context) {
        baseActivity = (BaseActivity) context;
        String[] titles = context.getResources().getStringArray(R.array.pageTiTles);
        adapter = new TitleLeftAdapter(context, titles);
        list_view.setAdapter(adapter);
        loadFragments(FragmentEnunm.CT_BLUETOOTH);
        list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                adapter.setCurPos(position);
                loadFragments(getFragmentEnum(position));
            }
        });

    }

    private FragmentEnunm getFragmentEnum(int position) {
        String title = "";
        FragmentEnunm fragmentEnum = FragmentEnunm.CT_BLUETOOTH;
        switch (position) {
            case 0:
                fragmentEnum = FragmentEnunm.CT_BLUETOOTH;
                title = "蓝牙模块";
                break;
            case 1:
                fragmentEnum = FragmentEnunm.CT_PINPAD;
                title = "PIND模块";
                break;
            case 2:
                fragmentEnum = FragmentEnunm.CT_EMV;
                title = "EMV模块";
                break;
        }
        setTitle(title);
        return fragmentEnum;
    }

    private void loadFragments(@NonNull FragmentEnunm fragmentEnunm) {
        FragmentManager fragmentManager = baseActivity.getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragment = FragmentFactory.getFragment(fragmentEnunm);        //工厂模式，创造frgment
        fragmentTransaction.replace(R.id.fl_content, fragment);
        fragmentTransaction.commitAllowingStateLoss();
    }

    public void setTitle(String title) {
        toolbar.setTitle(title.toString());
    }
}
