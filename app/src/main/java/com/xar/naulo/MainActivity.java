package com.xar.naulo;


import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.xar.naulo.adapters.CategoryAdapter;

import com.google.firebase.auth.FirebaseAuth;

import androidx.appcompat.widget.SearchView;
import androidx.core.view.MenuItemCompat;
import androidx.viewpager2.widget.ViewPager2;

import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager2 viewPager = findViewById(R.id.view_pager);
        CategoryAdapter adapter = new CategoryAdapter(getSupportFragmentManager(), getLifecycle());
        viewPager.setAdapter(adapter);

        TabLayout tabs = findViewById(R.id.tabs);
        new TabLayoutMediator(tabs, viewPager, (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText(R.string.category_you);
                    break;
                case 1:
                    tab.setText(R.string.category_following);
                    break;
                case 2:
                    tab.setText(R.string.category_global);
                    break;
            }
        }).attach();

        FloatingActionButton fab = findViewById(R.id.fabNewPost);
        fab.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), NewPostActivity.class);
            startActivity(intent);

        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setOnQueryTextListener(
            new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextChange (String newText) {
                    // Text has changed, apply filtering?
                    return false;
                }
                public boolean onQueryTextSubmit(String query) {
                    // Perform the final search
                    return true;
                }

            });

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        // Cam be replaced with getComponentName()
        // if this searchable activity is the current activity
        ComponentName componentName = new ComponentName(getApplicationContext(), SearchResultsActivity.class);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName));

        return true;

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int i = item.getItemId();
        if (i == R.id.action_logout) {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(this, GoogleSignInActivity.class));
            finish();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}
