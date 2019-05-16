package angqin.myapplication.custom_view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by ${lixuebin} on 2019/5/15.
 * 邮箱：2072301410@qq.com
 * TIP：自定义view ，重写onDraw()绘图   小球左右移动
 */

public class MovingGraghicView extends View {

    /*小球的垂直位置,固定为 100*/
    private static final int Y = 100;
    /*小球的水平位置*/
    private int x;
    /*小球的半径*/
    private static final int RADIUS = 30;
    /*小球的颜色*/
    private static final int COLOR = Color.RED;
    /*小球移动的方向*/
    private boolean direction;
    private Paint paint;

    public MovingGraghicView(Context context) {//动态实例化view用到;
        super(context);
    }

    public MovingGraghicView(Context context, @Nullable AttributeSet attrs) {//在xml 用到;
        super(context, attrs);
        //初始化画笔 参数表示去锯齿
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(COLOR);
        x = RADIUS;
    }

    public MovingGraghicView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {//不会被系统默认调用，需要自己去显示的调用;
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //根据x,y 坐标画一个小球
        canvas.drawCircle(x, Y, RADIUS, paint);
        //获取组件的宽度
        int measuredWidth = this.getMeasuredWidth();

        if (x <= RADIUS) {
            direction = true;
        }
        if (x >= measuredWidth - RADIUS) {
            direction = false;
        }
        x = direction ? x + 5 : x - 5;

    }
}
