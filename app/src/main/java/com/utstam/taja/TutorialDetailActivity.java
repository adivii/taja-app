package com.utstam.taja;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.utstam.taja.databinding.ActivityContentDetailBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class TutorialDetailActivity extends AppCompatActivity {
    ActivityContentDetailBinding activityContentDetailBinding;
    TutorialApiInterface tutorialApiInterface;
    Retrofit retrofit;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityContentDetailBinding = ActivityContentDetailBinding.inflate(getLayoutInflater());
        id = getIntent().getIntExtra("id", -1);

        retrofit = ApiClient.getClient();
        tutorialApiInterface = retrofit.create(TutorialApiInterface.class);

        Call<List<Tutorial>> callFetch = tutorialApiInterface.getTutorial(id);
        callFetch.enqueue(new Callback<List<Tutorial>>() {
            @Override
            public void onResponse(Call<List<Tutorial>> call, Response<List<Tutorial>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(TutorialDetailActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                }else{
                    List<Tutorial> tutorials = response.body();
                    getSupportActionBar().setTitle(tutorials.get(0).getTitle());
                    activityContentDetailBinding.contentBody.setText(tutorials.get(0).getContent());
                }
            }

            @Override
            public void onFailure(Call<List<Tutorial>> call, Throwable t) {

            }
        });

        Log.d(TAG, String.valueOf(id));

        setContentView(activityContentDetailBinding.getRoot());
    }
}