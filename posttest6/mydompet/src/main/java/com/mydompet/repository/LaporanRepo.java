package com.mydompet.repository;

import com.mydompet.model.Laporan;
import com.mydompet.model.Transaksi;
import java.util.List;

public class LaporanRepo {
    public Laporan buatLaporan(List<Transaksi> transaksiList) {
        return new Laporan(transaksiList);
    }
}
