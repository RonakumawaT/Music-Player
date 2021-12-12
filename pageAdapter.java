package com.example.tablayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class pageAdapter extends FragmentPagerAdapter {

    int tabCount;

    public pageAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        tabCount = behavior;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new Track();
            case 1:
                return new fav();
            case 2:
                return new ftab3();
            case 3:
                return new artists();
            case 4:
                return new tab5();
            case 5:
                return new tab6();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
