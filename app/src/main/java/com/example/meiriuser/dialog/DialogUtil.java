package com.example.meiriuser.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.meiriuser.R;

/**
 * Created by li on 2016/9/2.
 * 对话框工具
 */
public class DialogUtil {

    private AlertDialog.Builder dialog;
    private AlertDialog alertDialog;
    String selectText;
    public int EXCHANGE_OK=1;//兑换专区-确认兑换
    public int EXCHANGE_OK_ENTERTAIN=2;//资源掠夺-确认


    //简单的显示对话框
    public void showSimpleDialogOKCancel(Context context, WindowManager manager,String title, String content, final IDialogCallBack callBack) {

        dialog = new AlertDialog.Builder(context);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_simple_layout, null);
        dialog.setView(view);
        final AlertDialog alertDialog = dialog.show();
        alertDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);//去掉圆角背景背后的棱角
        Window dialogWindow = alertDialog.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        DisplayMetrics dm = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(dm);
        lp.width = (int) (dm.widthPixels * 0.78);
        dialogWindow.setAttributes(lp);
        TextView tvtitle = (TextView) view.findViewById(R.id.tv_title);
        TextView tvcontext = (TextView) view.findViewById(R.id.tv_content);
        TextView sure = (TextView) view.findViewById(R.id.btn_sure);
        TextView cancle = (TextView) view.findViewById(R.id.bt_cancel);
        tvtitle.setText(title);
        tvcontext.setText(content);

        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(callBack!=null){
                    callBack.setPositiveButton();
                    alertDialog.dismiss();
                }
            }
        });

        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(callBack!=null){
                    callBack.setNegativeButton();
                    alertDialog.dismiss();
                }
            }
        });
    }


    //未缴纳保证金
    public void showPayDepositOKCancel(Context context, WindowManager manager,String content, final IDialogCallBack callBack) {

        dialog = new AlertDialog.Builder(context);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_pay_deposit_layout, null);
        dialog.setView(view);
        final AlertDialog alertDialog = dialog.show();
        alertDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);//去掉圆角背景背后的棱角
        Window dialogWindow = alertDialog.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        DisplayMetrics dm = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(dm);
        lp.width = (int) (dm.widthPixels * 0.74);
        dialogWindow.setAttributes(lp);
        TextView tvContent = (TextView) view.findViewById(R.id.tv_content);
        TextView tvToPay = (TextView) view.findViewById(R.id.tv_to_pay);

        tvToPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(callBack!=null){
                    callBack.setPositiveButton();
                    alertDialog.dismiss();
                }
            }
        });

    }


    //
    public void showEditextBottom(Context context, WindowManager manager, final IDialogEtCallBack callBack) {

        dialog = new AlertDialog.Builder(context);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_editext_bottom, null);
        dialog.setView(view);
        final AlertDialog alertDialog = dialog.show();
        alertDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);//去掉圆角背景背后的棱角
        //设置点击Dialog外部任意区域关闭Dialog
        alertDialog.setCanceledOnTouchOutside(true);
        Window dialogWindow = alertDialog.getWindow();
        dialogWindow.setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        DisplayMetrics dm = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(dm);
        lp.width = (int) (dm.widthPixels);
        dialogWindow.setWindowAnimations(R.style.bottomDialog); // 添加动画
        final EditText etInfo = (EditText) view.findViewById(R.id.et_info);
        TextView btCancel = (TextView) view.findViewById(R.id.bt_cancel);
        TextView btnSure = (TextView) view.findViewById(R.id.btn_sure);


        btnSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(callBack!=null){
                    callBack.setPositiveButton(etInfo.getText().toString().trim());
                    alertDialog.dismiss();
                }
            }
        });

        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(callBack!=null){
                    callBack.setNegativeButton();
                    alertDialog.dismiss();
                }
            }
        });


    }


    //选择图片
    public void showSelectPhoto(Context context, WindowManager manager,final IDialogPhotoCallBack callBack) {

        dialog = new AlertDialog.Builder(context);
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_bottom_menu, null);
        dialog.setView(view);
        final AlertDialog alertDialog = dialog.show();
        alertDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);//去掉圆角背景背后的棱角
        //设置点击Dialog外部任意区域关闭Dialog
        alertDialog.setCanceledOnTouchOutside(true);
        Window dialogWindow = alertDialog.getWindow();
        dialogWindow.setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        DisplayMetrics dm = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(dm);
        lp.width = (int) (dm.widthPixels);
        dialogWindow.setWindowAnimations(R.style.bottomDialog); // 添加动画
        TextView takePhoto = (TextView) view.findViewById(R.id.takePhoto);
        TextView openPhotos = (TextView) view.findViewById(R.id.openPhotos);
        TextView cancel = (TextView) view.findViewById(R.id.cancel);
        takePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(callBack!=null){
                    callBack.setTakePhotoButton();
                    alertDialog.dismiss();
                }

            }
        });
        openPhotos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(callBack!=null){
                    callBack.setOpenPhotoButton();
                    alertDialog.dismiss();
                }
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(callBack!=null){
                    callBack.setCancelButton();
                    alertDialog.dismiss();
                }
            }
        });
    }



    public interface IDialogPhotoCallBack {
        void setTakePhotoButton();
        void setOpenPhotoButton();
        void setCancelButton();
    }


    public interface IDialogEtCallBack {
        void setPositiveButton(String positiveStr);
        void setNegativeButton();
    }
}
