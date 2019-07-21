package com.example.meiriuser.until;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

import com.blankj.utilcode.util.TimeUtils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Created by admin on 2019/5/28.
 */

public class CommanUtils {

    public static void callPhone(Context context,String phone){
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:" + phone);
        intent.setData(data);
        context.startActivity(intent);
    }


    public static void  getDistanceTime(String s,long currentTime) throws ParseException {

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        long days;
        long hours;
        long minutes;
        try
        {
            Date d2 = df.parse(s);
            long diff = currentTime - d2.getTime();//这样得到的差值是微秒级别
            days = diff / (1000 * 60 * 60 * 24);
            hours = (diff-days*(1000 * 60 * 60 * 24))/(1000* 60 * 60);
            minutes = (diff-days*(1000 * 60 * 60 * 24)-hours*(1000* 60 * 60))/(1000* 60);
            System.out.println(""+days+"天"+hours+"小时"+minutes+"分");
        }catch (Exception e) {

        }

    }



    /**
     * 32位MD5加密
     * @param content -- 待加密内容
     * @return
     */
    public static String md5Decode(String content) {
        byte[] hash;
        try {
            hash = MessageDigest.getInstance("MD5").digest(content.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("NoSuchAlgorithmException",e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("UnsupportedEncodingException", e);
        }
        //对生成的16字节数组进行补零操作
        StringBuilder hex = new StringBuilder(hash.length * 2);
        for (byte b : hash) {
            if ((b & 0xFF) < 0x10){
                hex.append("0");
            }
            hex.append(Integer.toHexString(b & 0xFF));
        }
        return hex.toString();
    }


    // appID：1559534975
    //appKey：74CA7F5576027963885D0C3A3567DF2950d47e36b9c

    public static String signature(){
        String appID="1559534975";
        String appKey="74CA7F5576027963885D0C3A3567DF2950d47e36b9c";
      /*  int nonce=(int)((Math.random()*9+1));//生成5位随机数 Math.random生成的是一个[0.0，1.0）*/

        int n = 6;//循环的次数
        String nonce="";
        for (int i = 0; i < n; i++) {
            int num = (int)((Math.random()*9+1));//方法1 随机产生1~6的数字
            nonce=num+nonce;
        }
        String timeStamp=String.valueOf(System.currentTimeMillis());
        return md5Decode(appID+appKey+nonce+timeStamp);

    }

    /**
     * 刷新的index下标计算
     */
    public static int getRefreshIndex(int index) {
        int index2 = index > 2 ? ((index - 1) * Constant.PAGENO) : 1;
        Log.e("index.......>>", index2 + "");
        return index2;
    }



    public static String doubleTrans(double num){
        String number1 = String.format("%.6f", num);//只保留小数点后6位
        double number2 = Double.parseDouble(number1);//類型轉換
        if(Math.round(number2)-number2 == 0){
            return String.valueOf((long)number2);
        }
        return String.valueOf(number2);
    }







}
