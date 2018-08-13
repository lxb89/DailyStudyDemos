package angqin.myapplication.decorate;

import android.util.Log;

/**
 * Created by ${lixuebin} on 2018/7/17.
 * 邮箱：2072301410@qq.com
 * TIP： 具体装饰器实现对象
 */

public class ConcreteDecorateA extends Decorator {
    /**
     * 依赖注入
     *
     * @param component
     */
    public ConcreteDecorateA(Component component) {
        super(component);
    }
    public int operationA(){
        Log.d(TAG,"ConcreteDecorateA------>>> operationA()");
        return 0;
    }

    @Override
    public void opreation() {
        operationA();
        super.opreation();
    }
}
