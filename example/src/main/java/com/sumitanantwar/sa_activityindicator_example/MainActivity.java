package com.sumitanantwar.sa_activityindicator_example;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.sumitanantwar.sa_activityindicator.ActivityIndicatorType;
import com.sumitanantwar.sa_activityindicator.SAActivityIndicator;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final SAActivityIndicator pulseIndicator = (SAActivityIndicator) findViewById(R.id.Pulse);
        pulseIndicator.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (pulseIndicator.isAnimating())
                    pulseIndicator.stop();
                else
                    pulseIndicator.start();
            }
        });

        final SAActivityIndicator spinIndicator = (SAActivityIndicator) findViewById(R.id.Spin);
        spinIndicator.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (spinIndicator.isAnimating())
                    spinIndicator.stop();
                else
                    spinIndicator.start();
            }
        });
    }
}
