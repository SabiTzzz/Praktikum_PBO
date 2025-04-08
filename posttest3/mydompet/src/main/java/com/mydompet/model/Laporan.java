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
            if (transaksi.isPemasukan()) {
                totalPemasukan += transaksi.getJumlah();
            } else {
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

    public double getSaldo() {
        return totalPemasukan - totalPengeluaran;
    }
}
