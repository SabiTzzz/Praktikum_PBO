package com.mydompet.model;

import java.time.LocalDate;

public class Rutinan extends Transaksi {
    public Rutinan(int id, double jumlah, LocalDate tanggal, String keterangan, String kategori) {
        super(id, "Rutinan", jumlah, tanggal, keterangan, kategori);
    }

    @Override
    public boolean isPemasukan() {
        return false;
    }
}
