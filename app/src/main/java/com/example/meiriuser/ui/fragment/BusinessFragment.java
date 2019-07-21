package com.example.meiriuser.ui.fragment;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.meiriuser.R;
import com.example.meiriuser.adapter.CardFragmentPagerAdapter;
import com.example.meiriuser.adapter.CardPagerAdapter;
import com.example.meiriuser.base.BaseFragment;
import com.example.meiriuser.model.CardItem;
import com.example.meiriuser.model.ShopInfoModel;
import com.example.meiriuser.until.Constant;
import com.example.meiriuser.widget.ShadowTransformer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 商家
 */

public class BusinessFragment extends BaseFragment {
    @BindView(R.id.tv_business_science)
    TextView tvBusinessScience;
    Dialog dialog;
    @BindView(R.id.tv_business_hours)
    TextView tvBusinessHours;
    @BindView(R.id.tv_business_address)
    TextView tvBusinessAddress;


    public void setInfo(ShopInfoModel.ShopBean dataBean) {
        if (dataBean != null) {
            tvBusinessAddress.setText(dataBean.getAddress() == null ? "" : dataBean.getAddress());
            tvBusinessHours.setText(String.format(getString(R.string.text_business_hours), dataBean.getYstartime() == null ? "" : dataBean.getYstartime()) + "-" + dataBean.getYendtime() == null ? "" : dataBean.getYendtime());
        }
    }


    @Override
    protected int provideContentViewId() {
        return R.layout.fragemnt_business;
    }


    public void showViewPageOKCancel() {

        LayoutInflater inflaterDl = LayoutInflater.from(getContext());
        WindowManager wm = (WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE);
        final int mScreenWidth = wm.getDefaultDisplay().getWidth();
        final int mScreenHeight = wm.getDefaultDisplay().getHeight();
        final View layout = (LinearLayout) inflaterDl.inflate(R.layout.dialog_viewpage, null);
        dialog = new Dialog(getContext(), R.style.dialog_no_bg) {

            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(layout);
                getWindow().setLayout(mScreenWidth, mScreenHeight);
                init();
            }

            private void init() {
                ViewPager mViewPager = (ViewPager) findViewById(R.id.viewPager);
                CardPagerAdapter mCardAdapter = new CardPagerAdapter(getContext(), new CardPagerAdapter.onItemClickListener() {
                    @Override
                    public void onItemClick() {
                        dialog.dismiss();
                    }
                });
                mCardAdapter.addCardItem(new CardItem("https://gss0.bdstatic.com/94o3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike92%2C5%2C5%2C92%2C30/sign=1aa714bafad3572c72ef948eeb7a0842/77c6a7efce1b9d16ed8cd932fbdeb48f8c54641d.jpg"));
                mCardAdapter.addCardItem(new CardItem("https://gss0.bdstatic.com/94o3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike92%2C5%2C5%2C92%2C30/sign=1aa714bafad3572c72ef948eeb7a0842/77c6a7efce1b9d16ed8cd932fbdeb48f8c54641d.jpg"));
                mCardAdapter.addCardItem(new CardItem(Constant.url));
                mCardAdapter.addCardItem(new CardItem(Constant.url));
                CardFragmentPagerAdapter mFragmentCardAdapter = new CardFragmentPagerAdapter(getChildFragmentManager(),
                        dpToPixels(2, getContext()));
                ShadowTransformer mCardShadowTransformer = new ShadowTransformer(mViewPager, mCardAdapter);
                ShadowTransformer mFragmentCardShadowTransformer = new ShadowTransformer(mViewPager, mFragmentCardAdapter);
                mViewPager.setAdapter(mCardAdapter);
                mViewPager.setPageTransformer(false, mCardShadowTransformer);
                mViewPager.setOffscreenPageLimit(3);
            }
        };
        dialog.show();

    }


    public static float dpToPixels(int dp, Context context) {
        return dp * (context.getResources().getDisplayMetrics().density);
    }

    @Override
    public void initData() {
        super.initData();
    }


    @OnClick(R.id.tv_business_science)
    public void onViewClicked() {
        showViewPageOKCancel();
    }


}
