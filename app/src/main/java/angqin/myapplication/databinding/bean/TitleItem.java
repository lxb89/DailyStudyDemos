package angqin.myapplication.databinding.bean;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;

import angqin.myapplication.BR;
import angqin.myapplication.R;
import angqin.myapplication.databinding.interfaze.IBaseBindingAdapterItem;

/**
 * Created by ${lixuebin} on 2018/8/22.
 * 邮箱：2072301410@qq.com
 * TIP：
 */

public class TitleItem extends BaseObservable implements IBaseBindingAdapterItem {
    private String text;

    public TitleItem(String text) {
        this.text = text;
    }

    @Bindable
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
        notifyPropertyChanged(BR.text);
    }

    @Override
    public int getItemViewType() {
        return R.layout.item_title;

    }
}
