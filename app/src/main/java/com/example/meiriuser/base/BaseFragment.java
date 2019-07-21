package com.example.meiriuser.base;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.meiriuser.R;
import com.example.meiriuser.event.BusProvider;
import com.tbruyelle.rxpermissions.RxPermissions;

import butterknife.ButterKnife;

/**
 * Created by admin on 2019/4/22.
 */

public abstract class BaseFragment extends Fragment {

    private RxPermissions rxPermissions;
    private ProgressDialog progressDialog;
    // 全局Toast控制器
    private Toast toast;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //子类不再需要设置布局ID，也不再需要使用ButterKnife.bind()
        View rootView = inflater.inflate(provideContentViewId(), container, false);
        ButterKnife.bind(this, rootView);
        initView(rootView);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
        initListener();
        BusProvider.getBus().register(this);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        BusProvider.getBus().unregister(this);
    }

    public void init() {

    }

    public void initView(View rootView) {
    }

    public void initData() {

    }

    public void initListener() {

    }

    //得到当前界面的布局文件id(由子类实现)
    protected abstract int provideContentViewId();


    protected RxPermissions getRxPermissions() {
        rxPermissions = new RxPermissions(getActivity());
        rxPermissions.setLogging(true);
        return rxPermissions;
    }

    /**
     * 显示吐司
     *
     * @param msg
     */
    public void showShort(String msg) {
        if (toast != null) {
            toast.cancel();
            toast = null;
        }
        toast = Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT);
        toast.show();
    }

    /**
     * 显示菊花
     * 使用默认提示
     */
    public void showDialog() {
        if (isAdded()){
            showDialog(getString(R.string.dialog_loading));
        }
    }

    /**
     * 显示菊花
     *
     * @param msg
     */
    public void showDialog(String msg) {
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage(msg);
        progressDialog.show();
    }
    /**
     * 显示菊花
     *
     * @param msg
     */
    public void showNoDialog(String msg) {
        try {
            if(progressDialog==null){
                progressDialog = new ProgressDialog(getActivity());
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
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    /**
     * 嵌入Fragment容器
     */
    protected void setFragment(Fragment fragment, int resId) {
        FragmentTransaction ft = getChildFragmentManager().beginTransaction();
        ft.replace(resId, fragment, fragment.getClass().getName());
        ft.commitAllowingStateLoss();
    }

    /**
     * 嵌入Fragment容器
     */
    protected void setFragment(Fragment fragment, int resId,Bundle bundle) {
        FragmentTransaction ft = getChildFragmentManager().beginTransaction();
        ft.replace(resId, fragment, fragment.getClass().getName());
        fragment.setArguments(bundle);
        ft.commitAllowingStateLoss();
    }

    public void jumpToActivity(Intent intent) {
        startActivity(intent);
    }

    public void jumpToActivity(Class activity) {
        Intent intent = new Intent(getActivity(), activity);
        startActivity(intent);
    }

    public void jumpToExtraActivity(String url,Class activity) {
        Intent intent = new Intent(getActivity(), activity);
        intent.putExtra("url", url);
        jumpToActivity(intent);
    }

    public void jumpToActivityAndClearTask(Class activity) {
        Intent intent = new Intent(getActivity(), activity);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        getActivity().finish();
    }

    public void jumpToActivityAndClearTop(Class activity) {
        Intent intent = new Intent(getActivity(), activity);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }


}
