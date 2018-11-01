package com.yichan.pea.order.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.yichan.pea.R;
import com.yichan.pea.home.fragment.HomeFragment;
import com.yichan.pea.order.activity.OrderQueryActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class OrderFragment extends Fragment {

    @BindView(R.id.date)
    ImageView date;
    public OrderFragment() {
        // Required empty public constructor
    }
    public static OrderFragment newInstance(String from) {
        Bundle args = new Bundle();
        args.putString("from", from);
        OrderFragment fragment = new OrderFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.pea_fragment_order, container, false);
        ButterKnife.bind(this,view);
        initView();
        return view;
    }

    private void initView() {
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), OrderQueryActivity.class));
            }
        });
    }

}
