package com.github.mikephil.charting.renderer;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.NinePatch;
import android.graphics.Path;
import android.graphics.RectF;

import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.interfaces.datasets.ILineScatterCandleRadarDataSet;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;

/**
 * Created by Philipp Jahoda on 11/07/15.
 */
public abstract class LineScatterCandleRadarRenderer extends BarLineScatterCandleBubbleRenderer {

    /**
     * path that is used for drawing highlight-lines (drawLines(...) cannot be used because of dashes)
     */
    private Path mHighlightLinePath = new Path();

    public LineScatterCandleRadarRenderer(ChartAnimator animator, ViewPortHandler viewPortHandler) {
        super(animator, viewPortHandler);
    }

    /**
     * Draws vertical & horizontal highlight-lines if enabled.
     *
     * @param c
     * @param x   x-position of the highlight line intersection
     * @param y   y-position of the highlight line intersection
     * @param set the currently drawn dataset
     */
    protected void drawHighlightLines(Canvas c, float x, float y, ILineScatterCandleRadarDataSet set) {

        // set color and stroke-width
        mHighlightPaint.setColor(set.getHighLightColor());
        mHighlightPaint.setStrokeWidth(set.getHighlightLineWidth());

        // draw highlighted lines (if enabled)
        mHighlightPaint.setPathEffect(set.getDashPathEffectHighlight());

        //优先使用高亮线图片显示，没有则使用默认样式
        if (set.getHighLightBitmap() != null) {
            if (set.isVerticalHighlightIndicatorEnabled()) {
                Bitmap highLightBitmap = set.getHighLightBitmap();
                NinePatch ninePatch = new NinePatch(highLightBitmap,
                        highLightBitmap.getNinePatchChunk(), null);
                int highLightWidth = (int) Utils.convertDpToPixel(14.0F);
                RectF rectF = new RectF(x - (float) (highLightWidth / 2),
                        this.mViewPortHandler.contentTop(),
                        x + (float) (highLightWidth / 2),
                        this.mViewPortHandler.contentBottom());
                ninePatch.draw(c, rectF);
            }
        } else {
            // draw vertical highlight lines
            if (set.isVerticalHighlightIndicatorEnabled()) {

                // create vertical path
                mHighlightLinePath.reset();
                mHighlightLinePath.moveTo(x, mViewPortHandler.contentTop());
                mHighlightLinePath.lineTo(x, mViewPortHandler.contentBottom());

                c.drawPath(mHighlightLinePath, mHighlightPaint);
            }

            // draw horizontal highlight lines
            if (set.isHorizontalHighlightIndicatorEnabled()) {

                // create horizontal path
                mHighlightLinePath.reset();
                mHighlightLinePath.moveTo(mViewPortHandler.contentLeft(), y);
                mHighlightLinePath.lineTo(mViewPortHandler.contentRight(), y);

                c.drawPath(mHighlightLinePath, mHighlightPaint);
            }
        }
    }
}