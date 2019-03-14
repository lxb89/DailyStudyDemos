package angqin.myapplication.databinding.base;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import angqin.myapplication.BR;
import angqin.myapplication.databinding.interfaze.IBaseBindingAdapterItem;

/**
 * Created by ${lixuebin} on 2018/8/22.
 * 邮箱：2072301410@qq.com
 * TIP：
 */

public class BaseBindingViewHolder extends RecyclerView.ViewHolder {
    private ViewDataBinding binding;

    public ViewDataBinding getBinding() {
        return binding;
    }

    //getRoot()方法会返回整个holder的最顶层的view
    public BaseBindingViewHolder(ViewDataBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    /**
     * 绑定数据
     * @param item
     */
    public void binData(IBaseBindingAdapterItem item) {
        binding.setVariable(BR.item, item);

    }
}
