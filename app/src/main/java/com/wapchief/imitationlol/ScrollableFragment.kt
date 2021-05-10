package com.wapchief.imitationlol

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cpoopc.scrollablelayoutlib.ScrollableHelper.ScrollableContainer
import kotlinx.android.synthetic.main.fragment_listview.*
import java.util.*

/**
 * Created by apple on 2017/6/20.
 */
class ScrollableFragment : Fragment(), ScrollableContainer {

//    var view: View
    var data: MutableList<MainBean?> = ArrayList()
    var bean: MainBean? = null
    var adapter: FragmentAdapter? = null
    var relativeLayout: RelativeLayout? = null
    var activity: ScrollableActivity? = null
    override fun getScrollableView(): View {
        return view!!
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // TODO: inflate a fragment view
        activity = getActivity() as ScrollableActivity?
        return View.inflate(getActivity(), R.layout.fragment_listview, null)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initView()
    }

    private fun initView() {
        initAdapter()
        initData()
        initonScroll()
    }

    private fun initonScroll() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            scrollView!!.setOnScrollChangeListener { view, i, i1, i2, i3 ->
                Log.e("i======", "i:$i,i1:$i1,i2:$i2,i3:$i3")
                if (i1 > 300) {
                    activity!!.getonScroll(i1)
                }
            }
        }
        //这里获取不到recycler的监听事件，因为ScrollView冲突问题，在布局将滚动焦点交给了ScrollView处理
        recyclerViewF!!.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                Log.e("d======", "$dx,$dy")
            }
        })
    }

    private fun initData() {
        for (i in 0..9) {
            data.add(i,bean)
        }
        adapter!!.notifyDataSetChanged()
    }

    private fun initAdapter() {
        val headerView = View.inflate(context, R.layout.view_header, null)
        relativeLayout = headerView.findViewById<View>(R.id.header_title) as RelativeLayout
        val layoutManager = LinearLayoutManager(getActivity())
        recyclerViewF!!.layoutManager = layoutManager
        header.addView(headerView);
        header.attachTo(recyclerViewF);
        adapter = getActivity()?.let { FragmentAdapter(data, it) }
        //分割线
        recyclerViewF!!.addItemDecoration(DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL))
        recyclerViewF!!.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}