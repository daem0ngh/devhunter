package com.tutors.dev.devhunter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.tutors.dev.devhunter.popup.PopupManager;

/**
 * Created by shs on 2016-01-10.
 */
public class JoinTutorActivity extends ParentActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_tutor);

        ActionBar actionbar = getSupportActionBar();
        actionbar.setHomeAsUpIndicator(R.mipmap.ic_arrow_back_white_24dp);
        actionbar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.join_member, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_join) {
            //comfrim
            PopupManager.showSnackBar(findViewById(R.id.layoutRootView),"Success", "Join Success", this);
            startActivity(new Intent(this, JoinInsertPhoneActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

    }
}
