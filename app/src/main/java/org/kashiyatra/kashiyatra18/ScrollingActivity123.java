package org.kashiyatra.kashiyatra18;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import org.kashiyatra.kashiyatra18.fragments.ContactUsFragment;
import org.kashiyatra.kashiyatra18.fragments.EventsFragment;
import org.kashiyatra.kashiyatra18.fragments.FaqFragment;
import org.kashiyatra.kashiyatra18.fragments.GalleryFragment;
import org.kashiyatra.kashiyatra18.fragments.MapFragment;
import org.kashiyatra.kashiyatra18.fragments.ScheduleFragment;
import org.kashiyatra.kashiyatra18.fragments.UpdatesFragment;

public class ScrollingActivity123 extends AppCompatActivity {

    private TabsPagerAdapter mTabsPagerAdapter;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mTabsPagerAdapter = new TabsPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mViewPager.setAdapter(mTabsPagerAdapter);

        final TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            int iconId = R.drawable.ic_devs;
            tabLayout.getTabAt(i).setIcon(iconId);
        }

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                tabLayout.getTabAt(position).select();
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }

        });

        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager) {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                super.onTabSelected(tab);
            }
        });
    }

    private class TabsPagerAdapter extends FragmentPagerAdapter {

        private TabsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).

            switch (position) {
                case 0:
                    return UpdatesFragment.newInstance();
                case 1:
                    return ScheduleFragment.newInstance();
                case 2:
                    return EventsFragment.newInstance();
                case 3:
                    return GalleryFragment.newInstance();
                case 4:
                    return MapFragment.newInstance();
                case 5:
                    return FaqFragment.newInstance();
                case 6:
                    return ContactUsFragment.newInstance();
                default:
                    return UpdatesFragment.newInstance();
            }
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 6;
        }

        @Override
        public int getItemPosition(Object object) {
            Log.d("Swapped Pos = ", getItemPosition(object) + "");
            return super.getItemPosition(object);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "UPDATES";
                case 1:
                    return "SCHEDULE";
                case 2:
                    return "EVENTS";
                case 3:
                    return "GALLERY";
                case 4:
                    return "Map";
                case 5:
                    return "FAQ";
                case 6:
                    return "CONTACT US";
                case 7:
                    return "DEVELOPERS";
                default:
                    return "UPDATES";
            }


        }
    }

}