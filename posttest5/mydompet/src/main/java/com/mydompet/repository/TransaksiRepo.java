package com.mydompet.repository;

import com.mydompet.model.Transaksi;
import java.util.ArrayList;

public class TransaksiRepo {
    private ArrayList<Transaksi> transaksiList = new ArrayList<>();

    // Create
    public void tambahTransaksi(Transaksi transaksi) {
        transaksiList.add(transaksi);
    }

    // Read
    public ArrayList<Transaksi> getTransaksi() {
        return transaksiList;
    }

    // Update
    public void updateTransaksi(int id, Transaksi transaksi) {
        for (int i = 0; i < transaksiList.size(); i++) {
            if (transaksiList.get(i).getId() == id) {
                transaksiList.set(i, transaksi);
                break;
            }
        }
    }

    // Delete
    public void hapusTransaksi(int id) {
        for (int i = 0; i < transaksiList.size(); i++) {
            if (transaksiList.get(i).getId() == id) {
                transaksiList.remove(i);
                break;
            }
        }
    }
}
