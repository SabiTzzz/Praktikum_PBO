package com.mydompet.service;

import com.mydompet.model.Pemasukan;
import com.mydompet.model.Pengeluaran;
import com.mydompet.model.Rutinan;
import com.mydompet.model.Transaksi;
import com.mydompet.repository.TransaksiRepo;

import java.time.LocalDate;
import java.util.ArrayList;

public class TransaksiService {
    private TransaksiRepo transaksiRepo;

    public TransaksiService(TransaksiRepo transaksiRepo) {
        this.transaksiRepo = transaksiRepo;
    }

    // Create
    public void tambahTransaksi(int id, String jenis, String kategori, double nominal, LocalDate tanggal, String keterangan) {
        Transaksi transaksi;
        if (" Pemasukan ".equalsIgnoreCase(jenis)) {
            transaksi = new Pemasukan(id, nominal, tanggal, keterangan, kategori);
        } else if ("Pengeluaran".equalsIgnoreCase(jenis)) {
            transaksi = new Pengeluaran(id, nominal, tanggal, keterangan, kategori);
        } else if ("Rutinan".equalsIgnoreCase(jenis)) {
            transaksi = new Rutinan(id, nominal, tanggal, keterangan, kategori);
        } else {
            throw new IllegalArgumentException("Jenis transaksi tidak valid. Harus 'Pemasukan' atau 'Pengeluaran' atau 'Rutinan'.");
        }
        transaksiRepo.tambahTransaksi(transaksi);
    }

    // Read
    public ArrayList<Transaksi> getTransaksi() {
        return transaksiRepo.getTransaksi();
    }

    // Update
    public void updateTransaksi(int id, String jenis, String kategori, double nominal, LocalDate tanggal, String keterangan) {
        Transaksi transaksi;
        if (" Pemasukan ".equalsIgnoreCase(jenis)) {
            transaksi = new Pemasukan(id, nominal, tanggal, keterangan, kategori);
        } else if ("Pengeluaran".equalsIgnoreCase(jenis)) {
            transaksi = new Pengeluaran(id, nominal, tanggal, keterangan, kategori);
        } else if ("Rutinan".equalsIgnoreCase(jenis)) {
            transaksi = new Rutinan(id, nominal, tanggal, keterangan, kategori);
        } else {
            throw new IllegalArgumentException("Jenis transaksi tidak valid. Harus 'Pemasukan' atau 'Pengeluaran' atau 'Rutinan'.");
        }
        transaksiRepo.updateTransaksi(id, transaksi);
    }

    // Delete
    public void hapusTransaksi(int id) {
        transaksiRepo.hapusTransaksi(id);
    }

    // Cek apakah ID sudah ada
    public boolean idExists(int id) {
        return transaksiRepo.getTransaksi().stream().anyMatch(transaksi -> transaksi.getId() == id);
    }
}