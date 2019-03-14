package angqin.myapplication.databinding.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

import angqin.myapplication.BR;
import angqin.myapplication.R;
import angqin.myapplication.databinding.base.BaseBindRecyclerViewAdapter;
import angqin.myapplication.databinding.base.BaseBindingViewHolder;
import angqin.myapplication.databinding.bean.GirlsItem;

/**
 * Created by ${lixuebin} on 2018/8/23.
 * 邮箱：2072301410@qq.com
 * TIP：单一 item 适配器
 */

public class SingleAdapter extends BaseBindRecyclerViewAdapter<GirlsItem>{

    public SingleAdapter(Context context, List<GirlsItem> mList) {
        super(context, mList);
    }

    @Override
    protected RecyclerView.ViewHolder onCreateMyViewHolder(ViewGroup parent, int viewType) {
        ViewDataBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_grils, parent, false);
        return new BaseBindingViewHolder(binding);
    }

    @Override
    protected void onBindMyViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewDataBinding binding = DataBindingUtil.getBinding(holder.itemView);
        binding.setVariable(BR.item,mList.get(position));
        binding.executePendingBindings();//数据改变，立即刷新数据，防止闪烁

    }
}
