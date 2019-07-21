package com.example.meiriuser.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectChangeListener;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.blankj.utilcode.util.KeyboardUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.example.meiriuser.R;
import com.example.meiriuser.base.BaseActivity;
import com.example.meiriuser.dialog.DialogUtil;
import com.example.meiriuser.event.BusProvider;
import com.example.meiriuser.event.LoginRefreshEvent;
import com.example.meiriuser.model.net.EditInfoModel;
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
import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import rx.functions.Action1;

/**
 * Created by admin on 2019/5/30.
 */

public class PersonInfoActivity extends BaseActivity implements TakePhoto.TakeResultListener, InvokeListener {
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.iv_head)
    ImageView ivHead;
    @BindView(R.id.tv_nickname)
    TextView tvNickname;
    @BindView(R.id.tv_sex)
    TextView tvSex;
    public int EDITOR_HEAD = 1;
    public int EDITOR_NAME = 2;
    public int EDITOR_SEX = 3;
    String nickName;
    String sex;
    String token;

    TakePhoto takePhoto;
    InvokeParam invokeParam;
    File file;
    Uri uri;
    int size;
    CropOptions cropOptions;
    Bitmap bitmap;
    File test;
    @BindView(R.id.line_head)
    LinearLayout lineHead;
    @BindView(R.id.line_nickname)
    LinearLayout lineNickname;
    @BindView(R.id.line_sex)
    LinearLayout lineSex;
    @BindView(R.id.line_receiving_address)
    LinearLayout lineReceivingAddress;
    private OptionsPickerView pvOptions;
    private ArrayList<String> options1ItemsString = new ArrayList<>();


    @Override
    protected int provideContentViewId() {
        return R.layout.activity_person_info;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getTakePhoto().onCreate(savedInstanceState);
    }


    @Override
    public void initView() {
        token = PreferenceUtil.getString(Constant.PF_TOKEN_KEY);
        toolbarTitle.setText("个人信息");
        String image = PreferenceUtil.getString(Constant.PF_HEAD_IMG);
        Glide.with(this)
                .load(image)
                .placeholder(R.mipmap.icon_logo_oval)
                .centerCrop()
                .bitmapTransform(new GlideCircleTransform(this))
                .into(ivHead);

        //各控件初始化
        file = new File(getExternalCacheDir(), System.currentTimeMillis() + ".png");
        uri = Uri.fromFile(file);
        size = Math.min(getResources().getDisplayMetrics().widthPixels, getResources().getDisplayMetrics().heightPixels);
        cropOptions = new CropOptions.Builder().setOutputX(size).setOutputX(size).setWithOwnCrop(false).create();
    }

    @Override
    public void initData() {
        String name = PreferenceUtil.getString(Constant.PF_USER_NAME);
        int sex = PreferenceUtil.getInt(Constant.PF_SEX, 0);
        if (sex == 1) {
            tvSex.setText(R.string.text_male);
        } else if (sex == 2) {
            tvSex.setText(R.string.text_female);
        } else {
            tvSex.setText(R.string.text_secret);
        }
        tvNickname.setText(name);

        options1ItemsString.add(getString(R.string.text_male));
        options1ItemsString.add(getString(R.string.text_female));
        options1ItemsString.add(getString(R.string.text_secret));
        initOptionPicker();
    }


    @Override
    public void initListener() {
        super.initListener();
        BusProvider.getBus().toObservable(LoginRefreshEvent.class)
                .subscribe(new Action1<LoginRefreshEvent>() {
                    @Override
                    public void call(LoginRefreshEvent event) {
                        if(event.getFlag()==LoginRefreshEvent.MODIFY_PERSON_INFO){
                            String name = PreferenceUtil.getString(Constant.PF_USER_NAME);
                            tvNickname.setText(name);
                        }
                    }
                });
    }

    @OnClick({R.id.icon_back, R.id.line_head, R.id.line_nickname, R.id.line_sex, R.id.line_receiving_address})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.icon_back:
                finish();
                break;
            case R.id.line_head:
                getRxPermissions().request(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA)
                        .subscribe(new Action1<Boolean>() {
                            @Override
                            public void call(Boolean aBoolean) {
                                if (aBoolean) {
                                    showPhotoDialog();
                                } else {
                                    showShort(getString(R.string.toast_permissions));
                                }
                            }
                        });
                break;
            case R.id.line_nickname:
                Intent intent=new Intent(PersonInfoActivity.this,ModifyNameActivity.class);
                intent.putExtra("modifyname",tvNickname.getText().toString().trim());
                jumpToActivity(intent);
                break;
            case R.id.line_sex:
                KeyboardUtils.hideSoftInput(lineSex);
                pvOptions.show();
                break;
            case R.id.line_receiving_address:
                jumpToActivity(ReceivingAddressActivity.class);
                break;
        }
    }


    private void initOptionPicker() {//条件选择器初始化

        pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                String tx = options1ItemsString.get(options1);
                int sex=0;
                if (tx.equals(getString(R.string.text_male))) {
                    sex=1;
                } else if (tx.equals(getString(R.string.text_female))) {
                    sex=2;
                }
                editInfo("sex", sex+"");
                tvSex.setText(tx);

            }
        })
                .setTitleText(getString(R.string.text_select_sex))
                .setContentTextSize(20)//设置滚轮文字大小
                .setDividerColor(Color.LTGRAY)//设置分割线的颜色
                .setSelectOptions(0, 1)//默认选中项
                .setBgColor(Color.WHITE)
                .setTitleBgColor(Color.WHITE)
                .setTitleColor(Color.BLACK)
                .setCancelColor(Color.BLACK)
                .setSubmitText(getString(R.string.text_sure))
                .setCancelText(getString(R.string.text_cancel))
                .setSubmitColor(Color.BLACK)
                .setTextColorCenter(Color.LTGRAY)
                .isRestoreItem(true)//切换时是否还原，设置默认选中第一项。
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setOptionsSelectChangeListener(new OnOptionsSelectChangeListener() {
                    @Override
                    public void onOptionsSelectChanged(int options1, int options2, int options3) {
                       /* String str = "options1: " + options1 + "\noptions2: " + options2 + "\noptions3: " + options3;
                        Toast.makeText(MainActivity.this, str, Toast.LENGTH_SHORT).show();*/
                    }
                })
                .build();

        pvOptions.setPicker(options1ItemsString);//一级选择器
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
            uploadFile(test, token);
            /*editInfo(token,EDITOR_HEAD,test);*/
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
     * 修改用户信息
     */
    public void editInfo(final String name, final String value) {

        showDialog();
        String url = ApiUrl.editInfoUrl;
        EditInfoModel updatePwdModel = new EditInfoModel(name, value);
        Gson gson = new Gson();
        final String s = gson.toJson(updatePwdModel);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), s);
        OkHttpClient okHttpClient1 = OkHttpUtils.getInstance().getOkHttpClient();
        Request request = new Request.Builder().url(url)
                .post(requestBody).addHeader("token", PreferenceUtil.getString(Constant.PF_TOKEN_KEY)).build();
        okHttpClient1.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                dissDialog();
                ToastUtils.showShort(e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                dissDialog();
                String strresponse = response.body().string();
                try {
                    JSONObject jsonObject = new JSONObject(strresponse);
                    int result = jsonObject.getInt("result");
                    if (result == 1) {
                        showShort(getString(R.string.toast_modify_successful));
                        Message message = new Message();
                        if (name.equals("headImg")) {
                            PreferenceUtil.commitString(Constant.PF_HEAD_IMG, value);
                            message.what = 1;
                        }else if(name.equals("sex")){
                            PreferenceUtil.commitInt(Constant.PF_SEX,Integer.valueOf(value));
                        }
                        myHandler.sendMessage(message);
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

    Handler myHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    String image = PreferenceUtil.getString(Constant.PF_HEAD_IMG);
                    Glide.with(PersonInfoActivity.this)
                            .load(image)
                            .centerCrop()
                            .bitmapTransform(new GlideCircleTransform(PersonInfoActivity.this))
                            .into(ivHead);
                    BusProvider.getBus().post(new LoginRefreshEvent());
                    break;
            }
            super.handleMessage(msg);
        }
    };


    /**
     * 上传图片
     */
    public void uploadFile(File file, String token) {

        showDialog();
        String url = ApiUrl.uploadFileUrl;
        OkHttpUtils
                .post()
                .url(url)
                .addFile("file", file.getName(), file)
                .addHeader("token", token)
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
                                editInfo("headImg", url);//username 用户名 sex 性别 headImg 头像URL
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
