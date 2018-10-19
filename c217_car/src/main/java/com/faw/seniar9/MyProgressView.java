package com.faw.seniar9;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.WindowManager;

import com.faw.seniar9.util.PhoneUtil;

/**
 * Created by admin on 2016/7/5.
 */
public class MyProgressView extends View {
    private static final String TAG = "MyProgressView";
    private Paint textPaint;//绘制文字的画笔
    private Paint anglePaint;//这是画弧的画笔
    private Paint reanglePaint;
    private int max = 100;
    private int progress;
    private Context context;
    private int height=0;

    public MyProgressView(Context context) {
        this(context, null);
        this.context = context;
    }

    public MyProgressView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        this.context = context;
    }

    public MyProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        WindowManager wm = ((Activity) context).getWindowManager();
        initPainters();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        height=PhoneUtil.dip2px(context, 142f);
        RectF rectF = new RectF(0, 0,height, height);
        float yHeight = getProgress() / (float) max * height;
        float angle = (float) (Math.acos((height / 2 - yHeight) / height / 2) * 180 / Math.PI);
        float startAngle = 90 + angle;//起始弧度
        float sweepAngle = 360 - angle * 2;//所经过的弧度
        canvas.drawArc(rectF, startAngle, sweepAngle, false, anglePaint);
        canvas.save();
        canvas.rotate(180, height / 2, height / 2);//围绕rectF矩形所在的内切圆的圆心进行旋转
        reanglePaint.setColor(Color.parseColor("#1a75d5"));
        canvas.drawArc(rectF, 270 - angle, angle * 2, false, reanglePaint);
        canvas.restore();
    }

    /**
     * 设置进度值,实时更新进度
     *
     * @param progress
     */
    public void setProgress(int progress) {
        this.progress = progress;
        if (this.progress > max) {
            this.progress %= max;
        }
        invalidate();
    }

    /**
     * 获取当前进度值
     *
     * @return
     */
    public int getProgress() {
        return progress;
    }

    private void initPainters() {
        textPaint = new Paint();
        textPaint.setTextSize(36);
        textPaint.setAntiAlias(true);
        anglePaint = new Paint();
        anglePaint.setStrokeWidth(8);
        anglePaint.setColor(Color.parseColor("#1f395f"));
        anglePaint.setAntiAlias(true);

        reanglePaint = new Paint();
        reanglePaint.setAntiAlias(true);
    }
}