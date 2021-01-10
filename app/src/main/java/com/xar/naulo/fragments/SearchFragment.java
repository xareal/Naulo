package com.xar.naulo.fragments;

import androidx.fragment.app.Fragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

public class SearchFragment extends PostListFragment {

    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public Query getQuery(DatabaseReference databaseReference) {
        // TODO: Posts from search result 
        //return databaseReference.child
        return databaseReference.child("posts").orderByChild("starCount").limitToFirst(100);
   }
 }

