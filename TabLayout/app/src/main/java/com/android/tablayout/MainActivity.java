package com.android.tablayout;

import android.opengl.Visibility;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private ArrayList<Fragment> mFragments;
    private Fragment FirstFragment, SecondFragment;
    private FragAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        FirstFragment = new FirstFragment();
        SecondFragment = new SecondFragment();

        mFragments = new ArrayList<>();
        mFragments.add(FirstFragment);
        mFragments.add(SecondFragment);

        adapter = new FragAdapter(getSupportFragmentManager(), mFragments);
        mViewPager = (ViewPager) findViewById(R.id.view_pager);

        mViewPager.setAdapter(adapter);

        mTabLayout = (TabLayout) findViewById(R.id.tab_layout);
        mTabLayout.setupWithViewPager(mViewPager);

        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                switch (tab.getPosition()) {
                    case 0:
                        com.android.tablayout.FirstFragment f1 = (com.android.tablayout.FirstFragment) mFragments.get(0);
                        f1.refreshInvoked();
                        break;
                    case 1:
                        com.android.tablayout.SecondFragment f2 = (com.android.tablayout.SecondFragment) mFragments.get(1);
                        f2.refreshInvoked();
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
