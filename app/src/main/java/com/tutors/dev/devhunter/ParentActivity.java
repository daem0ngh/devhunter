package com.tutors.dev.devhunter;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.RelativeLayout;

import com.tutors.dev.devhunter.data.Key;

/**
 * Created by shs on 2016-01-09.
 */
public class ParentActivity extends AppCompatActivity {

    protected String TAG = Key.DEBUG_TAG;

    protected CoordinatorLayout layoutRootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_root_view);
        TAG = getLocalClassName() + Key.DEBUG_TAG;

        layoutRootView = (CoordinatorLayout)findViewById(R.id.layoutRootView);
    }

}
