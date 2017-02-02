package com.ptlearnpoint.www.retrofittest1;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView ;
    ExampleAdapter adapter ;
    ArrayList<Example> worldpopulationArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.customer_list);

        loadView();
    }

    private void loadView() {

        if(isNetworkConnected()){
            final ProgressDialog dialog ;
            dialog = new ProgressDialog(this);
            dialog.setTitle("Wait!");
            dialog.setMessage("Loading......");
            dialog.show();

            ApiService api = RetroClient.getApiService();

            Call<ArrayList<Example>> call = api.getMovie();

            call.enqueue(new Callback<ArrayList<Example>>() {
                @Override
                public void onResponse(Call<ArrayList<Example>> call, Response<ArrayList<Example>> response) {
                    dialog.hide();
                    worldpopulationArrayList = response.body();
                    adapter= new ExampleAdapter(MainActivity.this,worldpopulationArrayList);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setAdapter(adapter);

                }

                @Override
                public void onFailure(Call<ArrayList<Example>> call, Throwable t) {
                    Toast.makeText(MainActivity.this, "Failed To Load", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            });
        }
        else{
            Toast.makeText(this, "Connect Internet", Toast.LENGTH_SHORT).show();
        }




    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }

}
