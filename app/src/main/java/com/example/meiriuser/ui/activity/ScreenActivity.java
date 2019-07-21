package com.example.meiriuser.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.meiriuser.R;
import com.example.meiriuser.base.BaseActivity;
import com.example.meiriuser.event.AddressSaveChangeEvent;
import com.example.meiriuser.event.BusProvider;
import com.example.meiriuser.model.AddressModel;
import com.example.meiriuser.until.Constant;
import com.example.meiriuser.widget.DoubleSlideSeekBar;

import butterknife.BindView;
import butterknife.OnClick;
import rx.functions.Action1;

/**
 * Created by admin on 2019/7/10.
 */

public class ScreenActivity extends BaseActivity {
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.tv_distance)
    TextView tvDistance;
    @BindView(R.id.progress_distance)
    SeekBar progressDistance;
    @BindView(R.id.tv_price_tasks)
    TextView tvPriceTasks;
  /*  @BindView(R.id.progress_price_tasks)
    DoubleSlideSeekBar progressPriceTasks;*/
    @BindView(R.id.btn_screen)
    Button btnScreen;
    String address;
    int distance;
    int price;
    AddressModel changeaddressModel;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_screen;
    }


    @Override
    public void initView() {
        super.initView();
        toolbarTitle.setText(getString(R.string.title_screen));
    }

    @Override
    public void initData() {
        super.initData();
    }

    @Override
    public void initListener() {
        super.initListener();
        tvDistance.setText(5+"km");
        tvPriceTasks.setText(String.format(getString(R.string.text_price),5+""));
        progressDistance.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                distance=progress+5;
                tvDistance.setText(distance+"km");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

       /* progressPriceTasks.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                price=progress+5;
                tvPriceTasks.setText(String.format(getString(R.string.text_price),price+""));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });*/

        BusProvider.getBus().toObservable(AddressSaveChangeEvent.class)
                .subscribe(new Action1<AddressSaveChangeEvent>() {
                    @Override
                    public void call(AddressSaveChangeEvent newMessageEvent) {
                        changeaddressModel=newMessageEvent.getTip();
                        tvAddress.setText(changeaddressModel.getAddress());
                    }
                });


    }

    @OnClick({R.id.icon_back, R.id.line_task_address, R.id.btn_screen})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.icon_back:
                finish();
                break;
            case R.id.line_task_address:
                Intent intent=new Intent(this,SearchAddressActivity.class);
                intent.putExtra(Constant.TYPE,"receiving");
                jumpToActivity(intent);
                break;
            case R.id.btn_screen:
                Intent intent1 = new Intent();
                //把返回数据存入Intent
                intent1.putExtra("address", changeaddressModel);
                intent1.putExtra("distance", distance);
                intent1.putExtra("price_tasks", price);
                //设置返回数据
                setResult(1, intent1);
                //关闭Activity
                finish();
                break;
        }
    }



}
