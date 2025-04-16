package com.mydompet.service;

import com.mydompet.model.Laporan;
import com.mydompet.repository.LaporanRepo;
import com.mydompet.repository.TransaksiRepo;
import java.util.List;
import com.mydompet.model.Transaksi;

public class LaporanService {
    private LaporanRepo laporanRepo;
    private TransaksiRepo transaksiRepo;

    public LaporanService(LaporanRepo laporanRepo, TransaksiRepo transaksiRepo) {
        this.laporanRepo = laporanRepo;
        this.transaksiRepo = transaksiRepo;
    }

    // Public method untuk generate laporan
    public Laporan generateLaporan() {
        List<Transaksi> transaksiList = transaksiRepo.getTransaksi();
        return laporanRepo.buatLaporan(transaksiList);
    }
}
