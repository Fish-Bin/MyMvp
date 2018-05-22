package com.fish.hui.dagger.component;

import com.fish.hui.dagger.module.ApplicationModule;
import com.fish.hui.net.RxjavaRetrofit;

import dagger.Component;

/**
 * Created by Administrator on 2018/5/18.
 */
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    RxjavaRetrofit initRxjavaRetrofit();
}
