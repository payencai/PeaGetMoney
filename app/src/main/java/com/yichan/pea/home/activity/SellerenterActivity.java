package com.yichan.pea.home.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.bumptech.glide.request.target.ThumbnailImageViewTarget;
import com.payencai.library.util.ToastUtil;
import com.yichan.pea.R;
import com.yichan.pea.home.activity.enterprise.EnterpriseFirstActivity;
import com.yichan.pea.home.activity.nolicense.NoFirstActivity;
import com.yichan.pea.home.activity.selfshop.SelfFirstActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SellerenterActivity extends AppCompatActivity {
    @BindView(R.id.top_left)
    FrameLayout back;
    @BindView(R.id.top_right)
    TextView right;
    @BindView(R.id.top_title)
    TextView title;
    @BindView(R.id.nolicense)
    TextView nolicense;
    @BindView(R.id.selfsell)
    TextView selfsell;
    @BindView(R.id.enterprise)
    TextView enterprise;
    int flag = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pea_activity_sellerenter);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        back.setVisibility(View.VISIBLE);
        right.setVisibility(View.VISIBLE);
        title.setVisibility(View.VISIBLE);
        title.setText("入驻");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (flag == -1) {
                    ToastUtil.showToast(SellerenterActivity.this, "请选择商户类型！");
                    return;
                }
                switch (flag) {
                    case 0:
                        startActivity(new Intent(SellerenterActivity.this, NoFirstActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(SellerenterActivity.this, SelfFirstActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(SellerenterActivity.this, EnterpriseFirstActivity.class));
                        break;
                    default:
                        break;

                }
            }
        });
        right.setTextColor(getResources().getColor(R.color.tv_999));
        title.setText("入驻");
        nolicense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag = 0;
                select(0);

            }
        });
        selfsell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag = 1;
                select(1);
            }
        });
        enterprise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag = 2;
                select(2);
            }
        });
    }

    private void select(int position) {
        right.setTextColor(getResources().getColor(R.color.tv_green));
        switch (position) {
            case 0:
                nolicense.setTextColor(getResources().getColor(R.color.tv_green));
                selfsell.setTextColor(getResources().getColor(R.color.tv_999));
                enterprise.setTextColor(getResources().getColor(R.color.tv_999));
                nolicense.setBackgroundResource(R.drawable.shape_solid_green2);
                selfsell.setBackgroundResource(R.drawable.shape_solid_999);
                enterprise.setBackgroundResource(R.drawable.shape_solid_999);

                break;
            case 1:
                selfsell.setTextColor(getResources().getColor(R.color.tv_green));
                nolicense.setTextColor(getResources().getColor(R.color.tv_999));
                enterprise.setTextColor(getResources().getColor(R.color.tv_999));

                nolicense.setBackgroundResource(R.drawable.shape_solid_999);
                selfsell.setBackgroundResource(R.drawable.shape_solid_green2);
                enterprise.setBackgroundResource(R.drawable.shape_solid_999);
                break;
            case 2:
                nolicense.setTextColor(getResources().getColor(R.color.tv_999));
                selfsell.setTextColor(getResources().getColor(R.color.tv_999));
                enterprise.setTextColor(getResources().getColor(R.color.tv_green));
                nolicense.setBackgroundResource(R.drawable.shape_solid_999);
                selfsell.setBackgroundResource(R.drawable.shape_solid_999);
                enterprise.setBackgroundResource(R.drawable.shape_solid_green2);
                break;
            default:
                break;
        }
    }
}
