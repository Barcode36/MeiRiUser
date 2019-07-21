package com.example.meiriuser.until;

import android.graphics.Color;
import android.os.CountDownTimer;
import android.widget.TextView;

/**
 * Created by admin on 2019/5/22.
 */

public class PayCountDownUtils extends CountDownTimer {
    private boolean isRunning = false;
    private TextView mView;
    private int startColor;
    private int endColor;

    public void setStartColor(int startColor) {
        this.startColor = startColor;
    }

    public void setEndColor(int endColor) {
        this.endColor = endColor;
    }

    public void setStartTxt(String startTxt) {
        this.startTxt = startTxt;
    }

    public void setEndTxt(String endTxt) {
        this.endTxt = endTxt;
    }

    private String startTxt;
    private String endTxt;


    /**
     * @param millisInFuture    倒计时总时长---毫秒数
     * @param countDownInterval 倒计时间隔长---毫秒数
     * @param mView             倒计时要显示的View---目前为TextView
     */
    public PayCountDownUtils(long millisInFuture, long countDownInterval, TextView mView) {
        super(millisInFuture, countDownInterval);
        this.mView = mView;
    }

    public boolean isRunning() {
        return isRunning;
    }

    @Override
    public void onTick(long l) {
        isRunning = true;
        mView.setEnabled(false);
        mView.setText(startTxt+"  " + formatDateTime((l/1000)));
        mView.setTextColor(startColor);//Color.parseColor("#FFC016")
    }

    @Override
    public void onFinish() {
        isRunning = false;
        mView.setEnabled(true);
        mView.setClickable(true);
        mView.setText(endTxt);
        mView.setTextColor(endColor);
    }

    public void IsFinish(String Tips) {
        cancel();
        isRunning = false;
        mView.setEnabled(true);
        mView.setClickable(true);
        mView.setText(Tips);
    }

    public  String formatDateTime(long mss) {
        String DateTimes = null;
        long days = mss / ( 60 * 60 * 24);
        long hours = (mss % ( 60 * 60 * 24)) / (60 * 60);
        long minutes = (mss % ( 60 * 60)) /60;
        long seconds = mss % 60;
        if(days>0){
            DateTimes= days + "天" + hours + "小时" + minutes + "分钟" + seconds + "秒";
        }else if(hours>0){
            DateTimes=hours + "小时" + minutes + "分钟" + seconds + "秒";
        }else if(minutes>0){
            DateTimes=minutes + ":" + seconds;
        }else{
            DateTimes=seconds + "秒";
        }
        return DateTimes;
    }


}
