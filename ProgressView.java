package com.itheima.googleplay_18.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.itheima.googleplay_18.R;

public class ProgressView extends LinearLayout {

    private ImageView mIvIcon;
    private TextView  mTvNote;

    private long mMax = 100;
    private long mProgress;
    private boolean isProgressEnable = true;

    /** 设置进度的最大值 */
    public void setMax(long max) {
        mMax = max;
    }

    /** 设置进度的当前值 */
    public void setProgress(long progress) {
        mProgress = progress;
        //重新绘制
        invalidate();
    }

    /** 设置是否允许进度 */
    public void setIsProgressEnable(boolean isProgressEnable) {
        this.isProgressEnable = isProgressEnable;
    }

    public void setIcon(int resId) {
        mIvIcon.setImageResource(resId);
    }

    public void setNote(String content) {
        mTvNote.setText(content);
    }

    public ProgressView(Context context) {
        this(context, null);
    }

    public ProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
        View view = View.inflate(context, R.layout.inflate_progressview, this);
        mIvIcon = (ImageView) view.findViewById(R.id.ivIcon);
        mTvNote = (TextView) view.findViewById(R.id.tvNote);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);//绘制背景
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);//绘制图片和文字

        if (isProgressEnable) {

            //绘制一些东西
            Paint paint = new Paint();

            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(3);
            paint.setAntiAlias(true);

            paint.setColor(Color.BLUE);
            //        canvas.drawText("我是文本", 20, 20, paint);

            float left = mIvIcon.getLeft();
            float top = mIvIcon.getTop();
            float right = mIvIcon.getRight();
            float bottom = mIvIcon.getBottom();
            RectF oval = new RectF(left, top, right, bottom);
            float startAngle = -90;
            float sweepAngle = mProgress * 1.0f / mMax * 360;
            boolean useCenter = false;
            //绘制圆弧
            canvas.drawArc(oval, startAngle, sweepAngle, useCenter, paint);
        }
    }
}
