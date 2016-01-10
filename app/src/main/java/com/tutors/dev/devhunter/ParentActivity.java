package com.tutors.dev.devhunter;

import android.app.Activity;
import android.os.Bundle;

import com.tutors.dev.devhunter.data.Key;

/**
 * Created by shs on 2016-01-09.
 */
public class ParentActivity extends Activity {

    protected String TAG = Key.DEBUG_TAG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG = getLocalClassName() + Key.DEBUG_TAG;
    }
}
