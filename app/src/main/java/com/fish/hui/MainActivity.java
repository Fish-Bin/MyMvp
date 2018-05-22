package com.fish.hui;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.fish.hui.arouter.ARouterConst;
import com.fish.hui.net.RxjavaRetrofit;
import com.fish.hui.rxjava.EgRxjava;
import com.fish.liubin.L;

@Route(path = ARouterConst.Activity_MainActivity)
public class MainActivity extends AppCompatActivity {
    MyView myView;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        EgRxjava.text3();
        myView = findViewById(R.id.my_view);
        tv = findViewById(R.id.tv);
        myView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                L.i("");
            }
        });

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        L.i("");
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        L.i("");
        return super.onTouchEvent(event);
    }

}
