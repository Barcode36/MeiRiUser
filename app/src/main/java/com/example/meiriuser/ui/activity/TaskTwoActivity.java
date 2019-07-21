package com.example.meiriuser.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.example.meiriuser.R;
import com.example.meiriuser.base.BaseActivity;
import com.example.meiriuser.dialog.DialogUtil;
import com.example.meiriuser.event.BusProvider;
import com.example.meiriuser.event.OrderFinishEvent;
import com.example.meiriuser.model.AddressModel;
import com.example.meiriuser.model.CleanMoreContentModel;
import com.example.meiriuser.model.net.CreateTaskModel;
import com.example.meiriuser.net.ApiUrl;
import com.example.meiriuser.until.Constant;
import com.example.meiriuser.until.PreferenceUtil;
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
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import rx.functions.Action1;

/**
 * Created by admin on 2019/5/29.
 */

public class TaskTwoActivity extends BaseActivity implements TakePhoto.TakeResultListener, InvokeListener {
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.et_title)
    EditText etTitle;
    @BindView(R.id.tv_title_number)
    TextView tvTitleNumber;
    @BindView(R.id.et_content)
    EditText etContent;
    @BindView(R.id.tv_content_number)
    TextView tvContentNumber;
    @BindView(R.id.is_switch)
    ToggleButton isSwitch;
    @BindView(R.id.id_flowlayout)
    TagFlowLayout mFlowLayout;
    @BindView(R.id.btn_next)
    Button btnNext;
    @BindView(R.id.img_flowlayout)
    TagFlowLayout imgFlowlayout;
    TagAdapter imgAdapter;
    List<String> mimgVals;
    List<String> mVals;
    TagAdapter tagAdapter;
    TakePhoto takePhoto;
    InvokeParam invokeParam;
    File file;
    Uri uri;
    int size;
    CropOptions cropOptions;
    Bitmap bitmap;
    File test;
    CreateTaskModel createTaskModel;
    CreateTaskModel.CreateTaskInfo createTaskInfo;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    String selectAddress="";
    String title;
    String content;
    List<String> cleanMoreContentModelList;
    String otherInfo="";


    @Override
    protected int provideContentViewId() {
        return R.layout.activity_task_two;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getTakePhoto().onCreate(savedInstanceState);
    }

    @Override
    public void initView() {
        //各控件初始化
        createTaskModel = new CreateTaskModel();
        createTaskInfo = new CreateTaskModel.CreateTaskInfo();
        cleanMoreContentModelList=new ArrayList<>();
        Intent intent = getIntent();
        if (intent != null) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                createTaskInfo = (CreateTaskModel.CreateTaskInfo) bundle.getSerializable(Constant.DATEBEAN);
                if(createTaskInfo!=null) {
                    etTitle.setText("清洁我的"+ createTaskInfo.getProperty()+"含"+createTaskInfo.getBedroomNum() +"间卧室和"+ createTaskInfo.getRestroomNum()+"间洗手间");
                    cleanMoreContentModelList.addAll(createTaskInfo.getOtherInfo());
                    if (cleanMoreContentModelList.contains(getString(R.string.text_laundry))) {
                        otherInfo = otherInfo + "- 完成(洗涤和折叠)的洗衣周期 - 应约1小时\n";
                    }
                    if (cleanMoreContentModelList.contains(getString(R.string.text_oven))) {
                        otherInfo = otherInfo + "- 烤箱清洁里面 - 应约1小时\n";
                    }
                    if (cleanMoreContentModelList.contains(getString(R.string.text_cupboard))) {
                        otherInfo = otherInfo + "- 衣柜内部清洁 - 应约 1 小时\n";
                    }
                    if (cleanMoreContentModelList.contains(getString(R.string.text_window))) {
                        otherInfo = otherInfo + "- 窗户(内部侧)清洁 - 应约1小时\n";
                    }
                    if (cleanMoreContentModelList.contains(getString(R.string.text_carpet))) {
                        otherInfo = otherInfo + "- 地毯蒸汽清洁 - 应约 1小时\n";
                    }
                    etContent.setText("需要一个可靠的美日帮手来帮我打扫" + createTaskInfo.getBedroomNum() + "间卧室/" + createTaskInfo.getRestroomNum() + "间洗手间的" + createTaskInfo.getProperty() + "。\n" +
                            "\n" +
                            "标准美日帮手清洁工作应包括：\n" +
                            "\n" +
                            "-室内各处：擦拭家具和可见表面灰层；拖地和吸层器清洁地板；清空垃圾\n" +
                            "\n" +
                            "-卫生间：清洗淋浴间、浴缸、卫生间的清洁；\n" +
                            "\n" +
                            "-厨房：洗碗；\n" +
                            "\n" +
                            "我还希望包括以下清洁任务：\n" +
                            "\n" + otherInfo);
                    createTaskModel.setInfo(createTaskInfo);
                }

            }

        }

        file = new File(getExternalCacheDir(), System.currentTimeMillis() + ".png");
        uri = Uri.fromFile(file);
        size = Math.min(getResources().getDisplayMetrics().widthPixels, getResources().getDisplayMetrics().heightPixels);
        cropOptions = new CropOptions.Builder().setOutputX(size).setOutputX(size).setWithOwnCrop(false).create();
        toolbarTitle.setText("任务细节");
        mVals = new ArrayList<>();
        mimgVals = new ArrayList<>();

    }

    @Override
    public void initData() {
        super.initData();
        tagAdapter = new TagAdapter<String>(mVals) {
            @Override
            public View getView(FlowLayout parent, final int position, String s) {

                FrameLayout iv = (FrameLayout) LayoutInflater.from(TaskTwoActivity.this).inflate(R.layout.item_task_flow,
                        mFlowLayout, false);
                TextView viewById = (TextView) iv.findViewById(R.id.tv_flow);
                viewById.setText(s);
                ImageView imageView = (ImageView) iv.findViewById(R.id.iv_delete);
                imageView.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        mVals.remove(position);
                        tagAdapter.notifyDataChanged();

                    }
                });
                return iv;
            }
        };
        mFlowLayout.setAdapter(tagAdapter);


        imgAdapter = new TagAdapter<String>(mimgVals) {
            @Override
            public View getView(FlowLayout parent, final int position, String s) {

                FrameLayout iv = (FrameLayout) LayoutInflater.from(TaskTwoActivity.this).inflate(R.layout.item_imag_flow,
                        mFlowLayout, false);
                ImageView viewById = (ImageView) iv.findViewById(R.id.iv_flow);
                Glide.with(TaskTwoActivity.this)
                        .load(s)
                        .into(viewById);
                ImageView imageView = (ImageView) iv.findViewById(R.id.iv_delete);
                imageView.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        mimgVals.remove(position);
                        imgAdapter.notifyDataChanged();

                    }
                });
                return iv;
            }
        };
        imgFlowlayout.setAdapter(imgAdapter);
    }


    @Override
    public void initListener() {
     /*   etTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                tvTitleNumber.setText(etTitle.getText().toString().trim().length() + "/15");
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });*/
      /*  etContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                tvContentNumber.setText(etContent.getText().toString().trim().length() + "/100");
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });*/
        isSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                createTaskModel.setIs_online(b);
            }
        });
        BusProvider.getBus().toObservable(OrderFinishEvent.class)
                .subscribe(new Action1<OrderFinishEvent>() {
                    @Override
                    public void call(OrderFinishEvent newMessageEvent) {
                        finish();
                    }
                });

    }

    @OnClick({R.id.icon_back, R.id.line_photo, R.id.line_task_address, R.id.btn_next, R.id.iv_add_flow})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.icon_back:
                finish();
                break;
            case R.id.line_photo:
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
            case R.id.line_task_address:
                startActivityForResult(new Intent(TaskTwoActivity.this, SelectDelegationAddressActivity.class), 2);
                break;
            case R.id.btn_next:
                String title=etTitle.getText().toString().trim();
                String content=etContent.getText().toString().trim();
                if(TextUtils.isEmpty(title)){
                    ToastUtils.showShort(R.string.toast_input_task_title);
                    return;
                }
                if(TextUtils.isEmpty(selectAddress)){
                    ToastUtils.showShort(R.string.toast_input_task_address);
                    return;
                }
                createTaskModel.setTitle(title);
                createTaskModel.setDesc(content);
                createTaskModel.setTags(mVals);
                createTaskModel.setImages(mimgVals);
                Intent intent=new Intent(TaskTwoActivity.this,TaskTimeActivity.class);
                intent.putExtra(Constant.DATEBEAN,createTaskModel);
                jumpToActivity(intent);
                break;
            case R.id.iv_add_flow:
                DialogUtil dialogUtil = new DialogUtil();
                dialogUtil.showEditextBottom(this, getWindowManager(), new DialogUtil.IDialogEtCallBack() {
                    @Override
                    public void setPositiveButton(String positiveStr) {
                        mVals.add(positiveStr);
                        tagAdapter.notifyDataChanged();
                    }

                    @Override
                    public void setNegativeButton() {

                    }
                });
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
        if (resultCode == 2) {
            if (data != null) {
                AddressModel addressModel = (AddressModel) data.getExtras().getSerializable(Constant.RESULT);
                selectAddress=addressModel.getAddress();
                tvAddress.setText(addressModel.getAddress());
                createTaskModel.setAddress(addressModel.getAddress());
                createTaskModel.setLat((float)addressModel.getLatitude());
                createTaskModel.setLng((float)addressModel.getLongitude());
            }
        }
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
                                mimgVals.add(url);
                                imgAdapter.notifyDataChanged();
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

    @Override
    public void takeFail(TResult result, String msg) {
        /*//取得失败
        Toast.makeText(Setting.this,"设置失败",Toast.LENGTH_SHORT).show();*/
    }

    @Override
    public void takeCancel() {
        //取消
    }

}
