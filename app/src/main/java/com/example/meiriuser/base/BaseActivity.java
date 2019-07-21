package com.example.meiriuser.base;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.Toast;


import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.util.Util;
import com.example.meiriuser.R;
import com.example.meiriuser.event.BusProvider;
import com.tbruyelle.rxpermissions.RxPermissions;

import com.yanzhenjie.sofia.Sofia;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import butterknife.ButterKnife;

/**
 * Created by admin on 2019/4/22.
 */

public abstract class BaseActivity extends AppCompatActivity {
    private RxPermissions rxPermissions;
    // 进度菊花
    private ProgressDialog progressDialog=null;
    // 全局Toast控制器
    private Toast toast;
    private Method noteStateNotSavedMethod;
    private Object fragmentMgr;
    private String[] activityClassName = {"Activity", "FragmentActivity"};


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); // 竖屏
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN); //设置EditText不主动弹出软键盘
        BaseApp.activities.add(this);
        init();
        //子类不再需要设置布局ID，也不再需要使用ButterKnife.bind()
        setContentView(provideContentViewId());
        ButterKnife.bind(this);
        //状态栏
        Sofia.with(this).statusBarBackground(getResources().getColor(R.color.colorPrimary)).statusBarDarkFont();
        //初始化控件
        initView();
        //初始化数据
        initData();
        //点击事件
        initListener();
    }

    @Override
    protected void onStart() {
        super.onStart();
        BusProvider.getBus().register(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        BusProvider.getBus().unregister(this);

        //Glide导致You cannot start a load on a fragment before it is attached
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            if (Util.isOnMainThread() && !isFinishing() && !isDestroyed()) {
                Glide.with(this).pauseRequests();
            }
        } else {
            if (Util.isOnMainThread() && !isFinishing()) {
                Glide.with(this).pauseRequests();
            }
        }

    }




    //在setContentView()调用之前调用，可以设置WindowFeature(如：this.requestWindowFeature(Window.FEATURE_NO_TITLE);)
    public  void init() {
    }

    public void initView() {
    }

    public void initData() {
    }

    public void initListener() {
    }
    //得到当前界面的布局文件id(由子类实现)
    protected abstract int provideContentViewId();


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        invokeFragmentManagerNoteStateNotSaved();
    }

    private void invokeFragmentManagerNoteStateNotSaved() {
        //java.lang.IllegalStateException: Can not perform this action after onSaveInstanceState
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
            return;
        }
        try {
            if (noteStateNotSavedMethod != null && fragmentMgr != null) {
                noteStateNotSavedMethod.invoke(fragmentMgr);
                return;
            }
            Class cls = getClass();
            do {
                cls = cls.getSuperclass();
            } while (!(activityClassName[0].equals(cls.getSimpleName())
                    || activityClassName[1].equals(cls.getSimpleName())));

            Field fragmentMgrField = prepareField(cls, "mFragments");
            if (fragmentMgrField != null) {
                fragmentMgr = fragmentMgrField.get(this);
                noteStateNotSavedMethod = getDeclaredMethod(fragmentMgr, "noteStateNotSaved");
                if (noteStateNotSavedMethod != null) {
                    noteStateNotSavedMethod.invoke(fragmentMgr);
                }
            }

        } catch (Exception ex) {
        }
    }

    private Field prepareField(Class<?> c, String fieldName) throws NoSuchFieldException {
        while (c != null) {
            try {
                Field f = c.getDeclaredField(fieldName);
                f.setAccessible(true);
                return f;
            } finally {
                c = c.getSuperclass();
            }
        }
        throw new NoSuchFieldException();
    }

    private Method getDeclaredMethod(Object object, String methodName, Class<?>... parameterTypes) {
        Method method = null;
        for (Class<?> clazz = object.getClass(); clazz != Object.class; clazz = clazz.getSuperclass()) {
            try {
                method = clazz.getDeclaredMethod(methodName, parameterTypes);
                return method;
            } catch (Exception e) {
            }
        }
        return null;
    }





    public void jumpToActivity(Intent intent) {
        startActivity(intent);
    }

    public void jumpToActivity(Class activity) {
        Intent intent = new Intent(this, activity);
        startActivity(intent);
    }

    public void jumpToExtraActivity(String url,Class activity) {
        Intent intent = new Intent(this, activity);
        intent.putExtra("url", url);
        jumpToActivity(intent);
    }

    public void jumpToActivityAndClearTask(Class activity) {
        Intent intent = new Intent(this, activity);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    public void jumpToActivityAndClearTop(Class activity) {
        Intent intent = new Intent(this, activity);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    /**
     * 显示吐司
     *
     * @param msg
     */
    public void showShort(String msg) {
        ToastUtils.showShort(msg);
    }


    /**
     * 显示菊花
     * 使用默认提示
     */
    public void showDialog() {
        showNoDialog(getString(R.string.dialog_loading));
    }

    /**
     * 显示菊花
     *
     * @param msg
     */
    public void showDialog(String msg) {
        try {
            if(progressDialog==null) {
                progressDialog = new ProgressDialog(this);
                progressDialog.setMessage(msg);
                progressDialog.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 显示菊花
     *
     * @param msg
     */
    public void showNoDialog(String msg) {
        try {
            if(progressDialog==null){
                progressDialog = new ProgressDialog(this);
                progressDialog.setCancelable(false);
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.setMessage(msg);
                progressDialog.show();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 隐藏掉菊花
     */
    public void dissDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
            progressDialog=null;
        }
    }



    /**
     * 显示吐司
     *
     * @param msg
     *//*
    public void showShort(String msg) {
        ToastUtils.setMessageColor(Color.parseColor("").showShort(msg);
    }
*/
    /**
     * 添加Fragment
     *
     * @param container_id 容器id
     * @param f            Fragment对象
     */
    protected void replaceFragment(int container_id, Fragment f) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(container_id, f);
        ft.commitAllowingStateLoss();
    }



    protected RxPermissions getRxPermissions() {
        rxPermissions = new RxPermissions(this);
        rxPermissions.setLogging(true);
        return rxPermissions;
    }

    /**
     * 嵌入Fragment容器
     */
    protected void setFragment(Fragment fragment, int resId,Bundle bundle) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        fragment.setArguments(bundle);
        ft.replace(resId, fragment, fragment.getClass().getName());
        ft.commitAllowingStateLoss();
    }

    /**
     * 嵌入Fragment容器
     */
    protected void setFragment(Fragment fragment, int resId) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(resId, fragment, fragment.getClass().getName());
        ft.commitAllowingStateLoss();
    }
}
