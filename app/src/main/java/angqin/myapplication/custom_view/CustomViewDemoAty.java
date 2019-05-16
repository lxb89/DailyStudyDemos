package angqin.myapplication.custom_view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.widget.ImageView;

import angqin.myapplication.R;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ${lixuebin} on 2019/5/14.
 * 邮箱：2072301410@qq.com
 * TIP： 自定义组件学习
 */

public class CustomViewDemoAty extends AppCompatActivity {
    @Bind(R.id.iv)
    ImageView iv;
    @Bind(R.id.iv_bitmap)
    ImageView ivBitmap;
    @Bind(R.id.iv_point)
    ImageView ivPoint;
    @Bind(R.id.iv_line)
    ImageView ivLine;
    @Bind(R.id.ivRect)
    ImageView ivRect;
    @Bind(R.id.ivArc)
    ImageView ivArc;
    @Bind(R.id.ivSector)
    ImageView ivSector;
    @Bind(R.id.ivStar)
    ImageView ivStar;
    @Bind(R.id.ivPath)
    ImageView ivPath;
    @Bind(R.id.ivBezier)
    ImageView ivBezier;
    @Bind(R.id.iv_pathMore)
    ImageView ivPathMore;
    @Bind(R.id.ivText)
    ImageView ivText;
    @Bind(R.id.acb_movingGraph)
    AppCompatButton acbMovingGraph;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view_demo);
        ButterKnife.bind(this);

        toUseCanvasAndPaint(); //学习使用Canvas和 Paint
        toCanvasBitmap();   //绘制位图
        toCanvasPoint();  //绘制点
        toCanvasLine(); //绘制线
        toCanvasRect();//绘制矩形
        toCanvasArc(false);//绘制椭圆/弧线/扇形
        toCanvasArc(true);//绘制扇形
        toCanvasPath();//绘制路径(是一种比普通图形复杂的图形)
        toCanvasPathAddGraph();  //通过path add 绘制不同图形
        toCanvasBezier();      //绘制 贝塞尔曲线（二阶）
        toCanvasPathMore();   //多个bath 进行图形运算
        toCanvasText();//指定位置绘制文字/ 沿着path 绘制文字

        acbMovingGraph.setOnClickListener((v)->{//绘制动态效果
            startActivity(new Intent(this,PlottingMovingGraphicAty.class));
        });

    }

    private void toCanvasText() {
        Bitmap bitmap = Bitmap.createBitmap(500, 400, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        paint.setTextSize(14);
        String text = "韬睿科技，移动互联网卓越品牌！ 我爱 Android！";
        //绘制文字
        canvas.drawText(text, 10, 50, paint);
        canvas.drawText(text, 0, 4, 10, 100, paint);
        canvas.drawText(text.toCharArray(), 5, 10, 10, 150, paint);
        //沿着path 绘制文字
        Path path = new Path();
        path.moveTo(10, 200);
        path.quadTo(100, 100, 300, 300);
        canvas.drawTextOnPath(text, path, 15, 15, paint);
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawPath(path, paint);

        ivText.setImageBitmap(bitmap);


    }

    @SuppressLint("NewApi")
    private void toCanvasPathMore() {
        Bitmap bitmap = Bitmap.createBitmap(500, 600, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        Path path = new Path();
        path.addRect(new RectF(10, 10, 110, 110), Path.Direction.CCW);
        Path path2 = new Path();
        path2.addCircle(100, 100, 50, Path.Direction.CCW);
        path.op(path2, Path.Op.DIFFERENCE);//差集
        canvas.drawPath(path, paint);
        ivPathMore.setImageBitmap(bitmap);

    }

    private void toCanvasBezier() {
        Bitmap bitmap = Bitmap.createBitmap(500, 600, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        Path path = new Path();
        path.moveTo(100, 100);
        path.quadTo(200, 10, 300, 300);
        canvas.drawPath(path, paint);
        paint.setStrokeWidth(4);
        paint.setColor(Color.RED);
        canvas.drawPoints(new float[]{100, 100, 200, 10, 300, 300}, paint);

        ivBezier.setImageBitmap(bitmap);
    }

    private void toCanvasPathAddGraph() {
        Bitmap bitmap = Bitmap.createBitmap(500, 600, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        Path path = new Path();
        //绘制矩形
        path.addRect(new RectF(10, 10, 300, 100), Path.Direction.CCW);//CCW 表示逆时针
        //圆角矩形  四个角弧度不一样，每两个数确定一个弧度
        path.addRoundRect(new RectF(10, 120, 300, 220),
                new float[]{10, 20, 20, 10, 20, 40, 40, 30}, Path.Direction.CCW);
        //椭圆
        path.addOval(new RectF(10, 240, 300, 340), Path.Direction.CCW);
        //圆
        path.addCircle(60, 390, 50, Path.Direction.CCW);
        //弧线
        path.addArc(new RectF(10, 500, 300, 600), -30, -60);
        canvas.drawPath(path, paint);
        ivPath.setImageBitmap(bitmap);
    }

    private void toCanvasPath() {
        Bitmap bitmap = Bitmap.createBitmap(500, 600, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        //绘制path
        Path path = new Path();
        path.moveTo(0, 150);//将画笔移动到点（x,y）位置，使用的绝对位置
        path.rLineTo(300, 0);//将画笔移动到一个新点，新点在上一个点的基础上偏移（xd,yd）,新点的坐标为（x+xd,y+yd）,同时在新点与上一个点画一条直线，使用相对位置
        path.rLineTo(-300, 150);
        path.rLineTo(150, -300);
        path.rLineTo(150, 300);
        path.close();//连接最后一个点和第一个点形成闭合图形
        canvas.drawPath(path, paint);
        ivStar.setImageBitmap(bitmap);

    }

    private void toCanvasArc(boolean isArcsStillSector) {
        Bitmap bitmap = Bitmap.createBitmap(500, 600, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        RectF rectF = new RectF(10, 10, 400, 300);
        paint.setColor(Color.GRAY);
        canvas.drawOval(rectF, paint);

        paint.setAntiAlias(true);
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(3);
        //绘制弧线
        // 第三个参数 userCenter 为true 表示利用中心点绘制一个扇形
        if (isArcsStillSector) {
            canvas.drawArc(rectF, -30, -30, true, paint);
            ivSector.setImageBitmap(bitmap);
        } else {
            canvas.drawArc(rectF, -30, -30, false, paint);
            ivArc.setImageBitmap(bitmap);
        }

    }

    @SuppressLint("NewApi")
    private void toCanvasRect() {
        Bitmap bitmapBuffer = Bitmap.createBitmap(500, 800, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapBuffer);
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        canvas.drawRoundRect(10, 10, 400, 300, 50, 30, paint);

        paint.setStyle(Paint.Style.STROKE);
        canvas.drawRoundRect(new RectF(10, 320, 400, 620), 30, 50, paint);

        ivRect.setImageBitmap(bitmapBuffer);

    }

    private void toCanvasLine() {
        Bitmap bitmapBuffer = Bitmap.createBitmap(500, 400, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapBuffer);

        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStrokeWidth(2);
        final int offetDy = 50;
        for (int i = 0; i < 5; i++) {
            canvas.drawLine(10, offetDy * i, 600, offetDy * i, paint);
        }
        ivLine.setImageBitmap(bitmapBuffer);
    }

    private void toCanvasPoint() {
        Bitmap bitmapBuffer = Bitmap.createBitmap(500, 600, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapBuffer);

        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStrokeWidth(8);
        //绘制一个红点
        canvas.drawPoint(120, 20, paint);
        paint.setColor(Color.BLUE);
        //绘制多个蓝点
        float[] points = new float[]{10, 10, 50, 50, 100, 50, 50, 150};
        canvas.drawPoints(points, paint);
        paint.setColor(Color.GREEN);
        //绘制两个绿点
        canvas.drawPoints(points, 1, 4, paint);

        ivPoint.setImageBitmap(bitmapBuffer);


    }

    private void toCanvasBitmap() {
        Bitmap bitmapBuffer = Bitmap.createBitmap(500, 400, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapBuffer);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);

        //绘制原始位图
        canvas.drawBitmap(bitmap, 0, 0, null);
        //对原始位图缩放
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Rect src = new Rect(0, 0, width, height);
        Rect dst = new Rect(0, height, width * 3, height * 3 + height);

        canvas.drawBitmap(bitmap, src, dst, null);
        ivBitmap.setImageBitmap(bitmapBuffer);


    }

    private void toUseCanvasAndPaint() {
        //绘制文字

        //首先创建一张空白位图
        Bitmap bitmap = Bitmap.createBitmap(400, 400, Bitmap.Config.ARGB_8888);
        //创建Canvas与位图关联
        Canvas canvas = new Canvas(bitmap);
        //创建Paint 绘制文字
        Paint paint = new Paint();
        paint.setAntiAlias(true);//防止边缘的锯齿
        paint.setTextAlign(Paint.Align.LEFT);
        paint.setTextSkewX(0.4f);//设置文字倾斜度，取值范围 0-1 ，正负表示方向
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(17);
        paint.setUnderlineText(true);
        paint.setFakeBoldText(true);//粗体
        canvas.drawText("xx科技，移动互联网的领导者！", 10, 50, paint);

        //绘制图形
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.RED);
        paint.setStrokeWidth(6);
        paint.setStrokeJoin(Paint.Join.ROUND);//当绘图样式为Stroke 时，该方法用于指定线条连接处的拐角样式
        canvas.drawRect(10, 100, 350, 250, paint);

        iv.setImageBitmap(bitmap);

    }
}
