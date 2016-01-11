package com.tutors.dev.devhunter;

import android.content.Intent;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.tutors.dev.devhunter.popup.PopupManager;

public class MainActivity extends ParentActivity implements View.OnClickListener {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private PagerAdapter mAdapter;
    private CoordinatorLayout rootView;
    private FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupToolBar();
        setupTabView();

        rootView = (CoordinatorLayout)findViewById(R.id.main_layout);
        floatingActionButton = (FloatingActionButton)findViewById(R.id.floatBtn);
        floatingActionButton.setOnClickListener(this);

    }

    public void http_API_Request()
    {

    }

    public void setupTabView()
    {
        tabLayout = (TabLayout)findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_tutee));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_tutor));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.myinfo));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        mAdapter = new FragmentAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager = (ViewPager)findViewById(R.id.pager);
        viewPager.setAdapter(mAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) { }

            @Override
            public void onTabReselected(TabLayout.Tab tab) { }
        });

    }

    public void setupToolBar()
    {
//        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setHomeAsUpIndicator(R.mipmap.ic_dehaze_white_24dp);
        actionbar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_additem) {
            //종목추가.
            PopupManager.showSnackBar(rootView, "firstItem", "onClick", this);
            return true;

        } else if(item.getItemId() == R.id.action_etc) {

            PopupManager.showSnackBar(rootView, "seconds Item", "onClick",this);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

        if(v == floatingActionButton) {
            startActivity(new Intent(this, JoinTutorActivity.class));
        }

    }
}
