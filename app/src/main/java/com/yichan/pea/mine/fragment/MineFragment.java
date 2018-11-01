package com.yichan.pea.mine.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yichan.pea.R;
import com.yichan.pea.mine.activity.AboutActivity;
import com.yichan.pea.mine.activity.SettingActivity;
import com.yichan.pea.mine.activity.UserinfoActivity;
import com.yichan.pea.order.fragment.OrderFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class MineFragment extends Fragment {

    @BindView(R.id.top_title)
    TextView topTitle;
    @BindView(R.id.hot_line)
    RelativeLayout horLine;
    @BindView(R.id.rl_about)
    RelativeLayout about;
    @BindView(R.id.rl_settings)
    RelativeLayout settings;
    @BindView(R.id.rl_head)
    RelativeLayout rl_head;
    public MineFragment() {
        // Required empty public constructor
    }

    public static MineFragment newInstance(String from) {
        Bundle args = new Bundle();
        args.putString("from", from);
        MineFragment fragment = new MineFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.pea_fragment_mine, container, false);
        ButterKnife.bind(this,view);
        initView();
        return view;
    }
    private void initView(){
        topTitle.setText("我的");
        topTitle.setVisibility(View.VISIBLE);
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 startActivity(new Intent(getContext(), AboutActivity.class));
            }
        });
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), SettingActivity.class));
            }
        });
        rl_head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), UserinfoActivity.class));
            }
        });

    }

}
