package com.si6b.ridermotogp.Model;

import java.util.List;

public class ModelResponse {
    int kode;
    String pesan;
    List<ModelRider> data;

    public int getKode() {
        return kode;
    }

    public String getPesan() {
        return pesan;
    }

    public List<ModelRider> getData() {
        return data;
    }
}
