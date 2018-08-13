package angqin.myapplication.enum_study;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import angqin.myapplication.R;
import angqin.myapplication.enum_study.base.BaseFragment;

/**
 * Created by ${lixuebin} on 2018/7/11.
 * 邮箱：2072301410@qq.com
 * TIP：
 */

public class PindFragment extends BaseFragment{

    private TextView fg_content;

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_pind,null);
    }

    @Override
    protected void findView(View view) {
        fg_content = view.findViewById(R.id.fg_content);

    }

    @Override
    protected void initData() {
        fg_content.setText("Pind 模块");
    }
}
