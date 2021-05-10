package com.wapchief.imitationlol

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.view.animation.TranslateAnimation
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.cpoopc.scrollablelayoutlib.ScrollableLayout
import com.squareup.picasso.Picasso
import com.youth.banner.Banner
import com.youth.banner.BannerConfig
import com.youth.banner.loader.ImageLoader
import kotlinx.android.synthetic.main.activity_scrollable.*
import kotlinx.android.synthetic.main.title_bar.*
import java.util.*

/**
 * Created by apple on 2017/6/20.
 */
class ScrollableActivity : AppCompatActivity() {
    var fragment: ScrollableFragment? = null
    private val titles = arrayOf("最新", "专栏", "官方", "活动", "攻略", "娱乐", "收藏")
    var img: MutableList<Any?> = ArrayList()
    var url = "http://m.beequick.cn/static/bee/img/m/boot_logo-275a61e3.png"
    var adapterVP: ViewPagerAdapter? = null
    var fragmentList: MutableList<Fragment> = ArrayList()
    var relativeLayout: RelativeLayout? = null

    //动画
    var mShowAction: TranslateAnimation? = null
    var mHiddenAction: TranslateAnimation? = null
    var headerView: View? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scrollable)
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        initView()
    }

    private fun initView() {
        initBanner()
        initTabLayout()
        initFragment()
        titleR.setBackgroundColor(Color.argb(150, 0, 0, 0))
        titleBarTitle.setTextColor(Color.argb(255, 198, 166, 102))
        titleBarContent.setTextColor(Color.argb(255, 198, 166, 102))
        initOnClickScroll()
        initSwipeRefresh()
    }

    /*刷新监听*/
    private fun initSwipeRefresh() {
//        srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                srl.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        srl.setRefreshing(false);
//                    }
//                }, 2000);
//            }
//        });
    }

    /*滚动监听*/
    private fun initOnClickScroll() {
        scrollablelayout.setOnScrollListener(ScrollableLayout.OnScrollListener { i, i1 ->
            val scale = i1.toFloat() - i
            val alpha = 255 * scale
            val alpha2 = scale / i1 * 150
            val alphaTv = scale / i1 * 250
            val alpha3 = i.toFloat() / i1 * 130
            val alphaTop = i.toFloat() / i1 * 150
            val lp = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT)
            lp.setMargins(0, alphaTop.toInt(), 0, 0)
            tab.setLayoutParams(lp)
            if (i == i1) {
//                    title.setVisibility(View.GONE);
                titleBarTitle.setText("动态列表")
                titleBarTitle.setTextColor(Color.GREEN)
            } else if (i < i1) {
                titleBarTitle.setText("英雄联盟")
                titleBarTitle.setTextColor(Color.argb(255, 198, 166, 102))
            }
            if (i < i1) {
                titleR.setVisibility(View.VISIBLE)
            }


            //img设置渐变
            val f = i.toFloat() / i1 //0-1递增
            val f1 = scale / i1 //1-0递减
            titleBarBack.setAlpha(f1)

//                0-100递增偏移量
            titleBarBack.scrollTo(alpha3.toInt(), 0)
            Log.e("aaa======", "$i    ,alpha:$alpha     ,alpha2:$alpha2  ,alpha3:$alpha3    ,alphaTop:$alphaTop")
            //                titleBarBack.setPadding((int) scale / i1 * 100, 12, 0, 8);
            //通过距离设置渐变效果
            titleR.setBackgroundColor(Color.argb(alpha2.toInt(), 0, 0, 0))
            titleBarTitle.setTextColor(Color.argb(alphaTv.toInt() - 1, 198, 166, 102))
            titleBarContent.setTextColor(Color.argb(alphaTv.toInt(), 198, 166, 102))
        })
    }

    /*初始化Fragment*/
    private fun initFragment() {
        fragment = ScrollableFragment()
        val fragment1 = ScrollableFragment()
        val fragment2 = ScrollableFragment()
        val fragment3 = ScrollableFragment()
        val fragment4 = ScrollableFragment()
        val fragment5 = ScrollableFragment()
        val fragment6 = ScrollableFragment()
        fragmentList.add(fragment!!)
        fragmentList.add(fragment1)
        fragmentList.add(fragment2)
        fragmentList.add(fragment3)
        fragmentList.add(fragment4)
        fragmentList.add(fragment5)
        fragmentList.add(fragment6)
        adapterVP = ViewPagerAdapter(supportFragmentManager)
        vp.setAdapter(adapterVP)
        tab.setupWithViewPager(vp)
    }

    /*通过Fragmenbt的滚动距离回调
    * 注意：这里只能处理当ScrollLayout完全隐藏后，Fragment的ScrollView才能开始滚动事件，
    * 上滑的时候却优先执行ScrollLayout的方法
     */
    fun getonScroll(i: Int) {
        Log.e("activity======I:", "i:$i")
        //        srl.setEnabled(i<450);
        if (i > 700) {
            headerTitle.setAnimation(mHiddenAction)
            headerTitle.setVisibility(View.GONE)
        }
        if (i < 450) {
            headerTitle.setAnimation(mShowAction)
            headerTitle.setVisibility(View.VISIBLE)
        }
    }

    /*初始化tab标签*/
    private fun initTabLayout() {
        for (i in titles.indices) {
            /* tab.addTab(tab.newTab().setText(titles[i])); */
        }
    }

    /*轮播*/
    private fun initBanner() {
        //圆形指示器
        banner!!.setBannerStyle(BannerConfig.CIRCLE_INDICATOR)
        //指示器居中
        banner!!.setIndicatorGravity(BannerConfig.CENTER)
        img.add("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2933732642,3589841831&fm=26&gp=0.jpg")
        //        img.add("http://m.beequick.cn/static/bee/img/m/boot_logo-275a61e3.png");
//        img.add("http://m.beequick.cn/static/bee/img/m/boot_logo-275a61e3.png");
        banner!!.setImageLoader(object : ImageLoader() {
            override fun displayImage(context: Context, o: Any, imageView: ImageView) {
                Picasso.with(context)
                        .load(url)
                        .into(imageView)
            }
        })
        banner!!.setImages(img)
        banner!!.start()
    }

    /*    OnClick({R.id.title_bar_back, R.id.title_bar_content})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_bar_back:
                break;
            case R.id.title_bar_content:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
        }
    }*/
    inner class ViewPagerAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm) {
        override fun getItem(position: Int): Fragment {
            return fragmentList[position]
        }

        override fun getCount(): Int {
            return fragmentList.size
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return titles[position]
        }
    }
}