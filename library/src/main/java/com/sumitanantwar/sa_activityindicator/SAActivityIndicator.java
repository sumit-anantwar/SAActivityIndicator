package com.sumitanantwar.sa_activityindicator;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.shapes.OvalShape;
import android.graphics.drawable.shapes.Shape;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * Created by Leo on 10/16/2016.
 */

public class SAActivityIndicator extends RelativeLayout
{

    private static final int DEFAULT_ACTIVITY_INDICATOR_COLOR   = Color.rgb(100, 100, 100);
    private static final int DEFAULT_INDICATOR_TYPE_INDEX       = 0;
    private static final float DEFAULT_INDICATOR_SIZE           = 100;

    private ActivityIndicatorType indicatorType;
    private int indicatorColor;
    private float indicatorSize;

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

        initialize();
    }

    private void initWithAttributes(TypedArray attributes)
    {
        indicatorType   = ActivityIndicatorType.getEnumFromIndex(attributes.getInt(R.styleable.SAActivityIndicator_activity_indicator_type, DEFAULT_INDICATOR_TYPE_INDEX));
        indicatorColor  = attributes.getColor(R.styleable.SAActivityIndicator_activity_indicator_color, DEFAULT_ACTIVITY_INDICATOR_COLOR);
        indicatorSize   = attributes.getDimension(R.styleable.SAActivityIndicator_activity_indicator_size, DEFAULT_INDICATOR_SIZE);
    }

    private void initialize()
    {

    }

    private void generatePulsatingIndicator()
    {
        Shape circle = new OvalShape();

    }
}
