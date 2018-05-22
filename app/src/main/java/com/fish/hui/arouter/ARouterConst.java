package com.fish.hui.arouter;

/**
 * 功能----路径跳转activity/fragment
 * <p>
 * Created by ACChain on 2017/11/24.
 */

public final class ARouterConst {
    /**
     * 无权限
     * 登录
     * 已开始了实名认证流程
     * 绑定手机
     * 绑定邮箱
     * 交易密码
     * 红色通道(默认没有,即正常请求都需要进行过滤,如果有目标需要该权限,则表示通往该目标的请求都将被拦截(除非有特殊处理))
     * WIFI网络
     * 流量网络(MOBILE)
     * activity启动:清除任务栈
     * 是否设置了名片;若没有,则无法展示名片与二维码信息
     * 实名认证已成功
     */
    public static final int FLAG_NONE = 0x00000000;
    public static final int FLAG_LOGIN = 0x00000003;
    public static final int FLAG_VERIFY_HAS_BEGIN = 0x00000004;
    public static final int FLAG_PHONE = 0x00000008;
    public static final int FLAG_EMAIL = 0x00000010;
    public static final int FLAG_TRANSACTION_PASSWORD = 0x00000020;
    public static final int FLAG_FORBID_ACCESS = 0x00000040;
    public static final int FLAG_WIFI_NET = 0x00000080;
    public static final int FLAG_MOBILE_NET = 0x00000100;
    public static final int FLAG_ACTIVITY_CLEAR_TOP = 0x00000200;
    public static final int FLAG_BUSINESS_CARD = 0x00000400;
    public static final int FLAG_VERIFY_HAS_SUCCESS = 0x00001000;

    /**
     * activity/fragment
     */
    public static final String Activity_MainActivity = "/activity/MainActivity";
    public static final String Fragment_FriendsFragment = "/fragment/FriendsFragment";
    public static final String Activity_SelectChangeTransPasswordTypeActivity = "/activity/SelectChangeTransPasswordTypeActivity";
    public static final String Activity_SetTransactionPasswordActivity = "/activity/SetTransactionPasswordActivity";

}
