package com.wapchief.imitationlol;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
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
    private String[] titles = new String[]{"最新", "专栏", "官方", "活动", "攻略", "娱乐", "收藏"};

    List<Object> img = new ArrayList<>();
    String url = "http://m.beequick.cn/static/bee/img/m/boot_logo-275a61e3.png";
    ViewPagerAdapter adapterVP;
    List<Fragment> fragmentList = new ArrayList<>();

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
        initOnClickScroll();
    }

    /*滚动监听*/
    private void initOnClickScroll() {
        scrollablelayout.setOnScrollListener(new ScrollableLayout.OnScrollListener() {
            @Override
            public void onScroll(int i, int i1) {
                if (i >= 600) {
                    title.setVisibility(View.GONE);
                } else {
                    title.setVisibility(View.VISIBLE);
                }
                //通过距离设置渐变效果
                float scale = (float) i1-i;
                float alpha = (255 * scale);
                float alpha2 = scale/i1*150;
                float alphaTv = scale / i1 * 250;
                title.setBackgroundColor(Color.argb((int) alpha2, 0, 0, 0));
                titleBarTitle.setTextColor(Color.argb((int) alphaTv, 198, 166, 102));
                titleBarContent.setTextColor(Color.argb((int) alphaTv,198,166,102));
            }
        });
    }

    /*初始化Fragment*/
    private void initFragment() {

        ScrollableFragment fragment = new ScrollableFragment();
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
        scrollablelayout.getHelper().setCurrentScrollableContainer(fragment3);
        adapterVP = new ViewPagerAdapter(getSupportFragmentManager());
        vp.setAdapter(adapterVP);
        tab.setupWithViewPager(vp);


    }


    /*初始化tab标签*/
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
