package com.faw.seniar9;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.WindowManager;

import com.faw.seniar9.util.PhoneUtil;
import com.wyc.c217_car.R;

/**
 * Created by admin on 2016/7/5.
 */
public class MyProgressView extends View {
    private static final String TAG = MyProgressView.class.getSimpleName();
    private final int min_size=0;
    private int progress;//进度条的进度
    private int max=100;//进度条的最大值
    private Paint paint = new Paint();
    private RectF rectF = new RectF();
    private final int default_finished_color = Color.rgb(66, 145, 241);
    private final int default_unfinished_color = Color.rgb(204, 204, 204);
    private int finishedColor=0xff1a75d5;
    private int unfinishedColor;
    private String prefixText = "";
    private String suffixText = "%";
    private final int default_max = 100;
    public MyProgressView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initPainters();
    }
    public MyProgressView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyProgressView(Context context) {
        this(context,null);
    }
    /**
     * 画笔的一些设置
     */
    private void initPainters() {
        paint.setAntiAlias(true);
    }


    /**
     * 给max赋值
     * @param max
     */
    private void setMax(int max) {
        if (max > 0) {
            this.max = max;
            invalidate();
        }
    }
    /**
     * 设置进度
     */
    public void setProgress(int progress) {
        this.progress = progress;
        if(this.progress > getMax()) {
            this.progress %= getMax();
        }
        invalidate();//重新绘制界面 ---ondraw()
    }
    /**
     * 获取最大进度
     * @return
     */
    private int getMax() {
        return max;
    }
    public int getProgress() {
        return progress;
    }
    public int getUnfinishedColor() {
        return unfinishedColor;
    }
    public int getFinishedColor() {
        return finishedColor;
    }
    public String getDrawText() {
        return getPrefixText() + getProgress() + getSuffixText();
    }
    public String getPrefixText() {
        return prefixText;
    }
    public String getSuffixText() {
        return suffixText;
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        rectF.set(0, 0, MeasureSpec.getSize(widthMeasureSpec), MeasureSpec.getSize(heightMeasureSpec));//设置圆的外切  正方形
        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float yHeight = getProgress() / (float) getMax() * getHeight();
        float radius = getWidth() / 2f;
        float angle = (float) (Math.acos((radius - yHeight) / radius) * 180 / Math.PI);//开始角度
        float startAngle = 90 + angle;
        float sweepAngle = 360 - angle * 2;
        paint.setColor(getUnfinishedColor());
        canvas.drawArc(rectF, startAngle, sweepAngle, false, paint);

        canvas.save();
        canvas.rotate(180, radius, radius);//围绕圆心旋转180度
        paint.setColor(getFinishedColor());
        canvas.drawArc(rectF, 270 - angle, angle * 2, false, paint);
        canvas.restore();

//        String text = getDrawText();
//        if (!TextUtils.isEmpty(text)) {
//            float textHeight = textPaint.descent() + textPaint.ascent();
//            canvas.drawText(text, (getWidth() - textPaint.measureText(text)) / 2.0f, (getWidth() - textHeight) / 2.0f, textPaint);
//        }
    }
}