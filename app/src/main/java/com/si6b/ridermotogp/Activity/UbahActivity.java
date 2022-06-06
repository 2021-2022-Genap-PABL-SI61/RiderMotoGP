package com.si6b.ridermotogp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.si6b.ridermotogp.API.APIRequestData;
import com.si6b.ridermotogp.API.RetroServer;
import com.si6b.ridermotogp.Model.ModelResponse;
import com.si6b.ridermotogp.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UbahActivity extends AppCompatActivity {
    private String yId, yNama, yNomor, yNegara, ySponsor;
    private EditText etNama, etNomor, etSponsor, etNegara;
    private Button btnUbah;
    private String nama, nomor, sponsor, negara;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah);

        initView();

        Intent tangkap = getIntent();
        yId = tangkap.getStringExtra("xId");
        yNama = tangkap.getStringExtra("xNama");
        yNomor = tangkap.getStringExtra("xNomor");
        yNegara = tangkap.getStringExtra("xNegara");
        ySponsor = tangkap.getStringExtra("xSponsor");

        etNama.setText(yNama);
        etNomor.setText(yNomor);
        etSponsor.setText(ySponsor);
        etNegara.setText(yNegara);

        btnUbah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nama = etNama.getText().toString();
                nomor = etNomor.getText().toString();
                sponsor = etSponsor.getText().toString();
                negara = etNegara.getText().toString();

                if(nama.trim().equals("")){
                    etNama.setError("Nama Rider Harus Diisi!");
                }
                else if(nomor.trim().equals("")){
                    etNomor.setError("Nomor Rider Harus Diisi!");
                }
                else if(sponsor.trim().equals("")){
                    etSponsor.setError("Sponsor Harus Diisi!");
                }
                else if(negara.trim().equals("")){
                    etNegara.setError("Negara Asal Rider Harus Diisi!");
                }
                else{
                    updateRider();
                }
            }
        });
    }

    private void initView(){
        etNama = findViewById(R.id.et_nama);
        etNomor = findViewById(R.id.et_nomor);
        etSponsor = findViewById(R.id.et_sponsor);
        etNegara = findViewById(R.id.et_negara);
        btnUbah = findViewById(R.id.btn_ubah);
    }

    private void updateRider(){
        APIRequestData API = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ModelResponse> proses = API.ardUpdateData(yId, nama, nomor, sponsor, negara);

        proses.enqueue(new Callback<ModelResponse>() {
            @Override
            public void onResponse(Call<ModelResponse> call, Response<ModelResponse> response) {
                int kode = response.body().getKode();
                String pesan = response.body().getPesan();

                if(kode == 1){
                    Toast.makeText(UbahActivity.this, "Selamat! Sukses Mengubah Data Rider", Toast.LENGTH_SHORT).show();
                    finish();
                }
                else{
                    Toast.makeText(UbahActivity.this, "Perhatian! Gagal Mengubah Data Rider! "+yId, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ModelResponse> call, Throwable t) {
                Toast.makeText(UbahActivity.this, "Gagal Menghubungi Server !", Toast.LENGTH_SHORT).show();
            }
        });
    }
}