package com.fish.hui.net;

import com.fish.hui.bean.BaseHttpBean;
import com.fish.hui.bean.CityJsonBean;
import com.fish.liubin.L;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;

/**
 * Created by Administrator on 2018/5/17.
 */

public class RxjavaRetrofit {

    public RxjavaRetrofit(){}

    @Inject
    HttpInterface httpInterface;

    /**
     * @param loadMessage   加载框的信息
     * @param observable    被观察者
     * @param nextListener  观察者处理结果
     * @param errorListener 异常监听
     */
    private synchronized <T extends Object> void request(final String loadMessage, Observable<BaseHttpBean<T>> observable, Consumer<BaseHttpBean<T>> nextListener, Consumer<Throwable> errorListener) {
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        L.i("加载框的信息：" + loadMessage);
                    }
                })
                .subscribe(nextListener, errorListener);
    }

    /**
     * @param loadMessage  加载框的信息
     * @param observable   被观察者
     * @param httpListener 自己封装的回调
     * @param <T>          实体类
     */
    public <T extends Object> void requestOut(final String loadMessage, Observable<BaseHttpBean<T>> observable, final HttpListener<BaseHttpBean<T>> httpListener) {
        final Consumer<BaseHttpBean<T>> consumerNext = new Consumer<BaseHttpBean<T>>() {
            @Override
            public void accept(BaseHttpBean<T> tBaseHttpBean) throws Exception {
                L.i("取消加载框");
                httpListener.onSuccess(tBaseHttpBean);
            }
        };
        Consumer<Throwable> consumerError = new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                L.i("取消加载框");
                if (throwable instanceof JsonSyntaxException) {
                    L.i("网络返回数据错误");
                } else if (throwable instanceof NullPointerException) {
                    L.i("必须字段不能为null");
                } else if (throwable instanceof IOException) {
                    L.i("io 异常");
                } else if (throwable instanceof TimeoutException) {
                    L.i("连接超时");
                } else if (throwable instanceof HttpException) {
                    L.i("http 异常");
                } else {
                    L.i(throwable.getMessage());
                }
                httpListener.onFailed(throwable.getMessage());
            }
        };
        request(loadMessage, observable, consumerNext, consumerError);
    }

    public void test() {
        requestOut("", httpInterface.getImgCode(), new HttpListener<BaseHttpBean<CityJsonBean>>() {
            @Override
            public void onSuccess(BaseHttpBean<CityJsonBean> response) {

            }

            @Override
            public void onFailed(String message) {
                super.onFailed(message);
            }
        });
    }
}
