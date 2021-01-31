package com.xar.naulo.fragments;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

public class SearchFragment extends PostListFragment {

	private String query;

    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public Query getQuery(DatabaseReference databaseReference) {
        // TODO: Posts from search result 
        //return databaseReference.child

        return databaseReference.orderByChild("posts")
                .startAt(query)
                .endAt(query + "\uf8ff")
                .limitToFirst(100);
 	}


}

