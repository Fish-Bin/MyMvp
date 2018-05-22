package com.fish.hui.dagger.module;

import com.fish.hui.net.RxjavaRetrofit;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2018/5/18.
 */
@Module()
public class ApplicationModule {
    @Provides
    RxjavaRetrofit provideRxjavaRetrofit() {
        return new RxjavaRetrofit();
    }
}
