package angqin.myapplication.databinding.bean;

import android.databinding.ObservableField;

/**
 * Created by ${lixuebin} on 2018/8/22.
 * 邮箱：2072301410@qq.com
 * TIP： ObservableFields  更新数据
 *  如果想要省时，或者数据类的字段很少的话，可以使用 ObservableField 以及它的派生
 *  ObservableBoolean、 ObservableByte ObservableChar、ObservableShort、ObservableInt、
 *  ObservableLong、ObservableFloat、ObservableDouble、 ObservableParcelable 等。
 */

public class DoubleBindBean2  {
    //变量要改成 public
    public final ObservableField<String> observableField = new ObservableField<>();
}
