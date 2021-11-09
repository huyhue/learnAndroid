package com.example.library_project.view.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.library_project.view.ui.AddBookFragment;
import com.example.library_project.view.ui.BooksFragment;
import com.example.library_project.view.ui.CategoriesFragment;
import com.example.library_project.view.ui.FavoriteFragment;
import com.example.library_project.view.ui.HomeFragment;
import com.example.library_project.view.ui.ProfileFragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new CategoriesFragment();
            case 1:
                return new FavoriteFragment();
            case 2:
                return new AddBookFragment();
            case 3:
                return new CategoriesFragment();
            case 4:
                return new ProfileFragment();
            default:
                return new HomeFragment();
        }
    }

    @Override
    public int getCount() {
        return 5;
    }
}
