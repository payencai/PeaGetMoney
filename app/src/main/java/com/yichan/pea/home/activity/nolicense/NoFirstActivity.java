package com.yichan.pea.home.activity.nolicense;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.yichan.pea.R;
import com.yichan.pea.home.activity.selfshop.SelfFirstActivity;
import com.yichan.pea.home.activity.selfshop.SelfSecActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NoFirstActivity extends AppCompatActivity {
    @BindView(R.id.top_left)
    FrameLayout back;
    @BindView(R.id.top_title)
    TextView title;
    @BindView(R.id.top_right)
    TextView right;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_first);
        ButterKnife.bind(this);
        initView();
    }
    private void initView(){
        back.setVisibility(View.VISIBLE);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        title.setText("无证商户");
        right.setVisibility(View.VISIBLE);
        title.setVisibility(View.VISIBLE);
        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(NoFirstActivity.this,NoSecActivity.class));
            }
        });
        right.setTextColor(getResources().getColor(R.color.tv_green));
    }
}
