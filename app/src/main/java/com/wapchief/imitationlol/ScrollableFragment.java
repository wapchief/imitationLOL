package com.wapchief.imitationlol;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bartoszlipinski.recyclerviewheader2.RecyclerViewHeader;
import com.cpoopc.scrollablelayoutlib.ScrollableHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by apple on 2017/6/20.
 */

public class ScrollableFragment extends Fragment implements ScrollableHelper.ScrollableContainer {

    @InjectView(R.id.header)
    RecyclerViewHeader header;
    @InjectView(R.id.recyclerView)
    RecyclerView recyclerView;
    private View view;
    List<MainBean> data = new ArrayList<>();
    MainBean bean;
    FragmentAdapter adapter;

    @Override
    public View getScrollableView() {
        return view;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        view = View.inflate(getActivity(), R.layout.fragment_listview, null);
        ButterKnife.inject(this, view);
        initView();
        return view;
    }

    private void initView() {
        initAdapter();
        initData();

    }

    private void initData() {

        for (int i = 0; i < 10; i++) {
            data.add(i, bean);
        }
        adapter.notifyDataSetChanged();
    }

    private void initAdapter() {
        View headerView = View.inflate(getContext(), R.layout.view_header, null);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        header.addView(headerView);
        header.attachTo(recyclerView);
        adapter = new FragmentAdapter(data, getActivity());
        //分割线
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(adapter);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
