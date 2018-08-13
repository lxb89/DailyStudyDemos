package angqin.myapplication.decorate;

/**
 * Created by ${lixuebin} on 2018/7/17.
 * 邮箱：2072301410@qq.com
 * TIP： 所有装饰类的抽象父类，持有接口 转发请求
 */

public abstract class Decorator extends Component {
    protected final String TAG = "Decorator";
    //内部持有组件接口对象
    protected Component component;
    /**
     * 依赖注入
     * @param component
     */
    public Decorator(Component component) {
        this.component = component;
    }

    /**
     * 转发请求给组件对象，在这里可以增加附加功能
     */
    @Override
    public void opreation() {
        component.opreation();
    }
}
