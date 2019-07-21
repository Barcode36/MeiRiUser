package com.example.meiriuser.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.example.meiriuser.R;
import com.example.meiriuser.base.BaseActivity;
import com.example.meiriuser.event.BusProvider;
import com.example.meiriuser.event.LoginRefreshEvent;
import com.example.meiriuser.event.OrderListRefresh;
import com.example.meiriuser.model.net.OrderNetModel;
import com.example.meiriuser.model.net.orderCommentModel;
import com.example.meiriuser.net.ApiUrl;
import com.example.meiriuser.until.Constant;
import com.example.meiriuser.until.PreferenceUtil;
import com.example.meiriuser.widget.GlideCircleTransform;
import com.example.meiriuser.widget.GlideRoundTransform;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by admin on 2019/5/23.
 */

public class EvaluateOrderActivity extends BaseActivity {
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_service_time)
    TextView tvServiceTime;
    @BindView(R.id.rb_rider_score)
    RatingBar rbRiderScore;
    @BindView(R.id.iv_logo)
    ImageView ivLogo;
    @BindView(R.id.tv_shop_name)
    TextView tvShopName;
    @BindView(R.id.tv_describe)
    TextView tvDescribe;
    @BindView(R.id.rb_shop_score)
    RatingBar rbShopScore;
    @BindView(R.id.et_remarks)
    EditText etRemarks;
    @BindView(R.id.tv_remarks_num)
    TextView tvRemarksNum;
    @BindView(R.id.btn_submit)
    Button btnSubmit;
    String type;
    @BindView(R.id.line_evaluate_rider)
    LinearLayout lineEvaluateRider;
    String order_id;
    int rbriderScore=0;
    int rbshopScore=0;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_evaluate_order;
    }

    @Override
    public void initView() {
        toolbarTitle.setText("评价");
        type = getIntent().getStringExtra(Constant.TYPE);
        if(type.equals("group")){
            lineEvaluateRider.setVisibility(View.GONE);
        }else {

        }
        order_id = getIntent().getExtras().getString("order_id");
        rbRiderScore.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                rbriderScore= (int) v;
            }
        });
        rbShopScore.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                rbshopScore= (int) v;
            }
        });
    }


    @Override
    public void initData() {
        Glide.with(this)
                .load(Constant.url)
                .transform(new CenterCrop(this), new GlideRoundTransform(this, 2))
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(ivLogo);
    }

    @Override
    public void initListener() {
        etRemarks.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String textChange = charSequence.toString();
                tvRemarksNum.setText(textChange.length() + "/100");
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }


    public void orderComment(final String id, String content,int rank,int rider_rank) {
        showDialog();
        String url = ApiUrl.orderCommentUrl;
        orderCommentModel orderCommentModel = new orderCommentModel(id,content, rank, rider_rank);
        Gson gson = new Gson();
        final String s = gson.toJson(orderCommentModel);
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
                String string = response.body().string();
                try {
                    JSONObject jsonObject = new JSONObject(string);
                    int result=jsonObject.getInt("result");
                    if(result==1){
                        Message message = new Message();
                        message.what =1;
                        myHandler.sendMessage(message);

                    }else {
                        String info= jsonObject.getString("info");
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
                    BusProvider.getBus().post(new LoginRefreshEvent());
                    finish();
                    break;
            }
            super.handleMessage(msg);
        }
    };


    @OnClick({R.id.icon_back, R.id.btn_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.icon_back:
                finish();
                break;
            case R.id.btn_submit:
                orderComment(order_id,etRemarks.getText().toString().trim(),rbriderScore,rbshopScore);
                break;
        }
    }


}
