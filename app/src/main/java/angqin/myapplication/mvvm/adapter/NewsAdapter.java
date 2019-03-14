package angqin.myapplication.mvvm.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.ViewGroup;
import android.widget.Toast;


import angqin.myapplication.BR;
import angqin.myapplication.R;
import angqin.myapplication.mvvm.base.BaseAdapter;
import angqin.myapplication.mvvm.base.BaseViewHolder;
import angqin.myapplication.mvvm.bean.SimpleNewsBean;

/**
 * Created by ${lixuebin} on 2018/8/23.
 * 邮箱：2072301410@qq.com
 * TIP：
 */

public class NewsAdapter extends BaseAdapter<SimpleNewsBean, BaseViewHolder> {
    public NewsAdapter(Context mContext) {
        super(mContext);
    }

    @Override
    protected BaseViewHolder onCreateVH(ViewGroup parent, int viewType) {
        ViewDataBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_news, parent, false);
        return new BaseViewHolder(binding);
    }

    @Override
    protected void onBindVH(BaseViewHolder baseViewHolder, int position) {
        ViewDataBinding binding = baseViewHolder.getmBinding();
        binding.setVariable(BR.simpleNewsBean, mList.get(position));
        binding.setVariable(BR.position,position);
        binding.setVariable(BR.adapter,this);
        binding.executePendingBindings(); //防止闪烁
    }

    public void clickDianZan(SimpleNewsBean simpleNewsBean, int position){
        if (simpleNewsBean.isGood.get()){
            simpleNewsBean.isGood.set(false);
            Toast.makeText(mContext,"取消点赞",Toast.LENGTH_SHORT).show();
        }else {
            simpleNewsBean.isGood.set(true);
            Toast.makeText(mContext,"点赞成功",Toast.LENGTH_SHORT).show();
        }

    }

}
