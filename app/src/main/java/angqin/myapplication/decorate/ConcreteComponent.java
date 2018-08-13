package angqin.myapplication.decorate;

import android.util.Log;

/**
 * Created by ${lixuebin} on 2018/7/17.
 * 邮箱：2072301410@qq.com
 * TIP：具体实现组建对象接口的对象（被装饰的原始对象）
 */

public class ConcreteComponent extends Component {
    private final String TAG = ConcreteComponent.class.getSimpleName();

    @Override
    public void opreation() {
        Log.d(TAG,"ConcreteComponent ---->>> opreation()");
    }
}
