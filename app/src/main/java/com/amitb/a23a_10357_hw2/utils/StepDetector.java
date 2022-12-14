package com.amitb.a23a_10357_hw2.utils;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import com.amitb.a23a_10357_hw2.interfaces.StepCallBack;


public class StepDetector {
    private StepCallBack stepCallBack;
    private SensorManager sensorManager;
    private Sensor sensor;

    private int stepCounterX = 0;
    private long timeStamp = 0;
    private SensorEventListener sensorEventListener;

    public StepDetector(Context context, StepCallBack _stepCallBack) {
        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        stepCallBack = _stepCallBack;
        initEventListener();
    }

    private void initEventListener() {
        sensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                float x = event.values[0];
                calculateStep(x);
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };
    }

    private void calculateStep(float x) {
        if (System.currentTimeMillis() - timeStamp > 500) {
            timeStamp = System.currentTimeMillis();
            if (x > 8.0) {
                stepCounterX++;
                if (stepCallBack != null)
                    stepCallBack.stepX();
            }
            else if (x < -8.0) {
                stepCounterX++;
                if (stepCallBack != null)
                    stepCallBack.stepX();
            }
        }
    }


    public void start(){
        sensorManager.registerListener(sensorEventListener,sensor,SensorManager.SENSOR_DELAY_NORMAL);
    }

    public void stop(){
        sensorManager.unregisterListener(sensorEventListener,sensor);
    }

    public int getStepCountX() {
        return stepCounterX;
    }

}
