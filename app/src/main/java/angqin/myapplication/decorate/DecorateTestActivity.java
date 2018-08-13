package angqin.myapplication.decorate;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import angqin.myapplication.R;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ${lixuebin} on 2018/7/16.
 * 邮箱：2072301410@qq.com
 * TIP： 装饰者模式 研究
 * 定义： 动态地给一个对象增加一些额外地职责。就增加功能来说，装饰者模式比生成子类更灵活。
 * 基本思路：在装饰模式的实现中，为了能够实现和原来使用被装饰对象的代码无缝衔接，
 * 是通过定义一个抽象类，让这个类实现与被装饰对象相同的接口，然后在具体的实现类中，转调被装饰的对象，
 * 在转调前后添加新功能，这就实现了给被装饰对象增加功能。
 * 而转调的实现是定义统一接口。
 * <p>
 * 实现步骤：
 * 1、定义组建对象接口；
 * 2、定义实现接口对象（被装饰的原始对象）；
 * 3、定义装饰类的抽象类，并持有接口
 * 4、定义具体装饰器的实现类，调用接口和具有自己的方法（附加功能加上去）；
 */

public class DecorateTestActivity extends AppCompatActivity {
    @Bind(R.id.btn_test)
    Button btnTest;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decorate_test);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.btn_test)
    public void onViewClicked() {
        Component concreteComponent = new ConcreteComponent();
        Decorator decorator = new ConcreteDecorateA(concreteComponent);
        decorator.opreation();
    }

}


