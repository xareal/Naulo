package com.xar.naulo.fragments;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

public class GlobalFragment extends PostListFragment {

    public GlobalFragment() {
        // Required empty public constructor
    }

    @Override
    public Query getQuery(DatabaseReference databaseReference) {
        // [Start top post query]
        // [End top post query]

        return databaseReference.child("posts").orderByChild("starCount").limitToFirst(100);
    }
}

