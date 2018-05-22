package com.fish.hui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.TextView;

import com.fish.liubin.L;

/**
 * Created by Administrator on 2018/5/21.
 */

@SuppressLint("AppCompatCustomView")
public class MyView extends TextView {
    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        L.i("");
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //通知父容器不要拦截事件
                getParent().requestDisallowInterceptTouchEvent(true);
                break;
            case MotionEvent.ACTION_MOVE:
                if (true) {
                    //通知父容器拦截此事件
                    getParent().requestDisallowInterceptTouchEvent(false);
                }
            case MotionEvent.ACTION_UP:
                getParent().requestDisallowInterceptTouchEvent(false);
            default:
                break;
        }
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        L.i("");
        return super.onTouchEvent(event);
    }
}
