package com.yichan.pea.order.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yichan.pea.R;
import com.yichan.pea.basic.view.TakePhotoPopWin;
import com.yichan.pea.basic.view.dialog.BottomDialog;
import com.yichan.pea.basic.view.dialog.SelectDialog;
import com.yichan.pea.home.activity.nolicense.NoSecActivity;
import com.yichan.pea.home.activity.nolicense.NoThirdActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrderQueryActivity extends AppCompatActivity {
    @BindView(R.id.top_left)
    FrameLayout back;
    @BindView(R.id.top_title)
    TextView title;
    @BindView(R.id.top_right)
    TextView right;
    @BindView(R.id.date)
    ImageView date;
    @BindView(R.id.tv_date)
    TextView sel_date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_query);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        back.setVisibility(View.VISIBLE);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        title.setText("全部订单");
        right.setText("筛选");
        right.setVisibility(View.VISIBLE);
        title.setVisibility(View.VISIBLE);
        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });
        right.setTextColor(getResources().getColor(R.color.tv_333));
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timeSelectDialog();
            }
        });
    }
    private void timeSelectDialog(){
        BottomDialog dialog=new BottomDialog(this);
        dialog.show();
        dialog.setOnPayItemSelect(new TakePhotoPopWin.OnPayItemSelect() {
            @Override
            public void onSelect(String type) {
                    String[] date=type.split(",");
                    sel_date.setText(date[0]+"年"+date[1]+"月");
            }
        });
    }
    private void showDialog() {
        SelectDialog selectDialog = new SelectDialog(OrderQueryActivity.this);
        selectDialog.setCancelable(false).show();
        selectDialog.setOnPayItemSelect(new TakePhotoPopWin.OnPayItemSelect() {
            @Override
            public void onSelect(String type) {

            }
        });
//        TakePhotoPopWin takePhotoPopWin = new TakePhotoPopWin(OrderQueryActivity.this);
//        takePhotoPopWin.showAtLocation(findViewById(R.id.main_view), Gravity.BOTTOM, 0, 0);
//        takePhotoPopWin.setWindowBackgroundAlpha(0.5f);
//        takePhotoPopWin.setOutsideTouchable(false);
//        takePhotoPopWin.setOnPayItemSelect(new TakePhotoPopWin.OnPayItemSelect() {
//            @Override
//            public void onSelect(String type) {
//                takePhotoPopWin.setWindowBackgroundAlpha(1f);
//                title.setText(type);
//            }
//        });
    }


}
