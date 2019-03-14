package angqin.myapplication.mvvm.base;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by ${lixuebin} on 2018/8/23.
 * 邮箱：2072301410@qq.com
 * TIP：
 */

public class BaseViewHolder<B extends ViewDataBinding> extends RecyclerView.ViewHolder {
    private B mBinding;

    public BaseViewHolder(B binding) {
        super(binding.getRoot());
        this.mBinding = binding;
    }

    /**
     * @return
     */
    public B getmBinding() {
        return mBinding;
    }
}
