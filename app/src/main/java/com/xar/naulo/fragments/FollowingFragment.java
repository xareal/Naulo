package com.xar.naulo;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class FollowingFragment extends Fragment {

    public FollowingFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.post_list, container, false);

        // List of sample post of url
        final ArrayList<Post> posts = new ArrayList<>();
        // TODO: sample url here
        posts.add(new Post(R.string.category_following, "https://www.google.com", R.drawable.common_google_signin_btn_icon_dark));
        posts.add(new Post(R.string.category_following, "https://radiosagarmatha.org.np/wp-content/uploads/2018/01/Bhairab-Sir.mp3", R.drawable.common_google_signin_btn_icon_dark));
        posts.add(new Post(R.string.category_following, "http://161.97.67.77:9300/;&autostart=true", R.drawable.common_google_signin_btn_icon_dark));


        PostAdapter adapter = new PostAdapter(getActivity(), posts, R.color.category_following);
        ListView listView = (ListView) rootView.findViewById(R.id.list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Post post = posts.get(position);

                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(post.getUrlId()));
                    startActivity(intent);
                } catch (Exception e) {
                    System.out.println("Url is not working");
                }





            }
        });

        return rootView;
    }

    @Override
    public void onStop() {
        super.onStop();
    }
 }

