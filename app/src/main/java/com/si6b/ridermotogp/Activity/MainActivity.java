package com.si6b.ridermotogp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.si6b.ridermotogp.API.APIRequestData;
import com.si6b.ridermotogp.API.RetroServer;
import com.si6b.ridermotogp.Adapter.AdapterRider;
import com.si6b.ridermotogp.Model.ModelResponse;
import com.si6b.ridermotogp.Model.ModelRider;
import com.si6b.ridermotogp.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvRider;
    private FloatingActionButton fabTambahRider;
    private ProgressBar pbRider;

    private RecyclerView.Adapter adRider;
    private RecyclerView.LayoutManager lmRider;
    private List<ModelRider> listRider = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvRider = findViewById(R.id.rv_rider);
        fabTambahRider = findViewById(R.id.fab_tambah_rider);
        pbRider = findViewById(R.id.pb_rider);

        lmRider = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvRider.setLayoutManager(lmRider);
    }

    @Override
    protected void onResume() {
        super.onResume();
        retrieveAllRider();
    }

    public void retrieveAllRider(){
        pbRider.setVisibility(View.VISIBLE);

        APIRequestData API = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ModelResponse> proses = API.ardRetrieveAllData();

        proses.enqueue(new Callback<ModelResponse>() {
            @Override
            public void onResponse(Call<ModelResponse> call, Response<ModelResponse> response) {
                int kode = response.body().getKode();
                String pesan = response.body().getPesan();
                listRider = response.body().getData();

                adRider = new AdapterRider(MainActivity.this, listRider);
                rvRider.setAdapter(adRider);
                adRider.notifyDataSetChanged();

                pbRider.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<ModelResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Gagal Menghubungi Server !", Toast.LENGTH_SHORT).show();
                pbRider.setVisibility(View.GONE);
            }
        });
    }
}