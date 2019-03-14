package angqin.myapplication.databinding.bean;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import angqin.myapplication.BR;

/**
 * Created by ${lixuebin} on 2018/8/22.
 * 邮箱：2072301410@qq.com
 * TIP： 双向绑定数据
 */

public class DoubleBindBean extends BaseObservable{

    /**
     * BR 是编译阶段生成的一个类，功能与 R.java 类似，用 @Bindable 标记过 getter 方法会在 BR 中生成一个 entry，当我们
     * 通过代码可以看出，当数据发生变化时还是需要手动发出通知。
     * 通过调用notifyPropertyChanged(BR.firstName)来通知系统 BR.firstName 这个 entry 的数据已经发生变化，需要更新 UI。
     */
    private String content;

    public DoubleBindBean(String content) {
        this.content = content;
    }

    @Bindable
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
        //通知系统数据源发生变化，刷新UI界面
        notifyPropertyChanged(BR.content);
    }
}
