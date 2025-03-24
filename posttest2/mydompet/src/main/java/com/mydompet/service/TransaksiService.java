package com.mydompet.service;

import com.mydompet.model.Transaksi;
import com.mydompet.repository.TransaksiRepo;
import java.util.ArrayList;
import java.time.LocalDate;

public class TransaksiService {
    private TransaksiRepo transaksiRepo;

    public TransaksiService(TransaksiRepo transaksiRepo) {
        this.transaksiRepo = transaksiRepo;
    }

    // Create
    public void tambahTransaksi(int id, String jenis, String kategori, double jumlah, LocalDate tanggal, String deskripsi) {
        Transaksi transaksi = new Transaksi(id, jenis, kategori, jumlah, tanggal, deskripsi);
        transaksiRepo.tambahTransaksi(transaksi);
    }

    // Read
    public ArrayList<Transaksi> getTransaksi() {
        return transaksiRepo.getTransaksi();
    }

    // Update
    public void updateTransaksi(int id, String jenis, String kategori, double jumlah, LocalDate tanggal, String deskripsi) {
        Transaksi transaksi = new Transaksi(id, jenis, kategori, jumlah, tanggal, deskripsi);
        transaksiRepo.updateTransaksi(id, transaksi);
    }

    // Delete
    public void hapusTransaksi(int id) {
        transaksiRepo.hapusTransaksi(id);
    }

    public boolean idExists(int id) {
        return transaksiRepo.getTransaksi().stream().anyMatch(transaksi -> transaksi.getId() == id);
    }
}
