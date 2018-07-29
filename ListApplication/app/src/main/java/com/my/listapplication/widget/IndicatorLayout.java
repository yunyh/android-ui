package com.my.listapplication.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.FrameLayout;

import com.my.listapplication.R;

import java.util.List;

public class IndicatorLayout extends FrameLayout {

    private static final String TAG = "IndicatorLayout";

    private Drawable dot;

    private int[] range;

    private Rect rect = new Rect();
    public int selectedPosition;

    public IndicatorLayout(@NonNull Context context) {
        super(context);
        init(context);
    }

    public IndicatorLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public IndicatorLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(@NonNull Context context) {
        setWillNotDraw(true);
        dot = ContextCompat.getDrawable(context, R.drawable.indicator_dot);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        drawIndicator(canvas);
        canvas.restore();
    }

    private void drawIndicator(Canvas canvas) {
        if (range == null || range.length <= 0) {
            return;
        }

        int radius = getHeight() / 2;
        int space = radius / 2;
        int x = (getWidth()- ((radius * range.length) + (space * (range.length - 1)))) / 2;


        for (int index = 0; index < range.length; index++) {
            //  Log.d(TAG, "draw indicator");
            rect.left = x;
            if (index != 0) {
                rect.left += ((space + radius) * index);
                Log.d(TAG, "draw indicator " + rect.left);
            }
            rect.right = rect.left + radius;
            rect.top = getTop() + 10;
            rect.bottom = rect.top + radius;

            dot.setBounds(rect);
            if (selectedPosition == index) {
                DrawableCompat.setTint(dot, ContextCompat.getColor(getContext(), R.color.colorAccent));
            } else {
                DrawableCompat.setTint(dot, ContextCompat.getColor(getContext(), R.color.colorPrimary));
            }
            dot.draw(canvas);
        }
    }

    public void setViewPager(final ViewPager viewPager) {
        if (viewPager == null) {
            return;
        }
        viewPager.addOnAdapterChangeListener(new ViewPager.OnAdapterChangeListener() {
            @Override
            public void onAdapterChanged(@NonNull ViewPager viewPager, @Nullable PagerAdapter oldAdapter, @Nullable PagerAdapter newAdapter) {
                Log.d(TAG, "" + viewPager.getChildCount());
                if (newAdapter != null) {
                    range = new int[newAdapter.getCount()];
                    invalidate();
                }
            }
        });
        if (viewPager.getAdapter() != null) {
            Log.d(TAG, "" + viewPager.getAdapter().getCount());
            range = new int[viewPager.getAdapter().getCount()];
            selectedPosition = viewPager.getCurrentItem();
            invalidate();
        }

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                selectedPosition = position;
                invalidate();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
