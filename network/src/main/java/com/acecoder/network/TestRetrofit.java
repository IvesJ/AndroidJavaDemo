package com.acecoder.network;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class TestRetrofit {

    public void createRetrofit() throws IOException {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GithubService githubService = retrofit.create(GithubService.class);

        Call<List<String>> call = githubService.listRepos("square");
        List<String> repos = call.execute().body();
    }

    public interface GithubService {
        @GET("users/{user}/repos")
        Call<List<String>> listRepos (@Path("user") String user);
    }

}
