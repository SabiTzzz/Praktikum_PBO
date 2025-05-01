package com.mydompet.service;

import com.mydompet.interfaces.LaporanInterface;
import com.mydompet.model.Laporan;
import com.mydompet.repository.LaporanRepo;
import com.mydompet.repository.TransaksiRepo;
import java.util.List;
import com.mydompet.model.Transaksi;

public class LaporanService implements LaporanInterface  {
    private LaporanRepo laporanRepo;
    private TransaksiRepo transaksiRepo;

    public LaporanService(LaporanRepo laporanRepo, TransaksiRepo transaksiRepo) {
        this.laporanRepo = laporanRepo;
        this.transaksiRepo = transaksiRepo;
    }

    @Override
    public Laporan generateLaporan() {
        List<Transaksi> transaksiList = transaksiRepo.getTransaksi();
        return laporanRepo.buatLaporan(transaksiList);
    }

    @Override
    public void tampilkanInformasi(Laporan laporan) {
        Laporan.header();
        System.out.println("Total Pemasukan  : Rp" + laporan.getTotalPemasukan());
        System.out.println("Total Pengeluaran: Rp" + laporan.getTotalPengeluaran());
        System.out.println("Sisa Saldo       : Rp" + laporan.getSaldo());
    }
}
