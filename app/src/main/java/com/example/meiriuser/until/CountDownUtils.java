package com.example.meiriuser.until;

import android.graphics.Color;
import android.os.CountDownTimer;
import android.widget.TextView;

/**
 *
 * 倒数计时工具类
 */
public class CountDownUtils extends CountDownTimer {
    private boolean isRunning = false;
    private TextView mView;



    /**
     * @param millisInFuture    倒计时总时长---毫秒数
     * @param countDownInterval 倒计时间隔长---毫秒数
     * @param mView             倒计时要显示的View---目前为TextView
     */
    public CountDownUtils(long millisInFuture, long countDownInterval, TextView mView) {
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
        mView.setText((l / 1000) + "s后重新获取");
    }

    @Override
    public void onFinish() {
        isRunning = false;
        mView.setEnabled(true);
        mView.setClickable(true);
        mView.setText("获取验证码");
    }

    public void IsFinish(String Tips) {
        cancel();
        isRunning = false;
        mView.setEnabled(true);
        mView.setClickable(true);
        mView.setText(Tips);
    }
}
