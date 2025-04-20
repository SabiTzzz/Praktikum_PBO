package com.mydompet.model;

import java.time.LocalDate;

public class Pengeluaran extends Transaksi {
    public Pengeluaran(int id, double jumlah, LocalDate tanggal, String keterangan, String kategori) {
        super(id, "Pengeluaran", jumlah, tanggal, keterangan, kategori);
    }

    @Override
    public boolean isPemasukan() {
        return false;
    }
}
