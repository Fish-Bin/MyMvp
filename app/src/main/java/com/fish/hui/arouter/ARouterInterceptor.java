package com.fish.hui.arouter;

import android.content.Context;
import com.alibaba.android.arouter.core.LogisticsCenter;
import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Interceptor;
import com.alibaba.android.arouter.facade.callback.InterceptorCallback;
import com.alibaba.android.arouter.facade.template.IInterceptor;
import com.alibaba.android.arouter.launcher.ARouter;
import com.fish.liubin.L;

/**
 * function : 跳转拦截器(权限拦截)
 * <p>
 * 比较经典的应用就是在跳转过程中处理登陆事件，这样就不需要在目标页重复做登陆检查
 * 拦截器会在跳转之间执行，多个拦截器会按优先级顺序依次执行
 *
 * @author ACChain
 */

@Interceptor(priority = 2, name = "ARouter跳转拦截器")
public class ARouterInterceptor implements IInterceptor {
    @Override
    public void process(Postcard postcard, InterceptorCallback callback) {
        L.i("######界面跳转 ： " + postcard.toString());
        //当前所有的权限
        String[] permissions = new String[]{
                "登录",
                "已开始了实名认证",
                "绑定手机",
                "绑定邮箱",
                "设置交易密码",
                "红色通道",//若目标此flag设定,则表示禁止跳转
                "wifi",
                "手机网络",
                "栈单例模式",//若目标设置此flag,则添加singTask标志
                "名片设置",
                "实名认证已成功",
        };
        int[] FLAGS_ALL = new int[]{
                ARouterConst.FLAG_LOGIN,
                ARouterConst.FLAG_VERIFY_HAS_BEGIN,
                ARouterConst.FLAG_PHONE,
                ARouterConst.FLAG_EMAIL,
        };

        //当前所有权限对应的boolean值;为false则对应权限设为 ARouterConst.FLAG_NONE
        boolean[] FLAGS_ALL_VALUE = new boolean[]{
                false,
                false,
                false,
                false,
        };

        //目标界面需要的权限
        int requireFlags = postcard.getExtra() | Integer.MIN_VALUE;
        L.i("######目标所需权限 : " + Integer.toBinaryString(requireFlags));
        //如果目标无需任何权限,跳过权限判断逻辑
        if (requireFlags == Integer.MIN_VALUE) {
            callback.onContinue(postcard);
            return;
        }
        //当前所有的权限
        int currentFlags = Integer.MIN_VALUE;
        for (int position = 0; position < FLAGS_ALL.length; position++) {
            currentFlags |= FLAGS_ALL_VALUE[position] ? FLAGS_ALL[position] : ARouterConst.FLAG_NONE;
        }
        L.i("######当前所有权限 : " + Integer.toBinaryString(currentFlags));
        //如果需要的权限都已存在,则直接跳转,不做处理
        if ((requireFlags & currentFlags) == requireFlags) {
            callback.onContinue(postcard);
            return;
        }
        //如果发现不一致,说明某些权限不存在,则需要依次判断哪个权限不存在
        for (int position = 0; position < FLAGS_ALL.length; position++) {
            if ((requireFlags & FLAGS_ALL[position]) != 0 && (currentFlags & FLAGS_ALL[position]) == 0) {
                boolean consume = false;
                switch (position) {
                    case 4: //未设置交易密码
                        consume = dispatchTransactionPassword(postcard, callback);
                        break;
                    default: {
                        callback.onInterrupt(new RuntimeException("没有 " + permissions[position] + " 权限"));
                    }
                }
                if(!consume){

                }
                return;
            }
        }
        L.i("未知权限");
    }

    /**
     * 未设置交易密码
     * 则跳转到设置交易密码界面
     * <p>
     * 默认提示toast
     */
    private boolean dispatchTransactionPassword(Postcard postcard, InterceptorCallback callback) {
        //如果是"选择修改交易密码方式"界面,则跳转到设置交易密码界面,其他的话,都弹出提示进行选择
        if(postcard.getPath().equalsIgnoreCase(ARouterConst.Activity_SelectChangeTransPasswordTypeActivity)){
            replaceDes(postcard, ARouterConst.Activity_SetTransactionPasswordActivity);
            process(postcard, callback);
            return true;
        }else{
//            RxBus.getInstance().post(new ARouterNoPermission(
//                    "未设置交易密码,是否前往设置?",
//                    "放弃", "前往",
//                    () -> ARouter.getInstance().build(ARouterConst.Activity_SetTransactionPasswordActivity).navigation()));
            return false;
        }
    }


    @Override
    public void init(Context context) {
        // 拦截器的初始化，会在sdk初始化的时候调用该方法，仅会调用一次
    }

    /**
     * 更换意图的跳转路径
     * 然后进行跳转处理
     *
     * @param postcard 意图
     * @param des      目的 string
     */
    private void replaceDes(Postcard postcard, String des) {
        //动态的修改postcard信息,更换跳转路径
        Postcard newPostcard = ARouter.getInstance().build(des);
        LogisticsCenter.completion(newPostcard);
        postcard.setExtra(newPostcard.getExtra()).setPath(newPostcard.getPath()).setGroup(newPostcard.getGroup()).setDestination(newPostcard.getDestination());
    }
}