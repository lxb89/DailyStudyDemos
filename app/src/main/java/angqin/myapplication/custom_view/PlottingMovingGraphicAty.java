package angqin.myapplication.custom_view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;

import java.util.Timer;
import java.util.TimerTask;

import angqin.myapplication.R;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ${lixuebin} on 2019/5/15.
 * 邮箱：2072301410@qq.com
 * TIP：如何通过绘图实现动态效果
 * 使用 Graphics2D 实现动态效果
 */

public class PlottingMovingGraphicAty extends AppCompatActivity {
    @Bind(R.id.cvTransformation)
    CoordinateView cvTransformation;
    @Bind(R.id.mgv_ball)
    MovingGraghicView mgvBall;
    @Bind(R.id.lin_root)
    LinearLayout linRoot;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plotting_moving_graphic);
        ButterKnife.bind(this);

        mMovingBall();// 自定义组件 通过绘图实现动态效果
        mCoordinateTransformation();//坐标转换

    }

    private void mMovingBall() {
        //通过定时器，周期性的调用invlidate(),不断重绘小球，也就是不断调用onDraw()方法，每调一次，x 值会变化一次，自然小球也就移动起来了
        //通过 Timer 类定义一个计时器，延时 200 毫秒开始计时，每隔 50 毫秒计时一次。
        // 定时任务类 TimerTask 其实就是一个子线程，只能调用 postInvalidate()方法来重绘组件
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                mgvBall.postInvalidate();
            }
        },200,50);
    }

    private void mCoordinateTransformation() {
        //动态实例化自定义view
        /*方式一*/
//        CoordinateView coordinateView = new CoordinateView(this);
//        coordinateView.setBackgroundColor(getResources().getColor(R.color.albumColorPrimary));
//        linRoot.addView(coordinateView);
        /*方式二*/
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(500,1500);
        CoordinateView customView = new CoordinateView(this);
        customView.setBackgroundColor(getResources().getColor(R.color.albumColorPrimary));
        customView.setLayoutParams(layoutParams);
        linRoot.addView(customView);
    }
}
