package com.wapchief.imitationlol;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity {

    @InjectView(R.id.meituan_bt)
    Button meituanBt;
    @InjectView(R.id.meituan_bt2)
    Button meituanBt2;

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

    }


    @OnClick({R.id.meituan_bt, R.id.meituan_bt2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.meituan_bt:
                Intent intent = new Intent(this, MeiTuanOrderActivity.class);
                startActivity(intent);
                break;
            case R.id.meituan_bt2:
                break;
        }
    }
}
