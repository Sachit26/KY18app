package org.kashiyatra.kashiyatra18;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import org.kashiyatra.kashiyatra18.fragments.DescriptionFragment;
import org.kashiyatra.kashiyatra18.fragments.RulesFragment;

import static java.lang.Math.min;


public class EventActivity extends AppCompatActivity {

    private TabsPagerAdapter mTabsPagerAdapter;
    private ViewPager mViewPager;
    private AppBarLayout appBarLayout;
    private int eventPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        Toolbar toolbar = findViewById(R.id.event_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        eventPosition = getIntent().getIntExtra("POSITION", 0);

        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        final int screenWidth = displaymetrics.widthPixels;

        appBarLayout = findViewById(R.id.app_bar);
        final TabLayout tabLayout = findViewById(R.id.event_tabs);
        appBarLayout.post(new Runnable() {
            @Override
            public void run() {
                int heightPx = screenWidth * 1 / 3;
                setAppBarOffset(heightPx);
            }
        });
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                float range = appBarLayout.getTotalScrollRange();
                float alpha = min((1 + verticalOffset / range) * 3 / 2, 1);
                (findViewById(R.id.event_banner)).setAlpha(alpha);
            }
        });

        ImageView eventImageView = findViewById(R.id.event_banner);
        Bitmap eventBanner = BitmapFactory.decodeResource(getResources(), R.drawable.events);
        eventImageView.setImageResource(R.drawable.events);
        getSupportActionBar().setTitle(getResources().getStringArray(R.array.event_names)[eventPosition]);

        Palette.from(eventBanner).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                int darkVibrantColor = palette.getDarkVibrantColor(ContextCompat.getColor(getApplicationContext(), R.color.colorSecondary));
                int lightMutedColor = palette.getLightMutedColor(ContextCompat.getColor(getApplicationContext(), R.color.colorSecondary));
                appBarLayout.setBackgroundColor(darkVibrantColor);
                tabLayout.setBackgroundColor(lightMutedColor);

            }
        });

        mTabsPagerAdapter = new EventActivity.TabsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = findViewById(R.id.event_viewpager);
        mViewPager.setAdapter(mTabsPagerAdapter);

        tabLayout.setupWithViewPager(mViewPager);

    }

    private void setAppBarOffset(int offsetPx) {
        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) appBarLayout.getLayoutParams();
        AppBarLayout.Behavior behavior = (AppBarLayout.Behavior) params.getBehavior();
        behavior.onNestedPreScroll((CoordinatorLayout) findViewById(R.id.event_coordinator_layout), appBarLayout, null, 0, offsetPx, new int[]{0, 0}, 0);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.action_register:
                Toast.makeText(getApplicationContext(), "Will be added soon", Toast.LENGTH_SHORT).show();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.event, menu);
        return true;
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
                    return DescriptionFragment.newInstance(eventPosition);
                case 1:
                    return RulesFragment.newInstance(eventPosition);
                default:
                    return RulesFragment.newInstance(eventPosition);
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

