package angqin.myapplication.databinding.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

import angqin.myapplication.R;
import angqin.myapplication.databinding.ItemGrilsBinding;
import angqin.myapplication.databinding.ItemTitleBinding;
import angqin.myapplication.databinding.base.BaseBindRecyclerViewAdapter;
import angqin.myapplication.databinding.bean.GirlsItem;
import angqin.myapplication.databinding.bean.TitleItem;
import angqin.myapplication.databinding.interfaze.IBaseBindingAdapterItem;

/**
 * Created by ${lixuebin} on 2018/8/22.
 * 邮箱：2072301410@qq.com
 * TIP：
 */

public class MultiItemAdapter extends BaseBindRecyclerViewAdapter<IBaseBindingAdapterItem> {
    public MultiItemAdapter(Context context, List<IBaseBindingAdapterItem> mList) {
        super(context, mList);
    }

    @Override
    protected RecyclerView.ViewHolder onCreateMyViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case R.layout.item_grils:
                ItemGrilsBinding itemGrilsBinding = DataBindingUtil.inflate(inflater, R.layout.item_grils, parent, false);
                return new GrilsViewHolder(itemGrilsBinding);
            case R.layout.item_title:
                ItemTitleBinding itemTextBinding = DataBindingUtil.inflate(inflater, R.layout.item_title, parent, false);
                return new TitleViewHolder(itemTextBinding);
            default:
                ItemGrilsBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_grils, parent, false);
                return new GrilsViewHolder(binding);
        }
    }

    @Override
    protected void onBindMyViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof GrilsViewHolder) {
            GirlsItem girlsItem = (GirlsItem) mList.get(position);
            ((GrilsViewHolder) holder).getBinding().setItem(girlsItem);
            ((GrilsViewHolder) holder).getBinding().executePendingBindings(); //解决databinding闪烁问题
        } else if (holder instanceof TitleViewHolder) {
            TitleItem titleItem = (TitleItem) mList.get(position);
            ((TitleViewHolder) holder).getBinding().setItem(titleItem);
            ((TitleViewHolder) holder).getBinding().executePendingBindings(); //解决databinding闪烁问题
        }
    }

    @Override
    public int getItemViewType(int position) {
        return mList.get(position).getItemViewType();
    }


    class GrilsViewHolder extends RecyclerView.ViewHolder {
        private ItemGrilsBinding binding;

        public ItemGrilsBinding getBinding() {
            return binding;
        }

        public GrilsViewHolder(ItemGrilsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    class TitleViewHolder extends RecyclerView.ViewHolder {
        private ItemTitleBinding binding;

        public ItemTitleBinding getBinding() {
            return binding;
        }

        public TitleViewHolder(ItemTitleBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
