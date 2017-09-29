package org.kashiyatra.kashiyatra18;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.kashiyatra.kashiyatra18.fragments.ContactUsFragment;
import org.kashiyatra.kashiyatra18.fragments.DevTeamFragment;
import org.kashiyatra.kashiyatra18.fragments.EventsFragment;
import org.kashiyatra.kashiyatra18.fragments.FaqFragment;
import org.kashiyatra.kashiyatra18.fragments.GalleryFragment;
import org.kashiyatra.kashiyatra18.fragments.MapFragment;
import org.kashiyatra.kashiyatra18.fragments.ScheduleFragment;
import org.kashiyatra.kashiyatra18.fragments.UpdatesFragment;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private TabsPagerAdapter mTabsPagerAdapter;
    private ViewPager mViewPager;
    private int back_count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        final NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        navigationView.getMenu().getItem(0).setChecked(true);

        mTabsPagerAdapter = new TabsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = findViewById(R.id.viewpager);
        mViewPager.setAdapter(mTabsPagerAdapter);

        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                navigationView.getMenu().getItem(tab.getPosition()).setChecked(true);
                if (tab.getPosition() == 4) {
                    AppBarLayout appBarLayout = findViewById(R.id.app_bar);
                    appBarLayout.setExpanded(false, true);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
            super.onBackPressed();
        } else if (back_count == 0) {
            back_count = 1;
            Toast.makeText(this, "Tap back again to exit", Toast.LENGTH_SHORT).show();
            Thread tim = new Thread() {
                public void run() {
                    try {
                        sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        back_count = 0;
                    }
                }
            };
            tim.start();

        } else {
            finish();
            super.onBackPressed();
        }
    }

    public void call(View v) {
        String uri = "tel:" + ((TextView) v).getText();
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse(uri));
        startActivity(intent);
    }

    public void mail(View view) {
        String[] email = new String[]{(String) ((TextView) view).getText()};
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL, email);
        intent.putExtra(Intent.EXTRA_SUBJECT, "Query regarding Kashiyatra");
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        switch (id) {
            case R.id.nav_updates:
                mViewPager.setCurrentItem(0);
                break;
            case R.id.nav_schedule:
                mViewPager.setCurrentItem(1);
                break;
            case R.id.nav_events:
                mViewPager.setCurrentItem(2);
                break;
            case R.id.nav_gallery:
                mViewPager.setCurrentItem(3);
                break;
            case R.id.nav_location:
                mViewPager.setCurrentItem(4);
                break;
            case R.id.nav_faq:
                mViewPager.setCurrentItem(5);
                break;
            case R.id.nav_contact_us:
                mViewPager.setCurrentItem(6);
                break;
            case R.id.nav_devteam:
                mViewPager.setCurrentItem(7);
                break;
            default:
                break;
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private class TabsPagerAdapter extends FragmentPagerAdapter {

        private TabsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

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
                case 7:
                    return DevTeamFragment.newInstance();
                default:
                    return UpdatesFragment.newInstance();
            }
        }

        @Override
        public int getCount() {
            return 8;
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
