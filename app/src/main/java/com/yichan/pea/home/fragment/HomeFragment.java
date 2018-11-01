package com.yichan.pea.home.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cool.expandviewlibrary.ExpandView;
import com.yichan.pea.R;
import com.yichan.pea.home.activity.MainActivity;
import com.yichan.pea.home.activity.NotifyActivity;
import com.yichan.pea.home.activity.SellerenterActivity;
import com.yichan.pea.home.activity.SellerinfoActivity;
import com.yichan.pea.home.activity.SuggActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    @BindView(R.id.rl_msg)
    RelativeLayout msg;
    @BindView(R.id.ev_img)
    ExpandView mExpandView;
    @BindView(R.id.ll_info)
    LinearLayout sellerinfo;
    @BindView(R.id.ll_mana)
    LinearLayout ordermana;
    @BindView(R.id.ll_enter)
    LinearLayout sellerenter;
    @BindView(R.id.show)
    ImageView sanjiao;
    @BindView(R.id.rl_hide)
    RelativeLayout hideLayout;
    @BindView(R.id.ll_sugg)
    LinearLayout sugg;
    @BindView(R.id.ll_help)
    LinearLayout help;
    boolean isShow=false;
    public HomeFragment() {
        // Required empty public constructor
    }
    public static HomeFragment newInstance(String from) {
        Bundle args = new Bundle();
        args.putString("from", from);
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.pea_fragment_home, container, false);
        ButterKnife.bind(this,view);
        initView();
        return view;
    }
    private void initView(){
        sugg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), SuggActivity.class));
            }
        });
        sanjiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 if(!isShow){
                     hideLayout.setVisibility(View.VISIBLE);
                     Animation showAnim= AnimationUtils.loadAnimation(getContext(),R.anim.pop_enter_anim);
                     hideLayout.startAnimation(showAnim);
                     isShow=true;
                     sanjiao.animate().rotation(180);
                 }else{
                     hideLayout.setVisibility(View.GONE);
                     Animation hideAnim= AnimationUtils.loadAnimation(getContext(),R.anim.pop_exit_anim);
                     hideLayout.startAnimation(hideAnim);
                     isShow=false;
                     sanjiao.animate().rotation(360);
                 }
            }
        });
        sellerinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), SellerinfoActivity.class));
            }
        });
        sellerenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), SellerenterActivity.class));
            }
        });
        ordermana.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               MainActivity mainActivity= (MainActivity) getActivity();
               mainActivity.onTabItemSelected(1);
            }
        });
        msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), NotifyActivity.class));
            }
        });
    }

}
