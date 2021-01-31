package com.xar.naulo.adapters;


import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.xar.naulo.fragments.FollowingFragment;
import com.xar.naulo.fragments.GlobalFragment;
import com.xar.naulo.fragments.YouFragment;
import com.xar.naulo.models.Post;

/**
 * {@link CategoryAdapter} is a {@link FragmentPagerAdapter} that can provide the layout for
 * eack list item based on a data source which is a list of {@link Post} objects.
 */
public class CategoryAdapter extends FragmentStateAdapter {

    // Context of the app
    private Context mContext;

    /**
     * Create a new {@link CategoryAdapter} object.
     * @param fm is the fragment manager that will keep each fragment's state in the adapter
     */
    public CategoryAdapter(FragmentManager fm, Lifecycle lifecycle) {
        super(fm, lifecycle);
    }

    /**
     * Return the {@link Fragment} that should be displayed for the given page number
     */
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0) {
            return new YouFragment();
        } else if (position == 1) {
            return new FollowingFragment();
        } else {
            return new GlobalFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
