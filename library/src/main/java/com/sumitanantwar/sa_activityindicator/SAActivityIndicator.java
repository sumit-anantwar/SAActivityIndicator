package com.sumitanantwar.sa_activityindicator;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.graphics.drawable.shapes.Shape;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

/**
 * Created by Leo on 10/16/2016.
 */

public class SAActivityIndicator extends RelativeLayout
{

    private static final int DEFAULT_ACTIVITY_INDICATOR_COLOR   = Color.rgb(100, 100, 100);
    private static final int DEFAULT_INDICATOR_TYPE_INDEX       = 0;
    private static final float DEFAULT_INDICATOR_SIZE           = 100;

    private FrameLayout container;

    private Context mContext;
    private Animation mAnimation;
    private ActivityIndicatorType mActivityIndicatorType;
    private int mIndicatorColor;
    private float mIndicatorSize;

    private boolean isAnimating = false;

    public SAActivityIndicator(Context context)
    {
        this(context, null);
    }

    public SAActivityIndicator(Context context, AttributeSet attrs)
    {
        this(context, attrs, 0);
    }

    public SAActivityIndicator(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);

        mContext = context;

        // Add the Main Container
        addContainer();

        // Initialize with Attributes
        final TypedArray attributes =  context.getTheme().obtainStyledAttributes(attrs, R.styleable.SAActivityIndicator, defStyleAttr, 0);
        initWithAttributes(attributes);
        attributes.recycle();

    }

    private void addContainer()
    {
        container = new FrameLayout(mContext);
        container.setBackgroundColor(Color.YELLOW);
        addView(container);

        RelativeLayout.LayoutParams containerLayoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        containerLayoutParams.addRule(CENTER_IN_PARENT, RelativeLayout.TRUE);
        container.setLayoutParams(containerLayoutParams);
    }

    private void initWithAttributes(TypedArray attributes)
    {
        mIndicatorColor = attributes.getColor(R.styleable.SAActivityIndicator_activity_indicator_color, DEFAULT_ACTIVITY_INDICATOR_COLOR);
        mIndicatorSize = attributes.getDimension(R.styleable.SAActivityIndicator_activity_indicator_size, DEFAULT_INDICATOR_SIZE);

        setActivityIndicatorType(ActivityIndicatorType.getEnumFromIndex(attributes.getInt(R.styleable.SAActivityIndicator_activity_indicator_type, DEFAULT_INDICATOR_TYPE_INDEX)));
    }

    private View pulsatingIndicatorView()
    {
        GradientDrawable background = new GradientDrawable();
        background.setShape(GradientDrawable.OVAL);
        background.setColor(mIndicatorColor);

        View view = new View(mContext);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams((int)mIndicatorSize, (int)mIndicatorSize);
        view.setLayoutParams(params);
        view.setBackgroundDrawable(background);

        return view;
    }

    private View spinnerIndicatorView()
    {
        GradientDrawable background = new GradientDrawable();
        background.setShape(GradientDrawable.RECTANGLE);
        background.setColor(mIndicatorColor);

        View view = new View(mContext);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams((int)mIndicatorSize, (int)mIndicatorSize);
        view.setLayoutParams(params);
        view.setBackgroundDrawable(background);

        return view;
    }

    public void setActivityIndicatorType(ActivityIndicatorType indicatorType)
    {
        // Empty the container, if it already contains an Activity Indicator
        if (container.getChildCount() > 0) container.removeAllViews();

        mActivityIndicatorType = indicatorType;
        switch (mActivityIndicatorType)
        {
            case PULSATING:
                container.addView(pulsatingIndicatorView());
                break;

            case SPINNER:
                container.addView(spinnerIndicatorView());
                break;
        }
    }

    public void start()
    {
        switch (mActivityIndicatorType)
        {
            case PULSATING:
                mAnimation = AnimationUtils.loadAnimation(mContext, R.anim.pulse);
                break;

            case SPINNER:
                mAnimation = AnimationUtils.loadAnimation(mContext, R.anim.spin);
                break;
        }

        isAnimating = true;
        container.startAnimation(mAnimation);
    }

    public void stop()
    {
        isAnimating = false;
        container.clearAnimation();
    }

    public boolean isAnimating()
    {
        return isAnimating;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = (int)mIndicatorSize;
        int height = (int)mIndicatorSize;

        switch (mActivityIndicatorType)
        {
            case PULSATING:
                width *= 1.5;
                height *= 1.5;
                break;

            case SPINNER:
                break;
        }

        setMeasuredDimension(width, height);
    }

    private int measure(int measureSpec)
    {
        int result;
        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);
        if (mode == MeasureSpec.EXACTLY)
        {
            result = size;
        } else
        {
            result = (int)DEFAULT_INDICATOR_SIZE;
            if (mode == MeasureSpec.AT_MOST)
            {
                result = Math.min(result, size);
            }
        }
        return result;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom)
    {
        super.onLayout(changed, left, top, right, bottom);
//        final int count = getChildCount();
//
//        for (int i = 0; i < count; i++) {
//            View child = getChildAt(i);
//            if (child.getVisibility() != GONE) {
//                RelativeLayout.LayoutParams st =
//                        (RelativeLayout.LayoutParams) child.getLayoutParams();
//                child.layout(st.leftMargin, st.topMargin, st.rightMargin, st.bottomMargin);
//            }
//        }


    }
}
