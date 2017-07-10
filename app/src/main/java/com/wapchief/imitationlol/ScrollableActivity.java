package com.wapchief.imitationlol;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cpoopc.scrollablelayoutlib.ScrollableLayout;
import com.squareup.picasso.Picasso;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by apple on 2017/6/20.
 */

public class ScrollableActivity extends AppCompatActivity {


    @InjectView(R.id.header)
    Banner header;
    @InjectView(R.id.tab)
    TabLayout tab;
    @InjectView(R.id.vp)
    ViewPager vp;
    @InjectView(R.id.scrollablelayout)
    ScrollableLayout scrollablelayout;
    @InjectView(R.id.title)
    RelativeLayout title;
    @InjectView(R.id.title_bar_back)
    ImageView titleBarBack;
    @InjectView(R.id.title_bar_title)
    TextView titleBarTitle;
    @InjectView(R.id.title_bar_content)
    TextView titleBarContent;
    @InjectView(R.id.header_title)
    RelativeLayout headerTitle;
    @InjectView(R.id.srl)
    SwipeRefreshLayout srl;
    ScrollableFragment fragment;
    private String[] titles = new String[]{"最新", "专栏", "官方", "活动", "攻略", "娱乐", "收藏"};

    List<Object> img = new ArrayList<>();
    String url = "http://m.beequick.cn/static/bee/img/m/boot_logo-275a61e3.png";
    ViewPagerAdapter adapterVP;
    List<Fragment> fragmentList = new ArrayList<>();
    RelativeLayout relativeLayout;
    //动画
    TranslateAnimation mShowAction, mHiddenAction;
    View headerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrollable);
        ButterKnife.inject(this);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        initView();
    }

    private void initView() {
        initBanner();
        initTabLayout();
        initFragment();
        title.setBackgroundColor(Color.argb((int) 150, 0, 0, 0));
        titleBarTitle.setTextColor(Color.argb((int) 255, 198, 166, 102));
        titleBarContent.setTextColor(Color.argb((int) 255, 198, 166, 102));
        initOnClickScroll();
        initSwipeRefresh();
    }
    /*刷新监听*/
    private void initSwipeRefresh() {
        srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                srl.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        srl.setRefreshing(false);
                    }
                }, 2000);
            }
        });
    }

    /*滚动监听*/
    private void initOnClickScroll() {
        scrollablelayout.setOnScrollListener(new ScrollableLayout.OnScrollListener() {
            @Override
            public void onScroll(int i, int i1) {
                float scale = (float) i1 - i;
                float alpha = (255 * scale);
                float alpha2 = scale / i1 * 150;
                float alphaTv = scale / i1 * 250;
                float alpha3 = (float) i / i1 * 130;

                float alphaTop = (float) i / i1 * 150;
                LinearLayout.LayoutParams lp = new
                        LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);

                lp.setMargins(0, (int) alphaTop, 0, 0);

                tab.setLayoutParams(lp);
                if (i == i1) {
//                    title.setVisibility(View.GONE);
                    titleBarTitle.setText("动态列表");
                    titleBarTitle.setTextColor(Color.GREEN);
                } else if (i < i1) {
                    titleBarTitle.setText("英雄联盟");
                    titleBarTitle.setTextColor(Color.argb((int) 255, 198, 166, 102));
                }
                if (i < i1) {
                    title.setVisibility(View.VISIBLE);
                }


                //img设置渐变
                float f = (float) i / i1; //0-1递增
                float f1 = scale / i1;   //1-0递减
                titleBarBack.setAlpha(f1);

//                0-100递增偏移量
                titleBarBack.scrollTo((int) alpha3, 0);
                Log.e("aaa======", i + "    ,alpha:" + alpha + "     ,alpha2:" + alpha2 + "  ,alpha3:" + alpha3 + "    ,alphaTop:" + alphaTop);
//                titleBarBack.setPadding((int) scale / i1 * 100, 12, 0, 8);
                //通过距离设置渐变效果
                title.setBackgroundColor(Color.argb((int) alpha2, 0, 0, 0));
                titleBarTitle.setTextColor(Color.argb((int) alphaTv - 1, 198, 166, 102));
                titleBarContent.setTextColor(Color.argb((int) alphaTv, 198, 166, 102));

            }
        });
    }

    /*初始化Fragment*/
    private void initFragment() {

        fragment = new ScrollableFragment();
        ScrollableFragment fragment1 = new ScrollableFragment();
        ScrollableFragment fragment2 = new ScrollableFragment();
        ScrollableFragment fragment3 = new ScrollableFragment();
        ScrollableFragment fragment4 = new ScrollableFragment();
        ScrollableFragment fragment5 = new ScrollableFragment();
        ScrollableFragment fragment6 = new ScrollableFragment();
        fragmentList.add(fragment);
        fragmentList.add(fragment1);
        fragmentList.add(fragment2);
        fragmentList.add(fragment3);
        fragmentList.add(fragment4);
        fragmentList.add(fragment5);
        fragmentList.add(fragment6);
        adapterVP = new ViewPagerAdapter(getSupportFragmentManager());
        vp.setAdapter(adapterVP);
        tab.setupWithViewPager(vp);


    }

    /*通过Fragmenbt的滚动距离回调
    * 注意：这里只能处理当ScrollLayout完全隐藏后，Fragment的ScrollView才能开始滚动事件，
    * 上滑的时候却优先执行ScrollLayout的方法
    *
    * */

    public void getonScroll(int i) {
        Log.e("activity======I:", "i:" + i);
        srl.setEnabled(i<450);

        if (i > 700) {
            headerTitle.setAnimation(mHiddenAction);
            headerTitle.setVisibility(View.GONE);
        }
        if (i < 450) {
            headerTitle.setAnimation(mShowAction);
            headerTitle.setVisibility(View.VISIBLE);
        }
    }

    /*初始化tab标签*/
    private void initTabLayout() {

        for (int i = 0; i < titles.length; i++) {
            tab.addTab(tab.newTab().setText(titles[i]));
        }

    }

    /*轮播*/
    private void initBanner() {
        //圆形指示器
        header.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //指示器居中
        header.setIndicatorGravity(BannerConfig.CENTER);
        img.add("http://m.beequick.cn/static/bee/img/m/boot_logo-275a61e3.png");
        img.add("http://m.beequick.cn/static/bee/img/m/boot_logo-275a61e3.png");
        img.add("http://m.beequick.cn/static/bee/img/m/boot_logo-275a61e3.png");
        header.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object o, ImageView imageView) {
                Picasso.with(context)
                        .load(url)
                        .into(imageView);
            }
        });
        header.setImages(img);
        header.start();
    }

    @OnClick({R.id.title_bar_back, R.id.title_bar_content})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_bar_back:
                break;
            case R.id.title_bar_content:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
        }
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }
    }
}
