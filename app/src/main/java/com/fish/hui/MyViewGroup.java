package com.fish.hui;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import com.fish.liubin.L;

/**
 * Created by Administrator on 2018/5/21.
 */

public class MyViewGroup extends LinearLayout {

    public MyViewGroup(Context context) {
        super(context);
    }

    public MyViewGroup(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyViewGroup(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        L.i("");
        return super.dispatchTouchEvent(ev);
        //1.已经被禁用了事件拦截功能或者是不拦截事件
    }

    @Override
    public void requestDisallowInterceptTouchEvent(boolean disallowIntercept) {
        L.i("");
        super.requestDisallowInterceptTouchEvent(disallowIntercept);
        //是否禁用事件拦截，默认返回false,
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        L.i("");//down的时候不能拦截，否则传递不到子View，就不能用内部拦截法
        if(ev.getAction()==MotionEvent.ACTION_DOWN){
            return false;
        }else {
            return true;
        }
        //是否拦截事件
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        L.i("");
        return super.onTouchEvent(event);
    }
}
