package dhu.android.games.framework.impl;

import android.content.Context;
import android.os.Build.VERSION;
import android.view.View;
import dhu.android.games.framework.Input;
import dhu.android.games.framework.TouchHandler;

import java.util.List;

public class AndroidInput implements Input {
    AccelerometerHandler accelHandler;
    KeyboardHandler keyHandler;
    TouchHandler touchHandler;

    //入力検出のWrappingクラス
    public AndroidInput(Context context, View view, float scaleX, float scaleY) {
        accelHandler = new AccelerometerHandler(context);
        keyHandler = new KeyboardHandler(view);
        //if(Integer.parseInt(VERSION.SDK) < 5)
        if (VERSION.SDK_INT < 5) {
            touchHandler = new SingleTouchHandler(view, scaleX, scaleY);
        }
        else {
            touchHandler = new MultiTouchHandler(view, scaleX, scaleY);
        }
    }

    //
    @Override
    public boolean isKeyPressed(int keyCode) {
        return keyHandler.isKeyPressed(keyCode);
    }

    //タッチされたかどうか
    @Override
    public boolean isTouchDown(int pointer) {
        return touchHandler.isTouchDown(pointer);
    }

    //タッチされてX軸
    @Override
    public int getTouchX(int pointer) {
        return touchHandler.getTouchX(pointer);
    }

    //タッチされたY軸
    @Override
    public int getTouchY(int pointer) {
        return touchHandler.getTouchY(pointer);
    }

    //加速度センサのX軸
    @Override
    public float getAccelX() {
        return accelHandler.getAccelX();
    }

    //加速度センサーのY軸
    @Override
    public float getAccelY() {
        return accelHandler.getAccelY();
    }

    //加速度センサーのZ軸
    @Override
    public float getAccelZ() {
        return accelHandler.getAccelZ();
    }

    //入力検出
    @Override
    public List<TouchEvent> getTouchEvents() {
        return touchHandler.getTouchEvents();
    }

    @Override
    public List<KeyEvent> getKeyEvents() {
        return keyHandler.getKeyEvents();
    }
}
