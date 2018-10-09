package com.example.oscarhg1.userprofile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ProfileActivity extends AppCompatActivity {

    private ImageView userProfileMain;
    private ImageView userProfileBG;
    private TextView name;
    private TextView handle;
    private TextView following;
    private TextView followers;
    private TextView about;

    private Gson gson;
    Profile profile;
    private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        gson = new Gson();

        queue = Volley.newRequestQueue(this);

        userProfileMain = findViewById(R.id.userProfileMain);
        userProfileBG = findViewById(R.id.userProfileBG);
        name = findViewById(R.id.name);
        handle = findViewById(R.id.handle);
        following = findViewById(R.id.following);
        followers = findViewById(R.id.followers);
        about = findViewById(R.id.about);


        Glide.with(this)
               .load("file:///android_asset/OscarHernandez.png")
               .into(userProfileMain);
        Glide.with(this)
               .load("file:///android_asset/UserProfile-background.png")
               .into(userProfileBG);


        try {
            InputStream stream = getAssets().open("OscarHernandez.json");
            InputStreamReader reader = new InputStreamReader(stream);
            profile = gson.fromJson(reader, Profile.class);
        } catch (IOException e) {
            e.printStackTrace();
        }


        name.setText(profile.getName());
        handle.setText(profile.getHandle());
        following.setText(profile.getFollowing());
        followers.setText((profile.getFollowers()));
        about.setText(profile.getAbout());
    }
}
