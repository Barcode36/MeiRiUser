package com.example.meiriuser.net;

/**
 * Created by admin on 2019/5/16.
 */

public class ApiUrl {

  /*  public static String COMMAN_URL = "http://192.168.0.10:8000/api";*/

    public static String COMMAN_URL = "https://meiri.hongfs.cn/api";  //测试
 /*   //验证签名
    public static String registerUrl=COMMAN_URL+"/Token/register.html";*/

    //注册
    public static String registerUrl=COMMAN_URL+"/Token/register.html";

    //登陆
    public static String loginUrl=COMMAN_URL+"/auth/login";

    //获取验证码
    public static String getCodeUrl=COMMAN_URL+"/sms";

    //短信登陆
    public static String smsLoginUrl=COMMAN_URL+"/auth/tel_login";

    //获取用户信息
    public static String infoUrl=COMMAN_URL+"/users/info";

    //修改用户信息
    public static String editInfoUrl=COMMAN_URL+"/users/update";

    //修改密码
    public static String updatePwdUrl=COMMAN_URL+"/users/password";

    //获取通知列表 (未读状态)获取通知详情
    public static String noticeListUrl=COMMAN_URL+"/push";

    //获取热门搜索列表
    public static String hotListUrl=COMMAN_URL+"/other/hot";

    //获取广告列表
    public static String bannerListUrl=COMMAN_URL+"/banner";

    //提交留言
    public static String feedbackUrl=COMMAN_URL+"/feedback";

    //上传图片
    public static String uploadFileUrl=COMMAN_URL+"/uploads";

    //操作用户收货地址
    public static String addressToDoUrl=COMMAN_URL+"/users/address";

    //获取用户收货地址列表
    public static String addressListUrl=COMMAN_URL+"/users/address";

    //获取/修改用户收货地址
    public static String getDefaultAddressUrl=COMMAN_URL+"/users/address/default";

    //获取商店列表
    public static String storeListUrl=COMMAN_URL+"/store/list";

    //获取单个商店信息
    public static String storeUrl=COMMAN_URL+"/store/store";

    //查询商品列表
    public static String goodsListUrl=COMMAN_URL+"/Goods/goodsList.html";

    //获取订单列表
    public static String orderListUrl=COMMAN_URL+"/order/list";

    //提交订单
    public static String orderQueryUrl=COMMAN_URL+"/order/create";

    //订单详情
    public static String orderDetailsUrl=COMMAN_URL+"/order/order";

    //取消订单
    public static String orderCancelUrl=COMMAN_URL+"/order/cancel";

    //提交评论
    public static String orderCommentUrl=COMMAN_URL+"/order/comment";

    //获取商店分类
    public static String orderCatgoryUrl=COMMAN_URL+"/store/catgory";

    //退出登陆
    public static String logoutUrl=COMMAN_URL+"/auth/logout";

    //获取地区列表
    public static String areaUrl=COMMAN_URL+"/area";

    //支付
    public static String payNo=COMMAN_URL+"/order/pay_no";

    //跑腿下单
    public static String errandsCreateUrl=COMMAN_URL+"/errands/create";

    //跑腿列表
    public static String errandsList=COMMAN_URL+"/errands/list";

    //获取保证金缴纳状态
    public static String depositStatusUrl=COMMAN_URL+"/mutual_aid/deposit_has";

    //设置保证金缴纳支付单号（即为创建保证金缴纳）
    public static String depositNoUrl=COMMAN_URL+"/mutual_aid/deposit_no";

    //提交保证金退款申请
    public static String depositRefundUrl=COMMAN_URL+"/mutual_aid/deposit_refund";

    //任务--创建任务
    public static String taskCreateUrl=COMMAN_URL+"/task/create";

    //任务--任务列表
    public static String applytaskListUrl=COMMAN_URL+"/task/list";

    //任务--获取任务申请列表
    public static String applyListUrl=COMMAN_URL+"/task/apply_list";

    //任务--设置任务申请状态
    public static String applyStatusUrl=COMMAN_URL+"/task/apply_confirm";

    //任务--获取任务状态
    public static String gettaskStatusUrl=COMMAN_URL+"/task/status";

    //任务--发表任务评论
    public static String taskCommentUrl=COMMAN_URL+"/task/apply_comment";

    //任务--设置任务状态
    public static String taskStatusUrl=COMMAN_URL+"/task/task_status";

    //互助-获取任务列表
    public static String taskListUrl=COMMAN_URL+"/mutual_aid/task_list";

    //互助--获取任务详情
    public static String taskDetailUrl=COMMAN_URL+"/mutual_aid/task";

    //互助--任务申请
    public static String taskApplyUrl=COMMAN_URL+"/mutual_aid/task_apply";

    //互助--任务申请取消
    public static String taskCancelUrl=COMMAN_URL+"/mutual_aid/task_cancel";

    //获取用户账单地址/设置用户账单地址
    public static String billAddressUrl=COMMAN_URL+"/users/bill_address";

    //获取用户认证状态/设置用户认证信息
    public static String attestationUrl=COMMAN_URL+"/users/attestation";

    //获取用户账单
    public static String accountUrl=COMMAN_URL+"/users/account";

    //附近搜索列表
    public static String nearbysearchUrl=COMMAN_URL+"/other/nearbysearch";

    //获取订单价格
    public static String orderPriceUrl=COMMAN_URL+"/order/query";

    //获取推荐列表
    public static String recommendListUrl=COMMAN_URL+"/other/recommend";

    //重置密码
    public static String resetPasswordUrl=COMMAN_URL+"/auth/reset_password";

    //设置用户银行卡
    public static String resetBankUrl=COMMAN_URL+"/users/bank";

    //解绑用户银行卡
    public static String deleteBankUrl=COMMAN_URL+"/users/bank";

    //用户发起提现
    public static String cashoutBankUrl=COMMAN_URL+"/cash_out/create";

}
