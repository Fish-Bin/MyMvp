package com.fish.hui.base;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.support.multidex.MultiDex;

import com.fish.liubin.L;

/**
 * Created by Administrator on 2018/5/9.
 */

public class BaseApplication extends Application {
    public static BaseApplication app;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);

    }

    @Override
    public void onCreate() {
        super.onCreate();
        app=this;
//        DaggerApplicationComponent.create().inject(this);
         registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
             @Override
             public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                 L.i(""+activity);
             }

             @Override
             public void onActivityStarted(Activity activity) {
                 L.i(""+activity);
             }

             @Override
             public void onActivityResumed(Activity activity) {
                 L.i(""+activity);
             }

             @Override
             public void onActivityPaused(Activity activity) {
                 L.i(""+activity);
             }

             @Override
             public void onActivityStopped(Activity activity) {
                 L.i(""+activity);
             }

             @Override
             public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
                 L.i(""+activity);
             }

             @Override
             public void onActivityDestroyed(Activity activity) {
                 L.i(""+activity);
             }
         });
    }
}
