package main.repository;

import main.model.Transaksi;
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
        if (id >= 0 && id < transaksiList.size()) {
            transaksiList.set(id, transaksi);
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
