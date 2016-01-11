package com.tutors.dev.devhunter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by shs on 2016-01-10.
 */
public class FragmentAdapter extends FragmentStatePagerAdapter
{
    public static final int PAGE_TUTOR = 0;
    public static final int PAGE_TOTEE = 1;
    public static final int PAGE_ETC = 2;


    int pageCount;

    public FragmentAdapter(FragmentManager fm, int numberOfPage) {
        super(fm);
        pageCount = numberOfPage;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case PAGE_TUTOR:
                return new TutorFragment();
            case PAGE_TOTEE:
                return new TuteeFragment();
            case PAGE_ETC:
                return new EtcFragment();
            default:
                return  null;
        }
    }

    @Override
    public int getCount() {
        return pageCount;
    }
}
