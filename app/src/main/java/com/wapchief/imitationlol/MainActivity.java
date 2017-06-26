package com.wapchief.imitationlol;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.squareup.picasso.Picasso;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity {


    @InjectView(R.id.banner)
    Banner banner;
    @InjectView(R.id.tab)
    TabLayout tab;
    @InjectView(R.id.title_bar_back)
    ImageView titleBarBack;
    @InjectView(R.id.title_bar_title)
    TextView titleBarTitle;
    @InjectView(R.id.title_bar_content)
    TextView titleBarContent;
    @InjectView(R.id.refresh)
    MaterialRefreshLayout refresh;
    private String[] titles = new String[]{"最新", "专栏", "官方", "活动", "攻略", "娱乐", "收藏"};

    List<Object> img = new ArrayList<>();
    String url = "http://m.beequick.cn/static/bee/img/m/boot_logo-275a61e3.png";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        //透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        initView();
    }

    private void initView() {
        initBanner();
        initActionBar();
        initTabLayout();
        refreshOnclick();
    }

    private void initActionBar() {
        setActionBarLayout(R.layout.title_bar);
            }

    /*刷新监听*/
    private void refreshOnclick() {
        refresh.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                refresh.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                    }
                }, 1000);
            }

            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                super.onRefreshLoadMore(materialRefreshLayout);
            }
        });
        refresh.setIsOverLay(false);
        refresh.setWaveShow(false);
    }

    /*tab标签*/
    private void initTabLayout() {
        tab.addTab(tab.newTab().setText(titles[0]));
        tab.addTab(tab.newTab().setText(titles[1]));
        tab.addTab(tab.newTab().setText(titles[2]));
        tab.addTab(tab.newTab().setText(titles[3]));
        tab.addTab(tab.newTab().setText(titles[4]));
        tab.addTab(tab.newTab().setText(titles[5]));
        tab.addTab(tab.newTab().setText(titles[6]));


    }


    /*轮播*/
    private void initBanner() {
        //圆形指示器
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //指示器居中
        banner.setIndicatorGravity(BannerConfig.CENTER);
        img.add("http://m.beequick.cn/static/bee/img/m/boot_logo-275a61e3.png");
        img.add("http://m.beequick.cn/static/bee/img/m/boot_logo-275a61e3.png");
        img.add("http://m.beequick.cn/static/bee/img/m/boot_logo-275a61e3.png");
        banner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object o, ImageView imageView) {
                Picasso.with(context)
                        .load(url)
                        .into(imageView);
            }
        });
        banner.setImages(img);
        banner.start();
    }


    @OnClick({R.id.title_bar_back, R.id.title_bar_title, R.id.title_bar_content})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_bar_back:
                Intent intent = new Intent(this, ScrollableActivity.class);
                startActivity(intent);
                break;
            case R.id.title_bar_title:
                break;
            case R.id.title_bar_content:
                refresh.finishRefresh();
                break;
        }
    }

    private void setActionBarLayout(int layoutId) {
        // TODO Auto-generated method stub
        ActionBar actionBar = getSupportActionBar();
        if(null != actionBar){
            actionBar.setDisplayShowHomeEnabled(false);
            actionBar.setDisplayShowCustomEnabled(true);

            LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View v = inflater.inflate(layoutId, null);
            ActionBar.LayoutParams lParams = new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
            actionBar.setCustomView(v, lParams);
        }

    }
}
