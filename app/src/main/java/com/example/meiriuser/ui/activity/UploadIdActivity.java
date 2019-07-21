package com.example.meiriuser.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.meiriuser.R;
import com.example.meiriuser.base.BaseActivity;
import com.example.meiriuser.dialog.DialogUtil;
import com.example.meiriuser.model.net.AttestationPhoneModel;
import com.example.meiriuser.model.net.BillingAddressModel;
import com.example.meiriuser.net.ApiUrl;
import com.example.meiriuser.until.Constant;
import com.example.meiriuser.until.PreferenceUtil;
import com.example.meiriuser.widget.GlideCircleTransform;
import com.google.gson.Gson;
import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.app.TakePhotoImpl;
import com.jph.takephoto.compress.CompressConfig;
import com.jph.takephoto.model.CropOptions;
import com.jph.takephoto.model.InvokeParam;
import com.jph.takephoto.model.TContextWrap;
import com.jph.takephoto.model.TResult;
import com.jph.takephoto.permission.InvokeListener;
import com.jph.takephoto.permission.PermissionManager;
import com.jph.takephoto.permission.TakePhotoInvocationHandler;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.MediaType;
import rx.functions.Action1;

/**
 * Created by admin on 2019/5/30.
 */

public class UploadIdActivity extends BaseActivity implements TakePhoto.TakeResultListener, InvokeListener {
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.iv_passport)
    ImageView ivPassport;
    @BindView(R.id.iv_visa)
    ImageView ivVisa;
    @BindView(R.id.iv_driver_license)
    ImageView ivDriverLicense;

    TakePhoto takePhoto;
    InvokeParam invokeParam;
    File file;
    Uri uri;
    int size;
    CropOptions cropOptions;
    Bitmap bitmap;
    File test;

    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.line_visa)
    FrameLayout lineVisa;
    @BindView(R.id.line_driver_license)
    FrameLayout lineDriverLicense;

    private String passport;
    private String visa;
    private String divers_license;
    int phoneType;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_upload_id;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getTakePhoto().onCreate(savedInstanceState);
    }

    @Override
    public void initView() {
        tvRight.setVisibility(View.VISIBLE);
        tvRight.setText("提交");
        toolbarTitle.setText("上传身份证件");
        //各控件初始化
        file = new File(getExternalCacheDir(), System.currentTimeMillis() + ".png");
        uri = Uri.fromFile(file);
        size = Math.min(getResources().getDisplayMetrics().widthPixels, getResources().getDisplayMetrics().heightPixels);
        cropOptions = new CropOptions.Builder().setOutputX(size).setOutputX(size).setWithOwnCrop(false).create();
    }

    @Override
    public void initData() {
        super.initData();
        getattestation();
    }

    @OnClick({R.id.icon_back, R.id.line_passport, R.id.line_visa, R.id.line_driver_license,R.id.tv_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.icon_back:
                finish();
                break;
            case R.id.line_passport:
                getRxPermissions().request(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA)
                        .subscribe(new Action1<Boolean>() {
                            @Override
                            public void call(Boolean aBoolean) {
                                if (aBoolean) {
                                    phoneType=0;
                                    showPhotoDialog();
                                } else {
                                    showShort(getString(R.string.toast_permissions));
                                }
                            }
                        });
                break;
            case R.id.line_visa:
                getRxPermissions().request(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA)
                        .subscribe(new Action1<Boolean>() {
                            @Override
                            public void call(Boolean aBoolean) {
                                if (aBoolean) {
                                    phoneType=1;
                                    showPhotoDialog();
                                } else {
                                    showShort(getString(R.string.toast_permissions));
                                }
                            }
                        });

                break;
            case R.id.line_driver_license:
                getRxPermissions().request(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA)
                        .subscribe(new Action1<Boolean>() {
                            @Override
                            public void call(Boolean aBoolean) {
                                if (aBoolean) {
                                    phoneType=2;
                                    showPhotoDialog();
                                } else {
                                    showShort(getString(R.string.toast_permissions));
                                }
                            }
                        });
                break;
            case R.id.tv_right:
                attestation();
                break;
        }
    }


    public void showPhotoDialog() {
        DialogUtil dialogUtil = new DialogUtil();
        dialogUtil.showSelectPhoto(this, getWindowManager(), new DialogUtil.IDialogPhotoCallBack() {
            @Override
            public void setTakePhotoButton() {
                //拍照并裁剪
                takePhoto.onPickFromCaptureWithCrop(uri, cropOptions);
            }

            @Override
            public void setOpenPhotoButton() {
                //从照片选择并裁剪
                takePhoto.onPickFromGalleryWithCrop(uri, cropOptions);

            }

            @Override
            public void setCancelButton() {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        getTakePhoto().onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //以下代码为处理Android6.0、7.0动态权限所需
        PermissionManager.TPermissionType type = PermissionManager.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionManager.handlePermissionsResult(this, type, invokeParam, this);
    }

    @Override
    public PermissionManager.TPermissionType invoke(InvokeParam invokeParam) {
        PermissionManager.TPermissionType type = PermissionManager.checkPermission(TContextWrap.of(this), invokeParam.getMethod());
        if (PermissionManager.TPermissionType.WAIT.equals(type)) {
            this.invokeParam = invokeParam;
        }
        return type;
    }

    public TakePhoto getTakePhoto() {
        //获得TakePhoto实例
        if (takePhoto == null) {
            takePhoto = (TakePhoto) TakePhotoInvocationHandler.of(this).bind(new TakePhotoImpl(this, this));
        }
        //设置压缩规则，最大500kb
        takePhoto.onEnableCompress(new CompressConfig.Builder().setMaxSize(500 * 1024).create(), true);
        return takePhoto;
    }

    @Override
    public void takeSuccess(final TResult result) {
        //成功取得照片
        test = new File(result.getImage().getOriginalPath());
        if (test.exists()) {
            bitmap = BitmapFactory.decodeFile(result.getImage().getOriginalPath());
            uploadFile(test);
        }
    }


    @Override
    public void takeFail(TResult result, String msg) {
        /*//取得失败
        Toast.makeText(Setting.this,"设置失败",Toast.LENGTH_SHORT).show();*/
    }

    @Override
    public void takeCancel() {
        //取消
    }


    /**
     * 上传图片
     */
    public void uploadFile(File file) {

        showDialog();
        String url = ApiUrl.uploadFileUrl;
        OkHttpUtils
                .post()
                .url(url)
                .addFile("file", file.getName(), file)
                .addHeader("token", PreferenceUtil.getString(Constant.PF_TOKEN_KEY))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        showShort(e.getMessage());
                        dissDialog();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        dissDialog();
                        try {
                            JSONObject jsonObject = new JSONObject(response.toString());
                            int result = jsonObject.getInt("result");
                            if (result == 1) {
                                String url = jsonObject.getJSONObject("data").getString("url");
                                if(phoneType==0){
                                    passport=url;
                                    Glide.with(UploadIdActivity.this)
                                            .load(url)
                                            .into(ivPassport);
                                }else if(phoneType==1){
                                    visa=url;
                                    Glide.with(UploadIdActivity.this)
                                            .load(url)
                                            .into(ivVisa);
                                }else if(phoneType==2){
                                    Glide.with(UploadIdActivity.this)
                                            .load(url)
                                            .into(ivDriverLicense);
                                    divers_license=url;
                                }
                            } else {
                                String info = jsonObject.getString("info");
                                showShort(info);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });

    }


    public void getattestation() {
        showDialog();
        String url = ApiUrl.attestationUrl;
        OkHttpUtils
                .get()
                .url(url)
                .addHeader("token", PreferenceUtil.getString(Constant.PF_TOKEN_KEY))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        showShort(e.getMessage());
                        dissDialog();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        dissDialog();
                        try {
                            JSONObject jsonObject = new JSONObject(response.toString());
                            JSONObject attestationObject = jsonObject.getJSONObject("data").getJSONObject("attestation");
                            passport=attestationObject.getString("passport");
                            visa=attestationObject.getString("visa");
                            divers_license=attestationObject.getString("divers_license");
                            int result = jsonObject.getInt("result");
                            if (result == 1) {
                                Glide.with(UploadIdActivity.this)
                                        .load(passport)
                                        .into(ivPassport);
                                Glide.with(UploadIdActivity.this)
                                        .load(visa)
                                        .into(ivVisa);
                                Glide.with(UploadIdActivity.this)
                                        .load(divers_license)
                                        .into(ivDriverLicense);
                            } else {

                            }
                        } catch (JSONException e) {

                            e.printStackTrace();
                        }
                    }
                });

    }

    public void attestation() {
        showDialog();
        String url = ApiUrl.attestationUrl;
        AttestationPhoneModel attestationPhoneModel = new AttestationPhoneModel(passport, visa, divers_license);
        OkHttpUtils
                .postString()
                .url(url)
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .content(new Gson().toJson(attestationPhoneModel))
                .addHeader("token", PreferenceUtil.getString(Constant.PF_TOKEN_KEY))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        showShort(e.getMessage());
                        dissDialog();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        dissDialog();
                        try {
                            JSONObject jsonObject = new JSONObject(response.toString());
                            int result = jsonObject.getInt("result");
                            if (result == 1) {
                                showShort(getString(R.string.toast_submis_successful));
                                finish();
                            } else {
                                String info = jsonObject.getString("info");
                                showShort(info);
                            }
                        } catch (JSONException e) {

                            e.printStackTrace();
                        }
                    }
                });

    }
}
