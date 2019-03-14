package angqin.myapplication.databinding.bean;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import angqin.myapplication.BR;
import angqin.myapplication.R;
import angqin.myapplication.databinding.interfaze.IBaseBindingAdapterItem;

/**
 * Created by ${lixuebin} on 2018/8/22.
 * 邮箱：2072301410@qq.com
 * TIP：
 */

public class GirlsItem extends BaseObservable implements IBaseBindingAdapterItem {
    private String picUrl; //图片的ID
    private String describe; //描述

    public GirlsItem(String picUrl, String describe) {
        this.picUrl = picUrl;
        this.describe = describe;
    }

    @Bindable
    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
        notifyPropertyChanged(BR.picUrl);
    }

    @Bindable
    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
        notifyPropertyChanged(BR.describe);
    }

    @Override
    public int getItemViewType() {
        return R.layout.item_grils;

    }
}
