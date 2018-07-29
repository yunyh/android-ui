package com.my.listapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TableLayout;

import com.my.listapplication.fragment.ListFragment;
import com.my.listapplication.widget.IndicatorLayout;

public final class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return new ListFragment();
            }

            @Override
            public int getCount() {
                return 4;
            }
        });
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setTitle("Position " + position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        IndicatorLayout indicatorLayout = findViewById(R.id.indicator);
        indicatorLayout.setViewPager(viewPager);

    }
}
