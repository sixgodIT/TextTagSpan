package com.sixit.textspan;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.text.style.ReplacementSpan;

/**
 * 使用ReplacementSpan在TextView中自定义Tag
 */
public class TextTagSpan extends ReplacementSpan {
    private int width;
    private int height;
    private float radius = 0;
    private int leftMargin;
    private int rightMargin;
    private int strokeColor;
    private float strokeWidth;
    @DrawableRes
    private int background = 0;
    private TextPaint mTextPaint;
    private Context mContext;

    public TextTagSpan(Context context, int width, int height) {
        mContext = context;
        this.width = width;
        this.height = height;
        mTextPaint = new TextPaint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setTextAlign(Paint.Align.CENTER);

    }


    public TextTagSpan setTextColor(@ColorInt int textColor) {
        mTextPaint.setColor(textColor);
        return this;
    }

    public TextTagSpan setStrokeColor(@ColorInt int strokeColor) {
        this.strokeColor = strokeColor;
        return this;
    }

    public TextTagSpan setTextSize(float textSize) {
        mTextPaint.setTextSize(textSize);
        return this;
    }


    public TextTagSpan setRadius(float radius) {
        this.radius = radius;
        return this;
    }

    public TextTagSpan setStrokeWidth(float strokeWidth) {
        this.strokeWidth = strokeWidth;
        return this;
    }

    public TextTagSpan setLeftMargin(int leftMargin) {
        this.leftMargin = leftMargin;
        return this;
    }

    public TextTagSpan setRightMargin(int rightMargin) {
        this.rightMargin = rightMargin;
        return this;
    }

    /**
     * 设置自定义背景，如果有自定义背景，则忽略stroke。
     *
     * @param background
     * @return
     */
    public TextTagSpan setBackground(@DrawableRes int background) {
        this.background = background;
        return this;
    }

    @Override
    public int getSize(@NonNull Paint paint, CharSequence charSequence, int start, int end, @Nullable Paint.FontMetricsInt fontMetricsInt) {
        return width + leftMargin + rightMargin;
    }

    @Override
    public void draw(@NonNull Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom, @NonNull Paint paint) {
        canvas.save();
        Rect strokeRect = drawTagRect(canvas, x, y, paint);
        //drawText
        Paint.FontMetricsInt fontMetrics = mTextPaint.getFontMetricsInt();
        float textCenterX = x + leftMargin + width / 2;
        //baseline 计算，在strokeRect中的baseline计算，需要用到strokeRect top和height
        float textBaselineY = height / 2 + (Math.abs(fontMetrics.ascent) - fontMetrics.descent) / 2 + strokeRect.top;

        final String tag = text.subSequence(start, end).toString();
        canvas.drawText(tag, textCenterX, textBaselineY, mTextPaint);
        canvas.restore();
    }


    /**
     * 绘制背景边框，并返回边框的Rect，供text计算位置使用
     *
     * @return
     */
    private Rect drawTagRect(Canvas canvas, float x, int y, Paint paint) {
        Paint.FontMetricsInt fontMetrics = paint.getFontMetricsInt();
        int fontHeight = fontMetrics.descent - fontMetrics.ascent;

        int top = y + fontMetrics.ascent + (fontHeight - height) / 2;
        Rect rect = new Rect((int) x + leftMargin, top, (int) (x + leftMargin + width), top + height);
        //如果有自定义背景，则忽略stroke。
        if (background != 0) {
            Drawable drawable = mContext.getResources().getDrawable(background);
            drawable.setBounds(rect);
            drawable.draw(canvas);
        } else {
            paint.setColor(strokeColor);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(strokeWidth);
            paint.setAntiAlias(true);
            RectF oval = new RectF(rect);
            canvas.drawRoundRect(oval, radius, radius, paint);
        }

        return rect;
    }
}
