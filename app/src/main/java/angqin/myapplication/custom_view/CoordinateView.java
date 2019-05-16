package angqin.myapplication.custom_view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by ${lixuebin} on 2019/5/16.
 * 邮箱：2072301410@qq.com
 * TIP：坐标转换 学习
 */

public class CoordinateView extends View {

    public CoordinateView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CoordinateView(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.RED);
        paint.setStrokeWidth(2);
        paint.setStyle(Paint.Style.STROKE);

        canvas.save(); //保存现场
        for (int i = 0; i < 10; i++) {
            canvas.drawRect(0, 0, 150, 150, paint);
            canvas.translate(30, 30);
        }
        canvas.restore();// 恢复现场到save() 执行之前的状态

        //平移坐标，让接下来绘制的图形绘制在上一次图形的下面
        canvas.translate(0, 500);
        canvas.save();
        for (int i = 0; i < 10; i++) {
            canvas.drawRect(0, 0, 400, 400, paint);
            canvas.scale(0.9f, 0.9f, 200, 200); //以(200,200)为中心对画布进行缩放
        }
        canvas.restore();

        //通过坐标平移，重新绘制下一个图形
        canvas.translate(0,500);
        canvas.save();
        canvas.drawCircle(200, 200, 200, paint);
        for (int i = 0; i < 12; i++) {
            canvas.drawLine(350,200,400,200, paint);
            canvas.rotate(30,200, 200);//以(200,200)为中心，对画布坐标进行进行旋转 degrees 度，正表示顺时针，负 表示逆时针
        }
        canvas.restore();

    }
}
