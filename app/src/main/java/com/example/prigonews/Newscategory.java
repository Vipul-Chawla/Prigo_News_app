package com.example.prigonews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;

public class Newscategory extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private Verticaladapter adapter;
    private ApiInterface request;
    private TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newscategory);
        Intent intent = getIntent();
        String category = intent.getStringExtra("category");

        text = findViewById(R.id.category_name);
        recyclerView = findViewById(R.id.veri_recyclerview);
        layoutManager =  new LinearLayoutManager(this);
        loadnews(category);
        adapter = new Verticaladapter(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
    public void loadnews(final String cat){
        text.setText(cat);
        new Thread(new Runnable() {
            @Override
            public void run() {
                request = ApiClient.getRetrofit().create(ApiInterface.class);
                request.getNews1("in",cat,"stackoverflow.com","publishedAt","en","7d5254c453794658a84f7f741a903005").enqueue(new Callback<veri_data_class>() {

                    @Override
                    public void onResponse(Call<veri_data_class> call, retrofit2.Response<veri_data_class> response) {
                        adapter.updatelist((ArrayList<veri_data_class.Article>) response.body().articles);

                    }

                    @Override
                    public void onFailure(Call<veri_data_class> call, Throwable t) {
                        Toast.makeText(Newscategory.this, "Failure"+t.toString() , Toast.LENGTH_SHORT).show();
                    }
                });
            };
        }).start();

    }

}
