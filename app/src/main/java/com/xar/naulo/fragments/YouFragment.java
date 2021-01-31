package com.xar.naulo.fragments;

import androidx.fragment.app.Fragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

public class YouFragment extends PostListFragment {

    public YouFragment() {
        // Required empty public constructor
    }

    @Override
    public Query getQuery(DatabaseReference databaseReference) {
        // All you post
        return databaseReference.child("user-posts").child(getUid());
    }

}
