package com.wapchief.imitationlol;

import android.support.v4.widget.NestedScrollView;
import android.view.View;
import android.widget.ScrollView;

import com.sothree.slidinguppanel.ScrollableViewHelper;

/**
 * Created by apple on 2017/6/28.
 */

public class NestedScrollableViewHelper extends ScrollableViewHelper {
    public int getScrollableViewScrollPosition(View scrollableView, boolean isSlidingUp) {
        View mScrollableView = null;
        if (mScrollableView instanceof NestedScrollView) {
            if(isSlidingUp){
                return mScrollableView.getScrollY();
            } else {
                NestedScrollView nsv = ((NestedScrollView) mScrollableView);
                View child = nsv.getChildAt(0);
                return (child.getBottom() - (nsv.getHeight() + nsv.getScrollY()));
            }
        } else {
            return 0;
        }
    }
}
