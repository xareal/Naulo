package com.xar.naulo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class GlobalFragment extends Fragment {

    public GlobalFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.post_list, container, false);

        // List of sample post of url
        final ArrayList<Post> posts = new ArrayList<Post>();
        // TODO: sample url here
        //posts.add(new Post("Title", "www.google.com", "img"));

        PostAdapter adapter = new PostAdapter(getActivity(), posts, R.color.category_global);
        ListView listView = (ListView) rootView.findViewById(R.id.list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // releaseMediaplayer();

                Post post = posts.get(position);

                // TODO: Open url or do something with it
            }
        });

        return rootView;
    }

    @Override
    public void onStop() {
        super.onStop();

        // TODO: releaseMediaPlayer();
    }

    // TODO: private void releaseMediaPlayer() {}
}

