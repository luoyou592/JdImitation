package com.young.jdmall.ui.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.young.jdmall.R;

/**
 * Created by BjyJyk on 2017/8/5.
 */

public class GuaGuaLeView  extends  View{

        //被覆盖的内容图层
        private Bitmap bpBackground;
        //用来当做覆盖用的图层
        private Bitmap bpForeground;
        //用来当做覆盖用的图层的画布
        private Canvas mCanvas;
        //模拟手指头刮开路径的画笔
        private Paint pathPaint;
        //手指头刮开的路径
        private Path path;

        //用来当做覆盖用的图层的文字画笔
        private Paint contentPaint;
        //用来当做覆盖用的图层的文字内容
        private String content = "";

        public GuaGuaLeView(Context context) {
            super(context);
            init();
        }

        public GuaGuaLeView(Context context, AttributeSet attrs) {
            super(context, attrs);
            init();
        }

        private void init(){

            //初始化模拟刮开路径的画笔
            pathPaint = new Paint();
            pathPaint.setAlpha(0);
            pathPaint.setStyle(Paint.Style.STROKE);
            pathPaint.setStrokeWidth(50);
            //取两层绘制交集，显示下层
            pathPaint.setXfermode(new PorterDuffXfermode((PorterDuff.Mode.DST_IN)));
            pathPaint.setStrokeJoin(Paint.Join.ROUND);
            pathPaint.setStrokeCap(Paint.Cap.ROUND);


            //初始化模拟手指头刮开的路径
            path = new Path();


            //初始化被覆盖的内容bitmap
            bpBackground = BitmapFactory.decodeResource(getResources(), R.mipmap.ah8);
            //初始化用来当做覆盖用的bitmap
            bpForeground = Bitmap.createBitmap(bpBackground.getWidth(),bpBackground.getHeight(), Bitmap.Config.ARGB_8888);

            //初始化画布
            mCanvas = new Canvas(bpForeground);

            //初始化内容画笔
            contentPaint = new Paint();
            contentPaint.setColor(Color.WHITE);
            contentPaint.setTextSize(100);
            contentPaint.setStrokeWidth(20);

            //设置用来当做覆盖用的图层颜色为灰色
            mCanvas.drawColor(Color.GRAY);
            //在用来当做覆盖用的图层上绘制文字
            mCanvas.drawText(content,mCanvas.getWidth()/4,mCanvas.getHeight()/2,contentPaint);

        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {

            switch (event.getAction()){
                case MotionEvent.ACTION_DOWN:
                    //清空画笔
                    path.reset();
                    //原点移动至手指的触摸点
                    path.moveTo(event.getX(),event.getY());
                    break;
                case MotionEvent.ACTION_MOVE:

                    path.lineTo(event.getX(),event.getY());
                    break;
            }

            //模拟刮来效果
            mCanvas.drawPath(path,pathPaint);
            invalidate();
            return true;

        }

        @Override
        protected void onDraw(Canvas canvas) {

            //绘制两个图层
            canvas.drawBitmap(bpBackground,0,0,null);
            canvas.drawBitmap(bpForeground,0,0,null);

        }

}
