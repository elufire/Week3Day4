package com.example.week3day4;

import android.content.Context;
import android.util.Log;

import com.example.week3day4.GitHubUser.User;
import com.example.week3day4.GithubRepository.Repository;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttpHelper  {
    Context context;
    public OkHttpHelper(Context context) {
        this.context = context;
    }

    public static void ascyncOkHttpApi(String url){
        OkHttpClient okHttpClient = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            String jsonResonse;
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("TAG", "onFailure: ");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                jsonResonse = response.body().string();
                Gson gson = new Gson();
                User user = gson.fromJson(jsonResonse, User.class);
                EventBus.getDefault().post(new UserEvent(user));
            }
        });
    }

    public static void ascyncRepoOkHttpApi(String url){
        OkHttpClient okHttpClient = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            String jsonResonse;
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("TAG", "onFailure: ");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                jsonResonse = "{\"results\":" + response.body().string() + "}";
                Gson gson = new Gson();
                Repository repoResponse = gson.fromJson(jsonResonse, Repository.class);
                EventBus.getDefault().post(new RepoListEvent(repoResponse));
            }
        });
    }



}