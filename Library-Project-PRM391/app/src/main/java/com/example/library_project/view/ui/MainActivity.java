package com.example.library_project.view.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.library_project.R;
import com.example.library_project.data.constants.Common;
import com.example.library_project.data.remote.ApiClient;
import com.example.library_project.data.remote.ApiInterface;
import com.example.library_project.view.adapter.ViewPagerAdapter;
import com.example.library_project.view.ui.interfaces.IListBook;
import com.example.library_project.view.ui.interfaces.ToolbarTitle;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements ToolbarTitle, IListBook {
    private long backPressedTime;
    private Toast mToast;

    private BottomNavigationView navigationView;
    private ViewPager mViewPager;
    private TextView titleItem;

    public static ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navigationView = findViewById(R.id.bottom_nav);
        mViewPager = findViewById(R.id.view_pager_main);
        titleItem = findViewById(R.id.item_title);
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        setUpViewPager();

        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_home:
                        titleItem.setText("Home");
                        mViewPager.setCurrentItem(0);
                        break;
                    case R.id.action_favorite:
                        titleItem.setText("Favorite");
                        mViewPager.setCurrentItem(1);
                        break;
                    case R.id.action_add:
                        titleItem.setText("Add");
                        mViewPager.setCurrentItem(2);
                        break;
                    case R.id.action_categories:
                        titleItem.setText("Categories");
                        mViewPager.setCurrentItem(3);
                        break;
                    case R.id.action_profile:
                        titleItem.setText("Profile");
                        mViewPager.setCurrentItem(4);
                        break;
                }
                return true;
            }
        });
    }

    private void setUpViewPager() {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mViewPager.setAdapter(viewPagerAdapter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        navigationView.getMenu().findItem(R.id.action_home).setChecked(true);
                        break;
                    case 1:
                        navigationView.getMenu().findItem(R.id.action_favorite).setChecked(true);
                        break;
                    case 2:
                        navigationView.getMenu().findItem(R.id.action_add).setChecked(true);
                        break;
                    case 3:
                        navigationView.getMenu().findItem(R.id.action_categories).setChecked(true);
                        break;
                    case 4:
                        navigationView.getMenu().findItem(R.id.action_profile).setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()){
            mToast.cancel();
            super.onBackPressed();
            return;
        } else {
            mToast = Toast.makeText(MainActivity.this, "Press back again to exit the application", Toast.LENGTH_SHORT);
            mToast.show();
        }
        backPressedTime = System.currentTimeMillis();
    }

    @Override
    public void setToolbarTitle(String toolbarTitle) {
        titleItem.setText(toolbarTitle);
    }

    @Override
    public void setViewBook(int id, String category) {
        Bundle bundle = new Bundle();
                // add bundle arguments
                bundle.putInt(Common.CAT_ID_KEY, id);
                bundle.putString(Common.CAT_NAME, category);
        mViewPager.setCurrentItem(5);
//                BooksFragment bookFragment = new BooksFragment();
//                bookFragment.setArguments(bundle);
    }
}