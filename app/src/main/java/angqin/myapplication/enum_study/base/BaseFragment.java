package angqin.myapplication.enum_study.base;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import angqin.myapplication.enum_study.EnumInstanceActivity;

/**
 * Created by ${lixuebin} on 2018/7/11.
 * 邮箱：2072301410@qq.com
 * TIP：
 */

public abstract class BaseFragment extends Fragment {

    private EnumInstanceActivity enumInstanceActivity;
    private View view;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        enumInstanceActivity = (EnumInstanceActivity) getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = initView(inflater, container);
        findView(view);
        initData();
        return view;
    }

    protected abstract void initData();

    protected abstract void findView(View view);

    public abstract View initView(LayoutInflater inflater, ViewGroup container);
}
