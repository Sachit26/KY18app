package org.kashiyatra.kashiyatra18;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;

import org.kashiyatra.kashiyatra18.fragments.UpdatesFragment;

public class EventActivity extends AppCompatActivity {

    private TabsPagerAdapter mTabsPagerAdapter;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        Toolbar toolbar = findViewById(R.id.event_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        int eventPosition = getIntent().getIntExtra("POSITION", 0);

        ImageView eventImageView = findViewById(R.id.event_banner);
        eventImageView.setImageResource(R.drawable.events);
        getSupportActionBar().setTitle(getResources().getStringArray(R.array.event_names)[eventPosition]);

        mTabsPagerAdapter = new EventActivity.TabsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = findViewById(R.id.event_viewpager);
        mViewPager.setAdapter(mTabsPagerAdapter);

        TabLayout tabLayout = findViewById(R.id.event_tabs);
        tabLayout.setupWithViewPager(mViewPager);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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
                    return UpdatesFragment.newInstance();
                default:
                    return UpdatesFragment.newInstance();
            }
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "DESCRIPTION";
                case 1:
                    return "RULES";
                default:
                    return "DESCRIPTION";
            }
        }

    }
}

