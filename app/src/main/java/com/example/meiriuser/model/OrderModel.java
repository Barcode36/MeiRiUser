package com.example.meiriuser.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by admin on 2019/5/22.
 */

public class OrderModel extends BaseBean implements Serializable{

    /**
     * data : {"total":11,"per_page":10,"current_page":"1","last_page":2,"data":[{"order_id":167,"order_sn":"2019070117513932f182","user_id":37,"order_status":0,"shipping_status":0,"pay_status":2,"consignee":"GG","country":11,"province":11,"city":11,"district":11,"address":"zz","zipcode":"","tel":"","mobile":"18344560792","email":"","best_time":"","sign_building":"","postscript":"","shipping_id":1,"shipping_name":"骑手配送","pay_id":0,"pay_name":"","pay_fee":"0.00","pack_id":0,"pack_name":"","pack_fee":"0.00","goods_amount":"28.00","shipping_fee":"8.00","insure_fee":"0.00","integral":0,"integral_money":"0.00","order_amount":"28.00","add_time":"2019-07-01 17:51","confirm_time":"","pay_time":"","shipping_time":"","invoice_no":"","to_buyer":"","pay_note":"","is_separate":0,"parent_id":0,"discount":0,"note":"","rider_id":null,"store_id":1,"task_code":null,"type":1,"rider_status":0,"lat":"0.0000000","lng":"0.0000000","shipping_complete_time":"","shipping_estimate_time":null,"pay_info":{"pay_id":"0KB50523P36611309","paymentId":"PAYID-LUM5POA5KV58917F60209945"},"other_info":null,"commission_money":"1.00","errands_money":"1.00","is_comment":0,"store":{"name":"优衣库2","username":"","tel":"","store_id":1},"goods":[{"rec_id":183,"order_id":167,"goods_id":2,"goods_name":"商品2","goods_sn":"","goods_number":1,"market_price":"0.00","goods_price":"20.00","goods_attr":"","is_real":0,"parent_id":0,"note":""},{"rec_id":184,"order_id":167,"goods_id":4,"goods_name":"商品4","goods_sn":"","goods_number":2,"market_price":"0.00","goods_price":"4.00","goods_attr":"","is_real":0,"parent_id":0,"note":""}],"status_name":"待骑手接单","has_cancel":true},{"order_id":166,"order_sn":"201907011750037f2390","user_id":37,"order_status":0,"shipping_status":0,"pay_status":2,"consignee":"GG","country":11,"province":11,"city":11,"district":11,"address":"zz","zipcode":"","tel":"","mobile":"18344560792","email":"","best_time":"","sign_building":"","postscript":"","shipping_id":1,"shipping_name":"骑手配送","pay_id":0,"pay_name":"","pay_fee":"0.00","pack_id":0,"pack_name":"","pack_fee":"0.00","goods_amount":"24.00","shipping_fee":"0.00","insure_fee":"0.00","integral":0,"integral_money":"0.00","order_amount":"24.00","add_time":"2019-07-01 17:50","confirm_time":"","pay_time":"","shipping_time":"","invoice_no":"","to_buyer":"","pay_note":"","is_separate":0,"parent_id":0,"discount":0,"note":"","rider_id":null,"store_id":1,"task_code":null,"type":1,"rider_status":0,"lat":"0.0000000","lng":"0.0000000","shipping_complete_time":"","shipping_estimate_time":null,"pay_info":{"pay_id":"8W7124800Y172700A","paymentId":"PAYID-LUM5OWQ2WA97143EF282572W"},"other_info":null,"commission_money":"1.00","errands_money":"1.00","is_comment":0,"store":{"name":"优衣库2","username":"","tel":"","store_id":1},"goods":[{"rec_id":181,"order_id":166,"goods_id":2,"goods_name":"商品2","goods_sn":"","goods_number":1,"market_price":"0.00","goods_price":"20.00","goods_attr":"","is_real":0,"parent_id":0,"note":""},{"rec_id":182,"order_id":166,"goods_id":4,"goods_name":"商品4","goods_sn":"","goods_number":1,"market_price":"0.00","goods_price":"4.00","goods_attr":"","is_real":0,"parent_id":0,"note":""}],"status_name":"待骑手接单","has_cancel":true},{"order_id":165,"order_sn":"2019070117213250e72e","user_id":37,"order_status":0,"shipping_status":0,"pay_status":2,"consignee":"GG","country":11,"province":11,"city":11,"district":11,"address":"zz","zipcode":"","tel":"","mobile":"18344560792","email":"","best_time":"","sign_building":"","postscript":"","shipping_id":1,"shipping_name":"骑手配送","pay_id":0,"pay_name":"","pay_fee":"0.00","pack_id":0,"pack_name":"","pack_fee":"0.00","goods_amount":"24.00","shipping_fee":"0.00","insure_fee":"0.00","integral":0,"integral_money":"0.00","order_amount":"24.00","add_time":"2019-07-01 17:21","confirm_time":"","pay_time":"","shipping_time":"","invoice_no":"","to_buyer":"","pay_note":"","is_separate":0,"parent_id":0,"discount":0,"note":"","rider_id":null,"store_id":1,"task_code":null,"type":1,"rider_status":0,"lat":"0.0000000","lng":"0.0000000","shipping_complete_time":"","shipping_estimate_time":null,"pay_info":{"pay_id":"1VE54739L0695622Y","paymentId":"PAYID-LUM5BOQ9NG3423685616515Y"},"other_info":null,"commission_money":"1.00","errands_money":"1.00","is_comment":0,"store":{"name":"优衣库2","username":"","tel":"","store_id":1},"goods":[{"rec_id":179,"order_id":165,"goods_id":2,"goods_name":"商品2","goods_sn":"","goods_number":1,"market_price":"0.00","goods_price":"20.00","goods_attr":"","is_real":0,"parent_id":0,"note":""},{"rec_id":180,"order_id":165,"goods_id":4,"goods_name":"商品4","goods_sn":"","goods_number":1,"market_price":"0.00","goods_price":"4.00","goods_attr":"","is_real":0,"parent_id":0,"note":""}],"status_name":"待骑手接单","has_cancel":true},{"order_id":164,"order_sn":"20190701171832a088eb","user_id":37,"order_status":0,"shipping_status":0,"pay_status":2,"consignee":"GG","country":11,"province":11,"city":11,"district":11,"address":"zz","zipcode":"","tel":"","mobile":"18344560792","email":"","best_time":"","sign_building":"","postscript":"","shipping_id":1,"shipping_name":"骑手配送","pay_id":0,"pay_name":"","pay_fee":"0.00","pack_id":0,"pack_name":"","pack_fee":"0.00","goods_amount":"52.00","shipping_fee":"0.00","insure_fee":"0.00","integral":0,"integral_money":"0.00","order_amount":"52.00","add_time":"2019-07-01 17:18","confirm_time":"","pay_time":"","shipping_time":"","invoice_no":"","to_buyer":"","pay_note":"","is_separate":0,"parent_id":0,"discount":0,"note":"","rider_id":null,"store_id":17,"task_code":null,"type":1,"rider_status":0,"lat":"0.0000000","lng":"0.0000000","shipping_complete_time":"","shipping_estimate_time":null,"pay_info":{"pay_id":"3VG05964UE2590131","paymentId":"PAYID-LUM5ABI9X683730WN0062138"},"other_info":null,"commission_money":"1.00","errands_money":"1.00","is_comment":0,"store":{"name":"麦当劳(中唱大厦分店)","username":"","tel":"","store_id":17},"goods":[{"rec_id":176,"order_id":164,"goods_id":13,"goods_name":"地瓜","goods_sn":"","goods_number":1,"market_price":"0.00","goods_price":"12.00","goods_attr":"","is_real":0,"parent_id":0,"note":""},{"rec_id":177,"order_id":164,"goods_id":15,"goods_name":"菠萝","goods_sn":"","goods_number":1,"market_price":"0.00","goods_price":"12.00","goods_attr":"","is_real":0,"parent_id":0,"note":""},{"rec_id":178,"order_id":164,"goods_id":16,"goods_name":"宫保鸡丁","goods_sn":"","goods_number":1,"market_price":"0.00","goods_price":"28.00","goods_attr":"","is_real":0,"parent_id":0,"note":""}],"status_name":"待骑手接单","has_cancel":true},{"order_id":163,"order_sn":"20190701163656c131db","user_id":37,"order_status":0,"shipping_status":0,"pay_status":0,"consignee":"GG","country":11,"province":11,"city":11,"district":11,"address":"zz","zipcode":"","tel":"","mobile":"18344560792","email":"","best_time":"","sign_building":"","postscript":"","shipping_id":1,"shipping_name":"骑手配送","pay_id":0,"pay_name":"","pay_fee":"0.00","pack_id":0,"pack_name":"","pack_fee":"0.00","goods_amount":"2251.00","shipping_fee":"0.00","insure_fee":"0.00","integral":0,"integral_money":"0.00","order_amount":"2251.00","add_time":"2019-07-01 16:36","confirm_time":"","pay_time":"","shipping_time":"","invoice_no":"","to_buyer":"","pay_note":"","is_separate":0,"parent_id":0,"discount":0,"note":"","rider_id":null,"store_id":17,"task_code":null,"type":1,"rider_status":0,"lat":"0.0000000","lng":"0.0000000","shipping_complete_time":"","shipping_estimate_time":null,"pay_info":null,"other_info":null,"commission_money":"1.00","errands_money":"1.00","is_comment":0,"store":{"name":"麦当劳(中唱大厦分店)","username":"","tel":"","store_id":17},"goods":[{"rec_id":171,"order_id":163,"goods_id":10,"goods_name":"大香蕉","goods_sn":"","goods_number":2,"market_price":"0.00","goods_price":"15.00","goods_attr":"","is_real":0,"parent_id":0,"note":""},{"rec_id":172,"order_id":163,"goods_id":11,"goods_name":"大香蕉","goods_sn":"","goods_number":2,"market_price":"0.00","goods_price":"15.00","goods_attr":"","is_real":0,"parent_id":0,"note":""},{"rec_id":173,"order_id":163,"goods_id":12,"goods_name":"大鸡吧","goods_sn":"","goods_number":2,"market_price":"0.00","goods_price":"18.00","goods_attr":"","is_real":0,"parent_id":0,"note":""},{"rec_id":174,"order_id":163,"goods_id":13,"goods_name":"地瓜","goods_sn":"","goods_number":2,"market_price":"0.00","goods_price":"12.00","goods_attr":"","is_real":0,"parent_id":0,"note":""},{"rec_id":175,"order_id":163,"goods_id":14,"goods_name":"劲野","goods_sn":"","goods_number":1,"market_price":"0.00","goods_price":"2131.00","goods_attr":"","is_real":0,"parent_id":0,"note":""}],"status_name":"未付款","has_cancel":true},{"order_id":162,"order_sn":"2019070116352985f90c","user_id":37,"order_status":0,"shipping_status":0,"pay_status":0,"consignee":"GG","country":11,"province":11,"city":11,"district":11,"address":"zz","zipcode":"","tel":"","mobile":"18344560792","email":"","best_time":"","sign_building":"","postscript":"","shipping_id":1,"shipping_name":"骑手配送","pay_id":0,"pay_name":"","pay_fee":"0.00","pack_id":0,"pack_name":"","pack_fee":"0.00","goods_amount":"48.00","shipping_fee":"0.00","insure_fee":"0.00","integral":0,"integral_money":"0.00","order_amount":"48.00","add_time":"2019-07-01 16:35","confirm_time":"","pay_time":"","shipping_time":"","invoice_no":"","to_buyer":"","pay_note":"","is_separate":0,"parent_id":0,"discount":0,"note":"","rider_id":null,"store_id":1,"task_code":null,"type":1,"rider_status":0,"lat":"0.0000000","lng":"0.0000000","shipping_complete_time":"","shipping_estimate_time":null,"pay_info":null,"other_info":null,"commission_money":"1.00","errands_money":"1.00","is_comment":0,"store":{"name":"优衣库2","username":"","tel":"","store_id":1},"goods":[{"rec_id":169,"order_id":162,"goods_id":2,"goods_name":"商品2","goods_sn":"","goods_number":2,"market_price":"0.00","goods_price":"20.00","goods_attr":"","is_real":0,"parent_id":0,"note":""},{"rec_id":170,"order_id":162,"goods_id":4,"goods_name":"商品4","goods_sn":"","goods_number":2,"market_price":"0.00","goods_price":"4.00","goods_attr":"","is_real":0,"parent_id":0,"note":""}],"status_name":"未付款","has_cancel":true},{"order_id":159,"order_sn":"201907011448271839e2","user_id":37,"order_status":0,"shipping_status":0,"pay_status":0,"consignee":"GG","country":11,"province":11,"city":11,"district":11,"address":"zz","zipcode":"","tel":"","mobile":"18344560792","email":"","best_time":"","sign_building":"","postscript":"","shipping_id":1,"shipping_name":"骑手配送","pay_id":0,"pay_name":"","pay_fee":"0.00","pack_id":0,"pack_name":"","pack_fee":"0.00","goods_amount":"20.00","shipping_fee":"0.00","insure_fee":"0.00","integral":0,"integral_money":"0.00","order_amount":"20.00","add_time":"2019-07-01 14:48","confirm_time":"","pay_time":"","shipping_time":"","invoice_no":"","to_buyer":"","pay_note":"","is_separate":0,"parent_id":0,"discount":0,"note":"","rider_id":null,"store_id":1,"task_code":null,"type":1,"rider_status":0,"lat":"0.0000000","lng":"0.0000000","shipping_complete_time":"","shipping_estimate_time":null,"pay_info":null,"other_info":null,"commission_money":"1.00","errands_money":"1.00","is_comment":0,"store":{"name":"优衣库2","username":"","tel":"","store_id":1},"goods":[{"rec_id":157,"order_id":159,"goods_id":2,"goods_name":"商品2","goods_sn":"","goods_number":1,"market_price":"0.00","goods_price":"20.00","goods_attr":"","is_real":0,"parent_id":0,"note":""}],"status_name":"未付款","has_cancel":true},{"order_id":153,"order_sn":"201907011116450ae92c","user_id":37,"order_status":0,"shipping_status":0,"pay_status":0,"consignee":"GG","country":11,"province":11,"city":11,"district":11,"address":"zz","zipcode":"","tel":"","mobile":"18344560792","email":"","best_time":"","sign_building":"","postscript":"","shipping_id":1,"shipping_name":"骑手配送","pay_id":0,"pay_name":"","pay_fee":"0.00","pack_id":0,"pack_name":"","pack_fee":"0.00","goods_amount":"20.00","shipping_fee":"0.00","insure_fee":"0.00","integral":0,"integral_money":"0.00","order_amount":"20.00","add_time":"2019-07-01 11:16","confirm_time":"","pay_time":"","shipping_time":"","invoice_no":"","to_buyer":"","pay_note":"","is_separate":0,"parent_id":0,"discount":0,"note":"","rider_id":null,"store_id":1,"task_code":null,"type":1,"rider_status":0,"lat":"0.0000000","lng":"0.0000000","shipping_complete_time":"","shipping_estimate_time":null,"pay_info":null,"other_info":null,"commission_money":"1.00","errands_money":"1.00","is_comment":0,"store":{"name":"优衣库2","username":"","tel":"","store_id":1},"goods":[{"rec_id":147,"order_id":153,"goods_id":2,"goods_name":"商品2","goods_sn":"","goods_number":1,"market_price":"0.00","goods_price":"20.00","goods_attr":"","is_real":0,"parent_id":0,"note":""}],"status_name":"未付款","has_cancel":true},{"order_id":152,"order_sn":"20190701110702a43907","user_id":37,"order_status":0,"shipping_status":0,"pay_status":0,"consignee":"GG","country":11,"province":11,"city":11,"district":11,"address":"zz","zipcode":"","tel":"","mobile":"18344560792","email":"","best_time":"","sign_building":"","postscript":"","shipping_id":1,"shipping_name":"骑手配送","pay_id":0,"pay_name":"","pay_fee":"0.00","pack_id":0,"pack_name":"","pack_fee":"0.00","goods_amount":"20.00","shipping_fee":"0.00","insure_fee":"0.00","integral":0,"integral_money":"0.00","order_amount":"20.00","add_time":"2019-07-01 11:07","confirm_time":"","pay_time":"","shipping_time":"","invoice_no":"","to_buyer":"","pay_note":"","is_separate":0,"parent_id":0,"discount":0,"note":"","rider_id":null,"store_id":1,"task_code":null,"type":1,"rider_status":0,"lat":"0.0000000","lng":"0.0000000","shipping_complete_time":"","shipping_estimate_time":null,"pay_info":null,"other_info":null,"commission_money":"1.00","errands_money":"1.00","is_comment":0,"store":{"name":"优衣库2","username":"","tel":"","store_id":1},"goods":[{"rec_id":146,"order_id":152,"goods_id":2,"goods_name":"商品2","goods_sn":"","goods_number":1,"market_price":"0.00","goods_price":"20.00","goods_attr":"","is_real":0,"parent_id":0,"note":""}],"status_name":"未付款","has_cancel":true},{"order_id":151,"order_sn":"20190701110549431ea9","user_id":37,"order_status":0,"shipping_status":0,"pay_status":0,"consignee":"GG","country":11,"province":11,"city":11,"district":11,"address":"zz","zipcode":"","tel":"","mobile":"18344560792","email":"","best_time":"","sign_building":"","postscript":"","shipping_id":1,"shipping_name":"骑手配送","pay_id":0,"pay_name":"","pay_fee":"0.00","pack_id":0,"pack_name":"","pack_fee":"0.00","goods_amount":"20.00","shipping_fee":"0.00","insure_fee":"0.00","integral":0,"integral_money":"0.00","order_amount":"20.00","add_time":"2019-07-01 11:05","confirm_time":"","pay_time":"","shipping_time":"","invoice_no":"","to_buyer":"","pay_note":"","is_separate":0,"parent_id":0,"discount":0,"note":"","rider_id":null,"store_id":1,"task_code":null,"type":1,"rider_status":0,"lat":"0.0000000","lng":"0.0000000","shipping_complete_time":"","shipping_estimate_time":null,"pay_info":null,"other_info":null,"commission_money":"1.00","errands_money":"1.00","is_comment":0,"store":{"name":"优衣库2","username":"","tel":"","store_id":1},"goods":[{"rec_id":145,"order_id":151,"goods_id":2,"goods_name":"商品2","goods_sn":"","goods_number":1,"market_price":"0.00","goods_price":"20.00","goods_attr":"","is_real":0,"parent_id":0,"note":""}],"status_name":"未付款","has_cancel":true}]}
     */

    private DataBeanX data;

    public DataBeanX getData() {
        return data;
    }

    public void setData(DataBeanX data) {
        this.data = data;
    }

    public static class DataBeanX implements Serializable{
        /**
         * total : 11
         * per_page : 10
         * current_page : 1
         * last_page : 2
         * data : [{"order_id":167,"order_sn":"2019070117513932f182","user_id":37,"order_status":0,"shipping_status":0,"pay_status":2,"consignee":"GG","country":11,"province":11,"city":11,"district":11,"address":"zz","zipcode":"","tel":"","mobile":"18344560792","email":"","best_time":"","sign_building":"","postscript":"","shipping_id":1,"shipping_name":"骑手配送","pay_id":0,"pay_name":"","pay_fee":"0.00","pack_id":0,"pack_name":"","pack_fee":"0.00","goods_amount":"28.00","shipping_fee":"8.00","insure_fee":"0.00","integral":0,"integral_money":"0.00","order_amount":"28.00","add_time":"2019-07-01 17:51","confirm_time":"","pay_time":"","shipping_time":"","invoice_no":"","to_buyer":"","pay_note":"","is_separate":0,"parent_id":0,"discount":0,"note":"","rider_id":null,"store_id":1,"task_code":null,"type":1,"rider_status":0,"lat":"0.0000000","lng":"0.0000000","shipping_complete_time":"","shipping_estimate_time":null,"pay_info":{"pay_id":"0KB50523P36611309","paymentId":"PAYID-LUM5POA5KV58917F60209945"},"other_info":null,"commission_money":"1.00","errands_money":"1.00","is_comment":0,"store":{"name":"优衣库2","username":"","tel":"","store_id":1},"goods":[{"rec_id":183,"order_id":167,"goods_id":2,"goods_name":"商品2","goods_sn":"","goods_number":1,"market_price":"0.00","goods_price":"20.00","goods_attr":"","is_real":0,"parent_id":0,"note":""},{"rec_id":184,"order_id":167,"goods_id":4,"goods_name":"商品4","goods_sn":"","goods_number":2,"market_price":"0.00","goods_price":"4.00","goods_attr":"","is_real":0,"parent_id":0,"note":""}],"status_name":"待骑手接单","has_cancel":true},{"order_id":166,"order_sn":"201907011750037f2390","user_id":37,"order_status":0,"shipping_status":0,"pay_status":2,"consignee":"GG","country":11,"province":11,"city":11,"district":11,"address":"zz","zipcode":"","tel":"","mobile":"18344560792","email":"","best_time":"","sign_building":"","postscript":"","shipping_id":1,"shipping_name":"骑手配送","pay_id":0,"pay_name":"","pay_fee":"0.00","pack_id":0,"pack_name":"","pack_fee":"0.00","goods_amount":"24.00","shipping_fee":"0.00","insure_fee":"0.00","integral":0,"integral_money":"0.00","order_amount":"24.00","add_time":"2019-07-01 17:50","confirm_time":"","pay_time":"","shipping_time":"","invoice_no":"","to_buyer":"","pay_note":"","is_separate":0,"parent_id":0,"discount":0,"note":"","rider_id":null,"store_id":1,"task_code":null,"type":1,"rider_status":0,"lat":"0.0000000","lng":"0.0000000","shipping_complete_time":"","shipping_estimate_time":null,"pay_info":{"pay_id":"8W7124800Y172700A","paymentId":"PAYID-LUM5OWQ2WA97143EF282572W"},"other_info":null,"commission_money":"1.00","errands_money":"1.00","is_comment":0,"store":{"name":"优衣库2","username":"","tel":"","store_id":1},"goods":[{"rec_id":181,"order_id":166,"goods_id":2,"goods_name":"商品2","goods_sn":"","goods_number":1,"market_price":"0.00","goods_price":"20.00","goods_attr":"","is_real":0,"parent_id":0,"note":""},{"rec_id":182,"order_id":166,"goods_id":4,"goods_name":"商品4","goods_sn":"","goods_number":1,"market_price":"0.00","goods_price":"4.00","goods_attr":"","is_real":0,"parent_id":0,"note":""}],"status_name":"待骑手接单","has_cancel":true},{"order_id":165,"order_sn":"2019070117213250e72e","user_id":37,"order_status":0,"shipping_status":0,"pay_status":2,"consignee":"GG","country":11,"province":11,"city":11,"district":11,"address":"zz","zipcode":"","tel":"","mobile":"18344560792","email":"","best_time":"","sign_building":"","postscript":"","shipping_id":1,"shipping_name":"骑手配送","pay_id":0,"pay_name":"","pay_fee":"0.00","pack_id":0,"pack_name":"","pack_fee":"0.00","goods_amount":"24.00","shipping_fee":"0.00","insure_fee":"0.00","integral":0,"integral_money":"0.00","order_amount":"24.00","add_time":"2019-07-01 17:21","confirm_time":"","pay_time":"","shipping_time":"","invoice_no":"","to_buyer":"","pay_note":"","is_separate":0,"parent_id":0,"discount":0,"note":"","rider_id":null,"store_id":1,"task_code":null,"type":1,"rider_status":0,"lat":"0.0000000","lng":"0.0000000","shipping_complete_time":"","shipping_estimate_time":null,"pay_info":{"pay_id":"1VE54739L0695622Y","paymentId":"PAYID-LUM5BOQ9NG3423685616515Y"},"other_info":null,"commission_money":"1.00","errands_money":"1.00","is_comment":0,"store":{"name":"优衣库2","username":"","tel":"","store_id":1},"goods":[{"rec_id":179,"order_id":165,"goods_id":2,"goods_name":"商品2","goods_sn":"","goods_number":1,"market_price":"0.00","goods_price":"20.00","goods_attr":"","is_real":0,"parent_id":0,"note":""},{"rec_id":180,"order_id":165,"goods_id":4,"goods_name":"商品4","goods_sn":"","goods_number":1,"market_price":"0.00","goods_price":"4.00","goods_attr":"","is_real":0,"parent_id":0,"note":""}],"status_name":"待骑手接单","has_cancel":true},{"order_id":164,"order_sn":"20190701171832a088eb","user_id":37,"order_status":0,"shipping_status":0,"pay_status":2,"consignee":"GG","country":11,"province":11,"city":11,"district":11,"address":"zz","zipcode":"","tel":"","mobile":"18344560792","email":"","best_time":"","sign_building":"","postscript":"","shipping_id":1,"shipping_name":"骑手配送","pay_id":0,"pay_name":"","pay_fee":"0.00","pack_id":0,"pack_name":"","pack_fee":"0.00","goods_amount":"52.00","shipping_fee":"0.00","insure_fee":"0.00","integral":0,"integral_money":"0.00","order_amount":"52.00","add_time":"2019-07-01 17:18","confirm_time":"","pay_time":"","shipping_time":"","invoice_no":"","to_buyer":"","pay_note":"","is_separate":0,"parent_id":0,"discount":0,"note":"","rider_id":null,"store_id":17,"task_code":null,"type":1,"rider_status":0,"lat":"0.0000000","lng":"0.0000000","shipping_complete_time":"","shipping_estimate_time":null,"pay_info":{"pay_id":"3VG05964UE2590131","paymentId":"PAYID-LUM5ABI9X683730WN0062138"},"other_info":null,"commission_money":"1.00","errands_money":"1.00","is_comment":0,"store":{"name":"麦当劳(中唱大厦分店)","username":"","tel":"","store_id":17},"goods":[{"rec_id":176,"order_id":164,"goods_id":13,"goods_name":"地瓜","goods_sn":"","goods_number":1,"market_price":"0.00","goods_price":"12.00","goods_attr":"","is_real":0,"parent_id":0,"note":""},{"rec_id":177,"order_id":164,"goods_id":15,"goods_name":"菠萝","goods_sn":"","goods_number":1,"market_price":"0.00","goods_price":"12.00","goods_attr":"","is_real":0,"parent_id":0,"note":""},{"rec_id":178,"order_id":164,"goods_id":16,"goods_name":"宫保鸡丁","goods_sn":"","goods_number":1,"market_price":"0.00","goods_price":"28.00","goods_attr":"","is_real":0,"parent_id":0,"note":""}],"status_name":"待骑手接单","has_cancel":true},{"order_id":163,"order_sn":"20190701163656c131db","user_id":37,"order_status":0,"shipping_status":0,"pay_status":0,"consignee":"GG","country":11,"province":11,"city":11,"district":11,"address":"zz","zipcode":"","tel":"","mobile":"18344560792","email":"","best_time":"","sign_building":"","postscript":"","shipping_id":1,"shipping_name":"骑手配送","pay_id":0,"pay_name":"","pay_fee":"0.00","pack_id":0,"pack_name":"","pack_fee":"0.00","goods_amount":"2251.00","shipping_fee":"0.00","insure_fee":"0.00","integral":0,"integral_money":"0.00","order_amount":"2251.00","add_time":"2019-07-01 16:36","confirm_time":"","pay_time":"","shipping_time":"","invoice_no":"","to_buyer":"","pay_note":"","is_separate":0,"parent_id":0,"discount":0,"note":"","rider_id":null,"store_id":17,"task_code":null,"type":1,"rider_status":0,"lat":"0.0000000","lng":"0.0000000","shipping_complete_time":"","shipping_estimate_time":null,"pay_info":null,"other_info":null,"commission_money":"1.00","errands_money":"1.00","is_comment":0,"store":{"name":"麦当劳(中唱大厦分店)","username":"","tel":"","store_id":17},"goods":[{"rec_id":171,"order_id":163,"goods_id":10,"goods_name":"大香蕉","goods_sn":"","goods_number":2,"market_price":"0.00","goods_price":"15.00","goods_attr":"","is_real":0,"parent_id":0,"note":""},{"rec_id":172,"order_id":163,"goods_id":11,"goods_name":"大香蕉","goods_sn":"","goods_number":2,"market_price":"0.00","goods_price":"15.00","goods_attr":"","is_real":0,"parent_id":0,"note":""},{"rec_id":173,"order_id":163,"goods_id":12,"goods_name":"大鸡吧","goods_sn":"","goods_number":2,"market_price":"0.00","goods_price":"18.00","goods_attr":"","is_real":0,"parent_id":0,"note":""},{"rec_id":174,"order_id":163,"goods_id":13,"goods_name":"地瓜","goods_sn":"","goods_number":2,"market_price":"0.00","goods_price":"12.00","goods_attr":"","is_real":0,"parent_id":0,"note":""},{"rec_id":175,"order_id":163,"goods_id":14,"goods_name":"劲野","goods_sn":"","goods_number":1,"market_price":"0.00","goods_price":"2131.00","goods_attr":"","is_real":0,"parent_id":0,"note":""}],"status_name":"未付款","has_cancel":true},{"order_id":162,"order_sn":"2019070116352985f90c","user_id":37,"order_status":0,"shipping_status":0,"pay_status":0,"consignee":"GG","country":11,"province":11,"city":11,"district":11,"address":"zz","zipcode":"","tel":"","mobile":"18344560792","email":"","best_time":"","sign_building":"","postscript":"","shipping_id":1,"shipping_name":"骑手配送","pay_id":0,"pay_name":"","pay_fee":"0.00","pack_id":0,"pack_name":"","pack_fee":"0.00","goods_amount":"48.00","shipping_fee":"0.00","insure_fee":"0.00","integral":0,"integral_money":"0.00","order_amount":"48.00","add_time":"2019-07-01 16:35","confirm_time":"","pay_time":"","shipping_time":"","invoice_no":"","to_buyer":"","pay_note":"","is_separate":0,"parent_id":0,"discount":0,"note":"","rider_id":null,"store_id":1,"task_code":null,"type":1,"rider_status":0,"lat":"0.0000000","lng":"0.0000000","shipping_complete_time":"","shipping_estimate_time":null,"pay_info":null,"other_info":null,"commission_money":"1.00","errands_money":"1.00","is_comment":0,"store":{"name":"优衣库2","username":"","tel":"","store_id":1},"goods":[{"rec_id":169,"order_id":162,"goods_id":2,"goods_name":"商品2","goods_sn":"","goods_number":2,"market_price":"0.00","goods_price":"20.00","goods_attr":"","is_real":0,"parent_id":0,"note":""},{"rec_id":170,"order_id":162,"goods_id":4,"goods_name":"商品4","goods_sn":"","goods_number":2,"market_price":"0.00","goods_price":"4.00","goods_attr":"","is_real":0,"parent_id":0,"note":""}],"status_name":"未付款","has_cancel":true},{"order_id":159,"order_sn":"201907011448271839e2","user_id":37,"order_status":0,"shipping_status":0,"pay_status":0,"consignee":"GG","country":11,"province":11,"city":11,"district":11,"address":"zz","zipcode":"","tel":"","mobile":"18344560792","email":"","best_time":"","sign_building":"","postscript":"","shipping_id":1,"shipping_name":"骑手配送","pay_id":0,"pay_name":"","pay_fee":"0.00","pack_id":0,"pack_name":"","pack_fee":"0.00","goods_amount":"20.00","shipping_fee":"0.00","insure_fee":"0.00","integral":0,"integral_money":"0.00","order_amount":"20.00","add_time":"2019-07-01 14:48","confirm_time":"","pay_time":"","shipping_time":"","invoice_no":"","to_buyer":"","pay_note":"","is_separate":0,"parent_id":0,"discount":0,"note":"","rider_id":null,"store_id":1,"task_code":null,"type":1,"rider_status":0,"lat":"0.0000000","lng":"0.0000000","shipping_complete_time":"","shipping_estimate_time":null,"pay_info":null,"other_info":null,"commission_money":"1.00","errands_money":"1.00","is_comment":0,"store":{"name":"优衣库2","username":"","tel":"","store_id":1},"goods":[{"rec_id":157,"order_id":159,"goods_id":2,"goods_name":"商品2","goods_sn":"","goods_number":1,"market_price":"0.00","goods_price":"20.00","goods_attr":"","is_real":0,"parent_id":0,"note":""}],"status_name":"未付款","has_cancel":true},{"order_id":153,"order_sn":"201907011116450ae92c","user_id":37,"order_status":0,"shipping_status":0,"pay_status":0,"consignee":"GG","country":11,"province":11,"city":11,"district":11,"address":"zz","zipcode":"","tel":"","mobile":"18344560792","email":"","best_time":"","sign_building":"","postscript":"","shipping_id":1,"shipping_name":"骑手配送","pay_id":0,"pay_name":"","pay_fee":"0.00","pack_id":0,"pack_name":"","pack_fee":"0.00","goods_amount":"20.00","shipping_fee":"0.00","insure_fee":"0.00","integral":0,"integral_money":"0.00","order_amount":"20.00","add_time":"2019-07-01 11:16","confirm_time":"","pay_time":"","shipping_time":"","invoice_no":"","to_buyer":"","pay_note":"","is_separate":0,"parent_id":0,"discount":0,"note":"","rider_id":null,"store_id":1,"task_code":null,"type":1,"rider_status":0,"lat":"0.0000000","lng":"0.0000000","shipping_complete_time":"","shipping_estimate_time":null,"pay_info":null,"other_info":null,"commission_money":"1.00","errands_money":"1.00","is_comment":0,"store":{"name":"优衣库2","username":"","tel":"","store_id":1},"goods":[{"rec_id":147,"order_id":153,"goods_id":2,"goods_name":"商品2","goods_sn":"","goods_number":1,"market_price":"0.00","goods_price":"20.00","goods_attr":"","is_real":0,"parent_id":0,"note":""}],"status_name":"未付款","has_cancel":true},{"order_id":152,"order_sn":"20190701110702a43907","user_id":37,"order_status":0,"shipping_status":0,"pay_status":0,"consignee":"GG","country":11,"province":11,"city":11,"district":11,"address":"zz","zipcode":"","tel":"","mobile":"18344560792","email":"","best_time":"","sign_building":"","postscript":"","shipping_id":1,"shipping_name":"骑手配送","pay_id":0,"pay_name":"","pay_fee":"0.00","pack_id":0,"pack_name":"","pack_fee":"0.00","goods_amount":"20.00","shipping_fee":"0.00","insure_fee":"0.00","integral":0,"integral_money":"0.00","order_amount":"20.00","add_time":"2019-07-01 11:07","confirm_time":"","pay_time":"","shipping_time":"","invoice_no":"","to_buyer":"","pay_note":"","is_separate":0,"parent_id":0,"discount":0,"note":"","rider_id":null,"store_id":1,"task_code":null,"type":1,"rider_status":0,"lat":"0.0000000","lng":"0.0000000","shipping_complete_time":"","shipping_estimate_time":null,"pay_info":null,"other_info":null,"commission_money":"1.00","errands_money":"1.00","is_comment":0,"store":{"name":"优衣库2","username":"","tel":"","store_id":1},"goods":[{"rec_id":146,"order_id":152,"goods_id":2,"goods_name":"商品2","goods_sn":"","goods_number":1,"market_price":"0.00","goods_price":"20.00","goods_attr":"","is_real":0,"parent_id":0,"note":""}],"status_name":"未付款","has_cancel":true},{"order_id":151,"order_sn":"20190701110549431ea9","user_id":37,"order_status":0,"shipping_status":0,"pay_status":0,"consignee":"GG","country":11,"province":11,"city":11,"district":11,"address":"zz","zipcode":"","tel":"","mobile":"18344560792","email":"","best_time":"","sign_building":"","postscript":"","shipping_id":1,"shipping_name":"骑手配送","pay_id":0,"pay_name":"","pay_fee":"0.00","pack_id":0,"pack_name":"","pack_fee":"0.00","goods_amount":"20.00","shipping_fee":"0.00","insure_fee":"0.00","integral":0,"integral_money":"0.00","order_amount":"20.00","add_time":"2019-07-01 11:05","confirm_time":"","pay_time":"","shipping_time":"","invoice_no":"","to_buyer":"","pay_note":"","is_separate":0,"parent_id":0,"discount":0,"note":"","rider_id":null,"store_id":1,"task_code":null,"type":1,"rider_status":0,"lat":"0.0000000","lng":"0.0000000","shipping_complete_time":"","shipping_estimate_time":null,"pay_info":null,"other_info":null,"commission_money":"1.00","errands_money":"1.00","is_comment":0,"store":{"name":"优衣库2","username":"","tel":"","store_id":1},"goods":[{"rec_id":145,"order_id":151,"goods_id":2,"goods_name":"商品2","goods_sn":"","goods_number":1,"market_price":"0.00","goods_price":"20.00","goods_attr":"","is_real":0,"parent_id":0,"note":""}],"status_name":"未付款","has_cancel":true}]
         */

        private int total;
        private int per_page;
        private String current_page;
        private int last_page;
        private List<DataBean> data;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getPer_page() {
            return per_page;
        }

        public void setPer_page(int per_page) {
            this.per_page = per_page;
        }

        public String getCurrent_page() {
            return current_page;
        }

        public void setCurrent_page(String current_page) {
            this.current_page = current_page;
        }

        public int getLast_page() {
            return last_page;
        }

        public void setLast_page(int last_page) {
            this.last_page = last_page;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean implements Serializable{
            /**
             * order_id : 167
             * order_sn : 2019070117513932f182
             * user_id : 37
             * order_status : 0
             * shipping_status : 0
             * pay_status : 2
             * consignee : GG
             * country : 11
             * province : 11
             * city : 11
             * district : 11
             * address : zz
             * zipcode :
             * tel :
             * mobile : 18344560792
             * email :
             * best_time :
             * sign_building :
             * postscript :
             * shipping_id : 1
             * shipping_name : 骑手配送
             * pay_id : 0
             * pay_name :
             * pay_fee : 0.00
             * pack_id : 0
             * pack_name :
             * pack_fee : 0.00
             * goods_amount : 28.00
             * shipping_fee : 8.00
             * insure_fee : 0.00
             * integral : 0
             * integral_money : 0.00
             * order_amount : 28.00
             * add_time : 2019-07-01 17:51
             * confirm_time :
             * pay_time :
             * shipping_time :
             * invoice_no :
             * to_buyer :
             * pay_note :
             * is_separate : 0
             * parent_id : 0
             * discount : 0
             * note :
             * rider_id : null
             * store_id : 1
             * task_code : null
             * type : 1
             * rider_status : 0
             * lat : 0.0000000
             * lng : 0.0000000
             * shipping_complete_time :
             * shipping_estimate_time : null
             * pay_info : {"pay_id":"0KB50523P36611309","paymentId":"PAYID-LUM5POA5KV58917F60209945"}
             * other_info : null
             * commission_money : 1.00
             * errands_money : 1.00
             * is_comment : 0
             * store : {"name":"优衣库2","username":"","tel":"","store_id":1}
             * goods : [{"rec_id":183,"order_id":167,"goods_id":2,"goods_name":"商品2","goods_sn":"","goods_number":1,"market_price":"0.00","goods_price":"20.00","goods_attr":"","is_real":0,"parent_id":0,"note":""},{"rec_id":184,"order_id":167,"goods_id":4,"goods_name":"商品4","goods_sn":"","goods_number":2,"market_price":"0.00","goods_price":"4.00","goods_attr":"","is_real":0,"parent_id":0,"note":""}]
             * status_name : 待骑手接单
             * has_cancel : true
             */

            private int order_id;
            private String order_sn;
            private int user_id;
            private int order_status;
            private int shipping_status;
            private int pay_status;
            private String consignee;
            private int country;
            private int province;
            private int city;
            private int district;
            private String address;
            private String zipcode;
            private String tel;
            private String mobile;
            private String email;
            private String best_time;
            private String sign_building;
            private String postscript;
            private int shipping_id;
            private String shipping_name;
            private int pay_id;
            private String pay_name;
            private String pay_fee;
            private int pack_id;
            private String pack_name;
            private String pack_fee;
            private String goods_amount;
            private String shipping_fee;
            private String insure_fee;
            private int integral;
            private String integral_money;
            private String order_amount;
            private String add_time;
            private String confirm_time;
            private String pay_time;
            private String shipping_time;
            private String invoice_no;
            private String to_buyer;
            private String pay_note;
            private int is_separate;
            private int parent_id;
            private int discount;
            private String note;
            private Object rider_id;
            private int store_id;
            private String task_code;
            private int type;
            private int rider_status;
            private String lat;
            private String lng;
            private String shipping_complete_time;
            private Object shipping_estimate_time;
            private PayInfoBean pay_info;
            private Object other_info;
            private String commission_money;
            private String errands_money;
            private int is_comment;
            private StoreBean store;
            private String status_name;
            private boolean has_cancel;
            private List<GoodsBean> goods;

            public int getOrder_id() {
                return order_id;
            }

            public void setOrder_id(int order_id) {
                this.order_id = order_id;
            }

            public String getOrder_sn() {
                return order_sn;
            }

            public void setOrder_sn(String order_sn) {
                this.order_sn = order_sn;
            }

            public int getUser_id() {
                return user_id;
            }

            public void setUser_id(int user_id) {
                this.user_id = user_id;
            }

            public int getOrder_status() {
                return order_status;
            }

            public void setOrder_status(int order_status) {
                this.order_status = order_status;
            }

            public int getShipping_status() {
                return shipping_status;
            }

            public void setShipping_status(int shipping_status) {
                this.shipping_status = shipping_status;
            }

            public int getPay_status() {
                return pay_status;
            }

            public void setPay_status(int pay_status) {
                this.pay_status = pay_status;
            }

            public String getConsignee() {
                return consignee;
            }

            public void setConsignee(String consignee) {
                this.consignee = consignee;
            }

            public int getCountry() {
                return country;
            }

            public void setCountry(int country) {
                this.country = country;
            }

            public int getProvince() {
                return province;
            }

            public void setProvince(int province) {
                this.province = province;
            }

            public int getCity() {
                return city;
            }

            public void setCity(int city) {
                this.city = city;
            }

            public int getDistrict() {
                return district;
            }

            public void setDistrict(int district) {
                this.district = district;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getZipcode() {
                return zipcode;
            }

            public void setZipcode(String zipcode) {
                this.zipcode = zipcode;
            }

            public String getTel() {
                return tel;
            }

            public void setTel(String tel) {
                this.tel = tel;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public String getBest_time() {
                return best_time;
            }

            public void setBest_time(String best_time) {
                this.best_time = best_time;
            }

            public String getSign_building() {
                return sign_building;
            }

            public void setSign_building(String sign_building) {
                this.sign_building = sign_building;
            }

            public String getPostscript() {
                return postscript;
            }

            public void setPostscript(String postscript) {
                this.postscript = postscript;
            }

            public int getShipping_id() {
                return shipping_id;
            }

            public void setShipping_id(int shipping_id) {
                this.shipping_id = shipping_id;
            }

            public String getShipping_name() {
                return shipping_name;
            }

            public void setShipping_name(String shipping_name) {
                this.shipping_name = shipping_name;
            }

            public int getPay_id() {
                return pay_id;
            }

            public void setPay_id(int pay_id) {
                this.pay_id = pay_id;
            }

            public String getPay_name() {
                return pay_name;
            }

            public void setPay_name(String pay_name) {
                this.pay_name = pay_name;
            }

            public String getPay_fee() {
                return pay_fee;
            }

            public void setPay_fee(String pay_fee) {
                this.pay_fee = pay_fee;
            }

            public int getPack_id() {
                return pack_id;
            }

            public void setPack_id(int pack_id) {
                this.pack_id = pack_id;
            }

            public String getPack_name() {
                return pack_name;
            }

            public void setPack_name(String pack_name) {
                this.pack_name = pack_name;
            }

            public String getPack_fee() {
                return pack_fee;
            }

            public void setPack_fee(String pack_fee) {
                this.pack_fee = pack_fee;
            }

            public String getGoods_amount() {
                return goods_amount;
            }

            public void setGoods_amount(String goods_amount) {
                this.goods_amount = goods_amount;
            }

            public String getShipping_fee() {
                return shipping_fee;
            }

            public void setShipping_fee(String shipping_fee) {
                this.shipping_fee = shipping_fee;
            }

            public String getInsure_fee() {
                return insure_fee;
            }

            public void setInsure_fee(String insure_fee) {
                this.insure_fee = insure_fee;
            }

            public int getIntegral() {
                return integral;
            }

            public void setIntegral(int integral) {
                this.integral = integral;
            }

            public String getIntegral_money() {
                return integral_money;
            }

            public void setIntegral_money(String integral_money) {
                this.integral_money = integral_money;
            }

            public String getOrder_amount() {
                return order_amount;
            }

            public void setOrder_amount(String order_amount) {
                this.order_amount = order_amount;
            }

            public String getAdd_time() {
                return add_time;
            }

            public void setAdd_time(String add_time) {
                this.add_time = add_time;
            }

            public String getConfirm_time() {
                return confirm_time;
            }

            public void setConfirm_time(String confirm_time) {
                this.confirm_time = confirm_time;
            }

            public String getPay_time() {
                return pay_time;
            }

            public void setPay_time(String pay_time) {
                this.pay_time = pay_time;
            }

            public String getShipping_time() {
                return shipping_time;
            }

            public void setShipping_time(String shipping_time) {
                this.shipping_time = shipping_time;
            }

            public String getInvoice_no() {
                return invoice_no;
            }

            public void setInvoice_no(String invoice_no) {
                this.invoice_no = invoice_no;
            }

            public String getTo_buyer() {
                return to_buyer;
            }

            public void setTo_buyer(String to_buyer) {
                this.to_buyer = to_buyer;
            }

            public String getPay_note() {
                return pay_note;
            }

            public void setPay_note(String pay_note) {
                this.pay_note = pay_note;
            }

            public int getIs_separate() {
                return is_separate;
            }

            public void setIs_separate(int is_separate) {
                this.is_separate = is_separate;
            }

            public int getParent_id() {
                return parent_id;
            }

            public void setParent_id(int parent_id) {
                this.parent_id = parent_id;
            }

            public int getDiscount() {
                return discount;
            }

            public void setDiscount(int discount) {
                this.discount = discount;
            }

            public String getNote() {
                return note;
            }

            public void setNote(String note) {
                this.note = note;
            }

            public Object getRider_id() {
                return rider_id;
            }

            public void setRider_id(Object rider_id) {
                this.rider_id = rider_id;
            }

            public int getStore_id() {
                return store_id;
            }

            public void setStore_id(int store_id) {
                this.store_id = store_id;
            }

            public String getTask_code() {
                return task_code;
            }

            public void setTask_code(String task_code) {
                this.task_code = task_code;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public int getRider_status() {
                return rider_status;
            }

            public void setRider_status(int rider_status) {
                this.rider_status = rider_status;
            }

            public String getLat() {
                return lat;
            }

            public void setLat(String lat) {
                this.lat = lat;
            }

            public String getLng() {
                return lng;
            }

            public void setLng(String lng) {
                this.lng = lng;
            }

            public String getShipping_complete_time() {
                return shipping_complete_time;
            }

            public void setShipping_complete_time(String shipping_complete_time) {
                this.shipping_complete_time = shipping_complete_time;
            }

            public Object getShipping_estimate_time() {
                return shipping_estimate_time;
            }

            public void setShipping_estimate_time(Object shipping_estimate_time) {
                this.shipping_estimate_time = shipping_estimate_time;
            }

            public PayInfoBean getPay_info() {
                return pay_info;
            }

            public void setPay_info(PayInfoBean pay_info) {
                this.pay_info = pay_info;
            }

            public Object getOther_info() {
                return other_info;
            }

            public void setOther_info(Object other_info) {
                this.other_info = other_info;
            }

            public String getCommission_money() {
                return commission_money;
            }

            public void setCommission_money(String commission_money) {
                this.commission_money = commission_money;
            }

            public String getErrands_money() {
                return errands_money;
            }

            public void setErrands_money(String errands_money) {
                this.errands_money = errands_money;
            }

            public int getIs_comment() {
                return is_comment;
            }

            public void setIs_comment(int is_comment) {
                this.is_comment = is_comment;
            }

            public StoreBean getStore() {
                return store;
            }

            public void setStore(StoreBean store) {
                this.store = store;
            }

            public String getStatus_name() {
                return status_name;
            }

            public void setStatus_name(String status_name) {
                this.status_name = status_name;
            }

            public boolean isHas_cancel() {
                return has_cancel;
            }

            public void setHas_cancel(boolean has_cancel) {
                this.has_cancel = has_cancel;
            }

            public List<GoodsBean> getGoods() {
                return goods;
            }

            public void setGoods(List<GoodsBean> goods) {
                this.goods = goods;
            }

            public static class PayInfoBean implements Serializable{
                /**
                 * pay_id : 0KB50523P36611309
                 * paymentId : PAYID-LUM5POA5KV58917F60209945
                 */

                private String pay_id;
                private String paymentId;

                public String getPay_id() {
                    return pay_id;
                }

                public void setPay_id(String pay_id) {
                    this.pay_id = pay_id;
                }

                public String getPaymentId() {
                    return paymentId;
                }

                public void setPaymentId(String paymentId) {
                    this.paymentId = paymentId;
                }
            }

            public static class StoreBean implements Serializable{
                /**
                 * name : 优衣库2
                 * username :
                 * tel :
                 * store_id : 1
                 */

                private String name;
                private String username;
                private String tel;
                private int store_id;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getUsername() {
                    return username;
                }

                public void setUsername(String username) {
                    this.username = username;
                }

                public String getTel() {
                    return tel;
                }

                public void setTel(String tel) {
                    this.tel = tel;
                }

                public int getStore_id() {
                    return store_id;
                }

                public void setStore_id(int store_id) {
                    this.store_id = store_id;
                }
            }

            public static class GoodsBean implements Serializable{
                /**
                 * rec_id : 183
                 * order_id : 167
                 * goods_id : 2
                 * goods_name : 商品2
                 * goods_sn :
                 * goods_number : 1
                 * market_price : 0.00
                 * goods_price : 20.00
                 * goods_attr :
                 * is_real : 0
                 * parent_id : 0
                 * note :
                 */

                private int rec_id;
                private int order_id;
                private int goods_id;
                private String goods_name;
                private String goods_sn;
                private int goods_number;
                private String market_price;
                private String goods_price;
                private String goods_attr;
                private int is_real;
                private int parent_id;
                private String note;

                public int getRec_id() {
                    return rec_id;
                }

                public void setRec_id(int rec_id) {
                    this.rec_id = rec_id;
                }

                public int getOrder_id() {
                    return order_id;
                }

                public void setOrder_id(int order_id) {
                    this.order_id = order_id;
                }

                public int getGoods_id() {
                    return goods_id;
                }

                public void setGoods_id(int goods_id) {
                    this.goods_id = goods_id;
                }

                public String getGoods_name() {
                    return goods_name;
                }

                public void setGoods_name(String goods_name) {
                    this.goods_name = goods_name;
                }

                public String getGoods_sn() {
                    return goods_sn;
                }

                public void setGoods_sn(String goods_sn) {
                    this.goods_sn = goods_sn;
                }

                public int getGoods_number() {
                    return goods_number;
                }

                public void setGoods_number(int goods_number) {
                    this.goods_number = goods_number;
                }

                public String getMarket_price() {
                    return market_price;
                }

                public void setMarket_price(String market_price) {
                    this.market_price = market_price;
                }

                public String getGoods_price() {
                    return goods_price;
                }

                public void setGoods_price(String goods_price) {
                    this.goods_price = goods_price;
                }

                public String getGoods_attr() {
                    return goods_attr;
                }

                public void setGoods_attr(String goods_attr) {
                    this.goods_attr = goods_attr;
                }

                public int getIs_real() {
                    return is_real;
                }

                public void setIs_real(int is_real) {
                    this.is_real = is_real;
                }

                public int getParent_id() {
                    return parent_id;
                }

                public void setParent_id(int parent_id) {
                    this.parent_id = parent_id;
                }

                public String getNote() {
                    return note;
                }

                public void setNote(String note) {
                    this.note = note;
                }
            }
        }
    }
}
