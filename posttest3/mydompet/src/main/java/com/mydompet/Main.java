package com.mydompet;

import com.mydompet.repository.*;
import com.mydompet.service.*;

import java.time.LocalDate;

import com.mydompet.interfaces.*;

public class Main {
    public static void main(String[] args) {
        TransaksiRepo transaksiRepo = new TransaksiRepo();
        TransaksiService transaksiService = new TransaksiService(transaksiRepo);
        LaporanRepo laporanRepo = new LaporanRepo();
        LaporanService laporanService = new LaporanService(laporanRepo, transaksiRepo);

        transaksiService.tambahTransaksi(1, "Pemasukan", "Gaji", 5000000, LocalDate.of(2025, 3, 1), "Gaji Bulanan");
        transaksiService.tambahTransaksi(2, "Pengeluaran", "Makan", 50000, LocalDate.of(2025, 3, 2), "Makan malam");
        transaksiService.tambahTransaksi(3, "Pemasukan", "Bonus", 1000000, LocalDate.of(2025, 3, 5), "Bonus proyek");

        MainMenu mainMenu = new MainMenu(laporanService, transaksiService);
        mainMenu.showLoginMenu();
    }
}
