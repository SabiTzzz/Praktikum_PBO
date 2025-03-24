package com.mydompet.model;

import java.util.List;

public class Laporan {
    private double totalPemasukan;
    private double totalPengeluaran;

    // Constructor
    public Laporan(List<Transaksi> transaksiList) {
        hitungTotal(transaksiList);
    }

    // Private method untuk menghitung total pemasukan & pengeluaran
    private void hitungTotal(List<Transaksi> transaksiList) {
        for (Transaksi transaksi : transaksiList) {
            if ("Pemasukan".equalsIgnoreCase(transaksi.getJenis())) {
                totalPemasukan += transaksi.getJumlah();
            } else if ("Pengeluaran".equalsIgnoreCase(transaksi.getJenis())) {
                totalPengeluaran += transaksi.getJumlah();
            }
        }
    }

    // Public getter agar bisa diakses oleh service
    public double getTotalPemasukan() {
        return totalPemasukan;
    }

    public double getTotalPengeluaran() {
        return totalPengeluaran;
    }
}
