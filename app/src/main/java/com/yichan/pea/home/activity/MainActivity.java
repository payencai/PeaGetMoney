package com.yichan.pea.home.activity;

import android.media.Image;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.payencai.library.view.BottomBar;
import com.yichan.pea.R;
import com.yichan.pea.home.fragment.HomeFragment;
import com.yichan.pea.mine.fragment.MineFragment;
import com.yichan.pea.order.fragment.OrderFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    private Fragment[] mFragmensts;
    @BindView(R.id.bottom_tab_layout)
    LinearLayout bottom;
    @BindView(R.id.ll_home)
    LinearLayout ll_home;
    @BindView(R.id.ll_order)
    LinearLayout ll_order;
    @BindView(R.id.ll_mine)
    LinearLayout ll_mine;
    @BindView(R.id.tv_home)
    TextView tv_home;
    @BindView(R.id.tv_order)
    TextView tv_order;
    @BindView(R.id.tv_mine)
    TextView tv_mine;
    @BindView(R.id.iv_home)
    ImageView iv_home;
    @BindView(R.id.iv_order)
    ImageView iv_order;
    @BindView(R.id.iv_mine)
    ImageView iv_mine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mFragmensts = getFragments("from");
        addFragments();
        onTabItemSelected(0);
        initView();
    }

    private void onSelectTab(int position) {
        switch (position) {
            case 0:
                iv_home.setImageResource(R.mipmap.home);
                iv_order.setImageResource(R.mipmap.order);
                iv_mine.setImageResource(R.mipmap.me_tab_me_normal);
                tv_home.setTextColor(getResources().getColor(R.color.tv_green));
                tv_mine.setTextColor(getResources().getColor(R.color.tv_999));
                tv_order.setTextColor(getResources().getColor(R.color.tv_999));
                break;
            case 1:
                iv_home.setImageResource(R.mipmap.normal_home);
                iv_order.setImageResource(R.mipmap.select_order);
                iv_mine.setImageResource(R.mipmap.me_tab_me_normal);
                tv_home.setTextColor(getResources().getColor(R.color.tv_999));
                tv_mine.setTextColor(getResources().getColor(R.color.tv_999));
                tv_order.setTextColor(getResources().getColor(R.color.tv_green));
                break;
            case 2:
                iv_home.setImageResource(R.mipmap.normal_home);
                iv_order.setImageResource(R.mipmap.order);
                iv_mine.setImageResource(R.mipmap.me_tab_me_pressed);
                tv_home.setTextColor(getResources().getColor(R.color.tv_999));
                tv_mine.setTextColor(getResources().getColor(R.color.tv_green));
                tv_order.setTextColor(getResources().getColor(R.color.tv_999));
                break;
            default:
                break;
        }
    }

    private void initView() {
        ll_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onTabItemSelected(0);
            }
        });
        ll_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onTabItemSelected(1);
            }
        });
        ll_mine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onTabItemSelected(2);
            }
        });
    }

    public static Fragment[] getFragments(String from) {
        Fragment fragments[] = new Fragment[3];
        fragments[0] = HomeFragment.newInstance(from);
        fragments[1] = OrderFragment.newInstance(from);
        fragments[2] = MineFragment.newInstance(from);
        return fragments;
    }

    public void onTabItemSelected(int position) {
        onSelectTab(position);
        switch (position) {
            case 0:
                hideFragment(2);
                hideFragment(1);
                showFragment(0);
                break;
            case 1:
                hideFragment(2);
                hideFragment(0);
                showFragment(1);
                break;
            case 2:
                hideFragment(1);
                hideFragment(0);
                showFragment(2);
                break;
            default:
                break;
        }

    }

    private void addFragments() {
        getSupportFragmentManager().beginTransaction().add(R.id.main_middle, mFragmensts[0]).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.main_middle, mFragmensts[1]).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.main_middle, mFragmensts[2]).commit();
    }

    private void showFragment(int index) {
        getSupportFragmentManager().beginTransaction().show(mFragmensts[index]).commit();
    }

    private void hideFragment(int index) {
        getSupportFragmentManager().beginTransaction().hide(mFragmensts[index]).commit();
    }

}
