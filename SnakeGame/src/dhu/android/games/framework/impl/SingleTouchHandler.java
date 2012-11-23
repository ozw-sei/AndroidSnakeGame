package dhu.android.games.framework.impl;

import android.view.MotionEvent;
import android.view.View;
import dhu.android.games.framework.Pool;
import dhu.android.games.framework.TouchHandler;
import dhu.android.games.framework.Input.TouchEvent;
import dhu.android.games.framework.Pool.PoolObjectFactory;

import java.util.ArrayList;
import java.util.List;

public class SingleTouchHandler implements TouchHandler {
    boolean isTouched;
    int touchX;
    int touchY;
    Pool<TouchEvent> touchEventPool;
    List<TouchEvent> touchEvents = new ArrayList<TouchEvent>();
    List<TouchEvent> touchEventsBuffer = new ArrayList<TouchEvent>();
    final float scaleX;
    final float scaleY;

    //タッチの認識
    public SingleTouchHandler(View view, float scaleX, float scaleY) {
        PoolObjectFactory<TouchEvent> factory = new PoolObjectFactory<TouchEvent>() {
            @Override
            public TouchEvent createObject() {
                return new TouchEvent();
            }
        };
        touchEventPool = new Pool<TouchEvent>(factory, 100);
        view.setOnTouchListener(this);

        this.scaleX = scaleX;
        this.scaleY = scaleY;
    }

    //特定のタッチをされたかどうか
    @Override
    public boolean onTouch(View v, MotionEvent e) {
        synchronized (this) {
            TouchEvent touchEvent = touchEventPool.newObject();
            switch (e.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    touchEvent.type = TouchEvent.TOUCH_DOWN;
                    isTouched = true;
                    break;
                case MotionEvent.ACTION_MOVE:
                    touchEvent.type = TouchEvent.TOUCH_DRAGGED;
                    isTouched = true;
                    break;
                case MotionEvent.ACTION_CANCEL:
                case MotionEvent.ACTION_UP:
                    touchEvent.type = TouchEvent.TOUCH_UP;
                    isTouched = false;
                    break;
            }
            touchEvent.x = touchX = (int) (e.getX() * scaleX);
            touchEvent.y = touchY = (int) (e.getY() * scaleY);
            touchEventsBuffer.add(touchEvent);
            return true;
        }
    }

    @Override
    public boolean isTouchDown(int pointer) {
        synchronized (this) {
            if (pointer == 0) {
                return isTouched;
            }
            else {
                return false;
            }
        }
    }

    //タッチされたX軸
    @Override
    public int getTouchX(int pointer) {
        synchronized (this) {
            return touchX;
        }
    }

    //タッチされたY軸
    @Override
    public int getTouchY(int pointer) {
        synchronized (this) {
            return touchY;
        }
    }

    //タッチイベントを返す
    @Override
    public List<TouchEvent> getTouchEvents() {
        synchronized (this) {
            int len = touchEvents.size();
            for (int i = 0; i < len; i++) {
                touchEventPool.free(touchEvents.get(i));
            }
            touchEvents.clear();
            touchEvents.addAll(touchEventsBuffer);
            touchEventsBuffer.clear();
            return touchEvents;
        }
    }
}
