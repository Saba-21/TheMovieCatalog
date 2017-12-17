package com.example.pc.themoviecatalog.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.example.pc.themoviecatalog.ui.FragGeneration;
import com.example.pc.themoviecatalog.ui.FragWatched;
import com.example.pc.themoviecatalog.ui.FragWishes;

/**
 * Created by PC on 12-Dec-17.
 */

public class WPagerAdapter extends FragmentPagerAdapter {

    private static int TOTAL_ITEMS = 3;

    public WPagerAdapter(FragmentManager fm) { super(fm); }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return FragGeneration.newInstance();
            case 1:
                return FragWatched.newInstance();
            case 2:
                return FragWishes.newInstance();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return TOTAL_ITEMS;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Generate";
            case 1:
                return "Watched";
            case 2:
                return "Wished";
            default:
                return "";
        }
    }

}
