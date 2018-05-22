package com.fish.hui.net;

/**
 * Created by Administrator on 2018/5/17.
 */

public abstract class HttpListener<E> {

   public abstract void onSuccess(E response);

   public void onFailed(String message){

   }

}
