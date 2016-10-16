package com.sumitanantwar.sa_activityindicator;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * Created by Leo on 10/16/2016.
 */

public class SAActivityIndicator extends RelativeLayout
{
    public enum ActivityIndicatorType
    {
        PULSATING(0),
        SPINNER(1);

        public final int index;

        ActivityIndicatorType(int index) { this.index = index;}
    }

    private static final int ACTIVITY_INDICATOR_COLOR = Color.rgb(100, 100, 100);

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

        final TypedArray attributes =  context.getTheme().obtainStyledAttributes(attrs, R.styleable.SAActivityIndicator, defStyleAttr, 0);
        initWithAttributes(attributes);
        attributes.recycle();
    }

    private void initWithAttributes(TypedArray attributes)
    {

    }
}
