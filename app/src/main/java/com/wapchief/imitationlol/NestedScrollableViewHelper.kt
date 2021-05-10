package com.wapchief.imitationlol

import android.view.View
import androidx.core.widget.NestedScrollView
import com.sothree.slidinguppanel.ScrollableViewHelper

/**
 * Created by apple on 2017/6/28.
 */
class NestedScrollableViewHelper : ScrollableViewHelper() {
    override fun getScrollableViewScrollPosition(scrollableView: View, isSlidingUp: Boolean): Int {
        val mScrollableView: View? = null
        return if (mScrollableView is NestedScrollView) {
            if (isSlidingUp) {
                mScrollableView.getScrollY()
            } else {
                val nsv = mScrollableView
                val child = nsv.getChildAt(0)
                child.bottom - (nsv.height + nsv.scrollY)
            }
        } else {
            0
        }
    }
}