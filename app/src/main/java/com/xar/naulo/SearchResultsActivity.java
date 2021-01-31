package com.xar.naulo;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import androidx.viewpager2.widget.ViewPager2;
import com.xar.naulo.adapters.CategoryAdapter;


public class SearchResultsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_results_search);

    ViewPager2 viewPager = findViewById(R.id.view_pager);
    CategoryAdapter adapter = new CategoryAdapter(getSupportFragmentManager(), getLifecycle());
    viewPager.setAdapter(adapter);

    TabLayout searchTabs = findViewById(R.id.search_tabs);
    new TabLayoutMediator(searchTabs, viewPager, (tab, position) -> {
        if (position == 0) {
            tab.setText(R.string.category_search);
        }
    }).attach();

    handleIntent(getIntent());
	}

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        handleIntent(intent);
    }
    private void handleIntent(Intent intent) {

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            //use the query to search your data somehow
        }
    }
}