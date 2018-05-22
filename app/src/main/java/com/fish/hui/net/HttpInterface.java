package com.fish.hui.net;

import com.fish.hui.bean.BaseHttpBean;
import com.fish.hui.bean.CityJsonBean;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * 功能----使用retrofit框架与http交换数据
 * <p>
 * Created by ACChain on 2017/9/25.
 * <p>
 * 注:短信/邮箱 验证码
 */

public interface HttpInterface {

    /**
     * 1.0.1 登录
     *
     * @param username 用户名
     * @param pwd      密码
     * @return 登录返回对象
     */
    @POST("login/doLogin")
    @FormUrlEncoded
    Observable<BaseHttpBean<Object>> doLogin(@Field("username") String username,
                                             @Field("pwd") String pwd);

    /**
     * 1.0.2 动态登录
     *
     * @param username 手机号
     * @param code     验证码
     * @return 登录返回对象
     */
    @POST("login/dynamicLogin")
    @FormUrlEncoded
    Observable<BaseHttpBean<Object>> dynamicLogin(@Field("username") String username,
                                                  @Field("code") String code);

    /**
     * 1.0.3 获取图形验证码
     * 直接访问url下载图片
     */
    @GET("randomCode/getImgCode")
    Observable<BaseHttpBean<CityJsonBean>> getImgCode();

    /**
     * 1.0.4 获取短信验证码
     *
     * @param mobile 手机号
     * @param tag    操作类型
     * @param code   图形验证码
     * @return 登录返回对象
     */
    @POST("randomCode/sendMessage")
    @FormUrlEncoded
    Observable<BaseHttpBean<Object>> sendMessage(@Field("mobile") String mobile,
                                                 @Field("tag") String tag,
                                                 @Field("code") String code);

    /**
     * 1.0.5 获取邮箱验证码
     *
     * @param email 邮箱
     * @param tag   操作类型
     * @param code  图形验证码
     * @return 登录返回对象
     */
    @POST("randomCode/sendEmail")
    @FormUrlEncoded
    Observable<BaseHttpBean<Object>> sendEmail(@Field("email") String email,
                                               @Field("tag") String tag,
                                               @Field("code") String code);

    /**
     * 1.0.6 校验验证码
     *
     * @param username 手机号/邮箱
     * @param tag      操作类型
     * @param code     短信验证码
     * @return 登录返回对象
     */
    @POST("randomCode/checkCode")
    @FormUrlEncoded
    Observable<BaseHttpBean<Object>> checkCode(@Field("username") String username,
                                               @Field("tag") String tag,
                                               @Field("code") String code);

    /**
     * 1.0.6 用户注册
     *
     * @param username       手机号/邮箱
     * @param pwd            密码
     * @param code           验证码
     * @param invitationCode 邀请码(用户微脉号)
     * @return 登录返回对象
     */
    @POST("register/newUser")
    @FormUrlEncoded
    Observable<BaseHttpBean<Object>> newUser(@Field("username") String username,
                                             @Field("pwd") String pwd,
                                             @Field("code") String code,
                                             @Field("invitationCode") String invitationCode);

    /**
     * 1.0.8 重置登录密码(手机/邮箱)
     *
     * @param username 手机号/邮箱
     * @param pwd      密码(sha256)
     * @param code     验证码
     * @param confPwd  密码再次确认
     * @return 登录返回对象
     */
    @POST("register/resetPwd")
    @FormUrlEncoded
    Observable<BaseHttpBean<Object>> resetPwd(@Field("username") String username,
                                              @Field("pwd") String pwd,
                                              @Field("code") String code,
                                              @Field("confPwd") String confPwd);




}
