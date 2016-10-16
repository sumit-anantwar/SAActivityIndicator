package com.sumitanantwar.sa_activityindicator;

/**
 * Created by Sumit Anantwar on 10/17/16.
 */

public enum ActivityIndicatorType
{
    PULSATING(0),
    SPINNER(1);

    private final int index;

    ActivityIndicatorType(int index) { this.index = index;}

    public static ActivityIndicatorType getEnumFromIndex(int index)
    {
        for (ActivityIndicatorType type : ActivityIndicatorType.values())
        {
            if (type.index == index) return type;
        }

        return null;
    }
}
