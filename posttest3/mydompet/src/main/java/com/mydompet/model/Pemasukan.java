package com.mydompet.model;

import java.time.LocalDate;

public class Pemasukan extends Transaksi {
    public Pemasukan(int id, double jumlah, LocalDate tanggal, String keterangan, String kategori) {
        super(id, "Pemasukan", jumlah, tanggal, keterangan, kategori);
    }

    @Override
    public boolean isPemasukan() {
        return true;
    }
}
