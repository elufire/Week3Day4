package com.example.week3day4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.week3day4.GitHubUser.User;
import com.example.week3day4.GithubRepository.Repository;
import com.example.week3day4.GithubRepository.Result;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import static com.example.week3day4.Constants.GITHUB_REPO_URL;
import static com.example.week3day4.Constants.GITHUB_URL;

public class MainActivity extends AppCompatActivity {
    TextView tvName;
    TextView tvLogin;
    TextView tvLocation;
    TextView tvType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvName = findViewById(R.id.tvName);
        tvLogin = findViewById(R.id.tvLogin);
        tvLocation = findViewById(R.id.tvLocation);
        tvType = findViewById(R.id.tvType);

        OkHttpHelper.ascyncOkHttpApi(GITHUB_URL);

    }

    public void onClick(View view){
        Intent intent = new Intent(this, Main2Activity.class);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void userEvent(UserEvent event){
        if(event != null){
            User user = event.getUser();
            tvName.setText(user.getName());
            tvLocation.setText(user.getLocation());
            tvType.setText(user.getType());
            tvLogin.setText(user.getLogin());

//            UserResponse userResponse = event.getUserResponse();
//            ArrayList<Result> arrayListResults = new ArrayList<>(userResponse.getResults());
//            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
//            recyclerViewAdapter = new RecyclerViewAdapter(arrayListResults);
//            recyclerView.setLayoutManager(layoutManager);
//            recyclerView.setAdapter(recyclerViewAdapter);

        }
    }
}
