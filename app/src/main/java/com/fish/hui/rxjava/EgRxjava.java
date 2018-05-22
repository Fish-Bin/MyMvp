package com.fish.hui.rxjava;


import android.content.res.AssetManager;
import android.graphics.Bitmap;

import com.fish.hui.base.BaseApplication;
import com.fish.hui.bean.CityJsonBean;
import com.fish.liubin.L;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.reactivestreams.Subscriber;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/5/17.
 */

public class EgRxjava {

    public static ArrayList<String> names=new ArrayList<>();

    public static void text3(){
        names.add("bin");
        names.add("hui");
        names.add("liu");
        names.add("wang");
        names.add("hu");
        names.add("zhan");
        names.add("hua");
        names.add("tian");
        Observable.fromIterable(names)
                .map(new Function<String, String>() {
                    @Override
                    public String apply(String s) throws Exception {
                        L.i(s);
                        return s+"ok";
                    }
                })
                .subscribeOn(Schedulers.single())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        L.i("s="+s);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        L.i(throwable.getMessage());
                    }
                });
    }

    public static void text2() {
        Disposable subscribe = Observable.just("liu bin")
                .map(new Function<String, String>() {
                    @Override
                    public String apply(String s) throws Exception {
                        return s+"a";
                    }
                })
                .subscribeOn(Schedulers.single())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String bitmap) throws Exception {
                        if (bitmap == null) {
                            L.i("bitmap is null");
                        } else {
                            L.i("bitmap is not null");
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        L.i(throwable.getMessage());
                    }
                });

    }

    public static void text1() {
        Disposable disposable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                StringBuilder sb = new StringBuilder();
                AssetManager assets = BaseApplication.app.getAssets();
                BufferedReader bf = new BufferedReader(new InputStreamReader(assets.open("province.json")));
                String line;
                while ((line = bf.readLine()) != null) {
                    sb.append(line);
                }
                e.onNext(sb.toString());
                e.onComplete();
            }
        }).map(new Function<String, JSONArray>() {
            @Override
            public JSONArray apply(String s) throws Exception {
                L.i("s=" + s);
                return new JSONArray(s);
            }
        }).map(new Function<JSONArray, ArrayList<CityJsonBean>>() {
            @Override
            public ArrayList<CityJsonBean> apply(JSONArray jsonArray) throws Exception {
                ArrayList<CityJsonBean> detail = new ArrayList<>();
                Gson gson = new Gson();
                for (int i = 0; i < jsonArray.length(); i++) {
                    CityJsonBean entity = gson.fromJson(jsonArray.optJSONObject(i).toString(), CityJsonBean.class);
                    detail.add(entity);
                }
                return detail;
            }
        }).subscribeOn(Schedulers.single())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ArrayList<CityJsonBean>>() {
                    @Override
                    public void accept(ArrayList<CityJsonBean> citys) throws Exception {
                        for (CityJsonBean city : citys) {
                            L.i(city.toString());
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        L.i("yi chang");
                    }
                });
//        if (!disposable.isDisposed()) {
//            disposable.dispose();
//        }
    }


    /**
     * 线程中初始化数据
     */
    private void initDataInThread() {
        /*被观察者,发射事件，subscribeOn()指定的就是发射事件的线程*/
        Observable<ArrayList<CityJsonBean>> observable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> subscriber) throws Exception {
                StringBuilder sb = new StringBuilder();
                AssetManager assets = BaseApplication.app.getAssets();
                BufferedReader bf = new BufferedReader(new InputStreamReader(assets.open("province.json")));
                String line;
                while ((line = bf.readLine()) != null) {
                    sb.append(line);
                }
                subscriber.onNext(sb.toString());
                subscriber.onComplete();
            }
        }).map(new Function<String, JSONArray>() {
            @Override
            public JSONArray apply(String s) throws Exception {
                return new JSONArray(s);
            }
        }).map(new Function<JSONArray, ArrayList<CityJsonBean>>() {
            @Override
            public ArrayList<CityJsonBean> apply(JSONArray jsonArray) throws Exception {
                ArrayList<CityJsonBean> detail = new ArrayList<>();
                Gson gson = new Gson();
                for (int i = 0; i < jsonArray.length(); i++) {
                    CityJsonBean entity = gson.fromJson(jsonArray.optJSONObject(i).toString(), CityJsonBean.class);
                    detail.add(entity);
                }
                return detail;
            }
        });
        /*观察者，接收事件，observerOn 指定的就是订阅者接收事件的线程*/
        Observer<ArrayList<CityJsonBean>> observer = new Observer<ArrayList<CityJsonBean>>() {
            @Override
            public void onNext(ArrayList<CityJsonBean> citys) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }

            @Override
            public void onSubscribe(Disposable d) {

            }
        };

        observable.subscribeOn(Schedulers.io())//指定发送线程
                .observeOn(Schedulers.single())//指定接收任务的线程
                .subscribe(observer);

    }
}

