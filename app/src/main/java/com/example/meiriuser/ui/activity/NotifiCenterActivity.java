package com.example.meiriuser.ui.activity;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.meiriuser.R;
import com.example.meiriuser.adapter.NotifiCenterAdapter;
import com.example.meiriuser.base.BaseActivity;
import com.example.meiriuser.dialog.DialogUtil;
import com.example.meiriuser.dialog.IDialogCallBack;
import com.example.meiriuser.model.NotifiCenterModel;
import com.example.meiriuser.net.ApiUrl;
import com.example.meiriuser.until.Constant;
import com.example.meiriuser.until.GsonUtil;
import com.example.meiriuser.until.PreferenceUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * Created by admin on 2019/5/30.
 */

public class NotifiCenterActivity extends BaseActivity {
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    List<NotifiCenterModel.DataBeanX.DataBean> notifiCenterModelList;
    NotifiCenterAdapter centerAdapter;
    String token;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    int pageNo = Constant.PAGENO;

    @Override
    protected int provideContentViewId() {
        return R.layout.title_recyclerview_layout_refresh;
    }


    @Override
    public void initView() {
        token = PreferenceUtil.getString(Constant.PF_TOKEN_KEY);
        toolbarTitle.setText("通知中心");
        notifiCenterModelList = new ArrayList<>();
        rvList.setLayoutManager(new LinearLayoutManager(this));
   /*     notifiCenterModelList.add(new NotifiCenterModel("提现成功通知", "2018-3-8", "您在2019/3/8的操作提现已到账,立即查看详情"));
        notifiCenterModelList.add(new NotifiCenterModel("提现成功通知", "2018-12-28", "您在2019/3/8的操作提现已到账,立即查看详情"));*/
    }


    @Override
    public void initData() {
        centerAdapter = new NotifiCenterAdapter(notifiCenterModelList);
        rvList.setAdapter(centerAdapter);
        getNoticeList(token,pageNo);
    }


    /**
     * 获取通知列表 (未读状态)
     */
    public void getNoticeList(String token, int pageNo) {

        showDialog();
        String url = ApiUrl.noticeListUrl;
        OkHttpUtils
                .get()
                .url(url)
                .addHeader("token", token)
                .addParams("page", String.valueOf(pageNo))
                .addParams("size", Constant.MAXCOUNT)
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
                            int result=jsonObject.getInt("result");
                            if(result==1){
                                NotifiCenterModel bean= (NotifiCenterModel) GsonUtil.JSONToObject(response.toString(),NotifiCenterModel.class);
                                notifiCenterModelList.addAll(bean.getData().getData());
                                centerAdapter.notifyDataSetChanged();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });

    }



    /**
     * 删除通知
     */
    public void deleteNotice(String token, int push_id, final int pos) {

        showDialog();
        String url = ApiUrl.noticeListUrl;
        OkHttpUtils
                .get()
                .url(url)
                .addHeader("token", token)
                .addParams("push_id", push_id+"")
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
                            int result=jsonObject.getInt("result");
                            if(result==1){
                                ToastUtils.showShort(getString(R.string.toast_delete_success));
                                notifiCenterModelList.remove(pos);
                                centerAdapter.notifyDataSetChanged();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });

    }

    @OnClick(R.id.icon_back)
    public void onViewClicked() {
        finish();
    }


    @Override
    public void initListener() {
        super.initListener();


        centerAdapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
                PopupWindow popupWindow = showPopupWindowDelete(NotifiCenterActivity.this, R.layout.popupwindow_delete,position);
                showPopupWindow(popupWindow, view);
                return true;
            }
        });


        setRefresh();
    }

    public void setRefresh() {
        refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                refreshLayout.finishLoadMore(2000);
                pageNo++;
                getNoticeList(token, pageNo);
            }

            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                refreshLayout.finishRefresh(2000);
                if (pageNo > Constant.PAGENO) {
                    pageNo--;
                    if (pageNo == Constant.PAGENO) {
                        notifiCenterModelList.clear();
                        centerAdapter.notifyDataSetChanged();
                    }
                    getNoticeList(token, pageNo);
                }
            }
        });


    }

    public PopupWindow showPopupWindowDelete(final Context context, int layoutId, final int pos) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final PopupWindow popupWindow = new PopupWindow();
        View conentView = inflater.inflate(layoutId, null);
        // 设置SelectPicPopupWindow的View
        popupWindow.setContentView(conentView);
        // 设置SelectPicPopupWindow弹出窗体的宽
        popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        // 设置SelectPicPopupWindow弹出窗体的高
        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        // 需要设置一下此参数，点击外边可消失
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        // 设置点击窗口外边窗口消失    这两步用于点击手机的返回键的时候，不是直接关闭activity,而是关闭pop框
        popupWindow.setOutsideTouchable(true);

        LinearLayout lineDetele = conentView.findViewById(R.id.line_delete);
        lineDetele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogUtil dialogUtil =new DialogUtil();
                dialogUtil.showSimpleDialogOKCancel(NotifiCenterActivity.this, getWindowManager(), getString(R.string.dialog_title_tip), getString(R.string.dialog_sure_delete), new IDialogCallBack() {
                    @Override
                    public void setPositiveButton() {
                        deleteNotice(token,notifiCenterModelList.get(pos).getId(),pos);
                    }

                    @Override
                    public void setNegativeButton() {

                    }
                });
                popupWindow.dismiss();
            }
        });

        return popupWindow;
    }

    public void showPopupWindow(PopupWindow popupWindow, View parent) {
        // 以下拉方式显示popupwindow
        if (!popupWindow.isShowing()) {
            popupWindow.showAsDropDown(parent);
        } else {
            popupWindow.dismiss();
        }
    }

}
