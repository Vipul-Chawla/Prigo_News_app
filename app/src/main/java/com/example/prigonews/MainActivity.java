package com.example.prigonews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements Horizontaladapter.onCategoryclick {
    private ArrayList<Hori_data_class>list = new ArrayList();
    private ApiInterface request;
    private RecyclerView hori_recyclerview;
    private RecyclerView veri_recyclerview;
    private RecyclerView.LayoutManager hori_layoutmanager;
    private RecyclerView.LayoutManager veri_layoutmanager;
    private Horizontaladapter horizontaladapter;
    private Verticaladapter verticaladapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
                setSupportActionBar(toolbar);
        inithoridata();
        initveridata();
        hori_layoutmanager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        veri_layoutmanager = new LinearLayoutManager(this);

        hori_recyclerview = findViewById(R.id.hori_recyclerview);
        veri_recyclerview = findViewById(R.id.veri_recyclerview);

        horizontaladapter = new Horizontaladapter(list,this);
        hori_recyclerview.setLayoutManager(hori_layoutmanager);
        hori_recyclerview.setAdapter(horizontaladapter);


        verticaladapter = new Verticaladapter(this);
        veri_recyclerview.setLayoutManager(veri_layoutmanager);
        veri_recyclerview.setAdapter(verticaladapter);
    }
    void inithoridata(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                list.add(new Hori_data_class(R.drawable.business,"Business"));
                list.add(new Hori_data_class(R.drawable.entertainment,"Entertainment"));
                list.add(new Hori_data_class(R.drawable.general,"General"));
                list.add(new Hori_data_class(R.drawable.sports,"Sports"));
                list.add(new Hori_data_class(R.drawable.science,"Science"));
                list.add(new Hori_data_class(R.drawable.technology,"Technology"));
                list.add(new Hori_data_class(R.drawable.health,"Health"));
            }
        }).start();

    }
    void initveridata(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                request = ApiClient.getRetrofit().create(ApiInterface.class);
                request.getNews().enqueue(new Callback<veri_data_class>() {
                    @Override
                    public void onResponse(Call<veri_data_class> call, Response<veri_data_class> response) {
                        verticaladapter.updatelist((ArrayList<veri_data_class.Article>) response.body().articles);

                    }

                    @Override
                    public void onFailure(Call<veri_data_class> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "Failure"+t.toString() , Toast.LENGTH_SHORT).show();
                    }
                });
            };
        }).start();

    }

    @Override
    public void newsonclick(int position) {
        list.get(position);
        String category[] = {"business","entertainment","general","sports","science","technology","health"};
        Intent intent = new Intent(this,Newscategory.class);
        intent.putExtra("category",category[position]);
        startActivity(intent);
    }
}
