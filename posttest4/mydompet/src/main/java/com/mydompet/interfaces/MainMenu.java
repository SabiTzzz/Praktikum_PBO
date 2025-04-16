    package com.mydompet.interfaces;

    import com.mydompet.service.*;
    import com.mydompet.model.*;
    import java.io.BufferedReader;
    import java.io.InputStreamReader;
    import java.time.LocalDate;
    import java.time.format.DateTimeFormatter;
    import java.util.ArrayList;

    public class MainMenu {
        public LaporanService laporanService;
        private TransaksiService transaksiService;
        protected InputStreamReader isr;
        BufferedReader br;

        public MainMenu(LaporanService laporanService, TransaksiService transaksiService) {
            this.laporanService = laporanService;
            this.transaksiService = transaksiService;
            this.isr = new InputStreamReader(System.in);
            this.br = new BufferedReader(isr);
        }

        public void showLoginMenu() {
            int choice;
            do {
                System.out.println("\n==============================");
                System.out.println("| Selamat Datang di MyDompet |");
                System.out.println("==============================");
                System.out.println("[1] Masuk");
                System.out.println("[2] Keluar");
                System.out.println("==============================");
                choice = bacaInputInt("Pilih menu: ");

                if (choice == 1) {
                    showUserMenu();
                } else if (choice != 2) {
                    System.out.println("Pilihan tidak valid.");
                }
            } while (choice != 2);
        }

        private void showUserMenu() {
            while (true) {
                System.out.println("\n======================");
                System.out.println("[O] Menu Transaksi [O]");
                System.out.println("======================");
                System.out.println("[1] Tambah Transaksi");
                System.out.println("[2] Lihat Transaksi");
                System.out.println("[3] Update Transaksi");
                System.out.println("[4] Hapus Transaksi");
                System.out.println("[5] Laporan Keuangan");
                System.out.println("[0] Keluar");
                System.out.println("======================");

                int choice = bacaInputInt("[?] Pilih menu: ");

                switch (choice) {
                    case 1 -> tambahTransaksi();
                    case 2 -> lihatTransaksi();
                    case 3 -> updateTransaksi();
                    case 4 -> hapusTransaksi();
                    case 5 -> tampilkanLaporan();
                    case 0 -> {
                        return;
                    }
                    default -> System.out.println("Pilihan tidak valid.");
                }
            }
        }

        private void tambahTransaksi() {
            while (true) {
                System.out.println("\n[C] Silahkan masukkan data transaksi (tentukan ID)");

                int id = bacaIdTransaksi(true);
                if (id == -1) return;

                String jenis = bacaJenisTransaksi();
                String kategori = bacaInputString("Kategori: ");
                double jumlah = bacaInputDouble("Jumlah: ");
                System.out.print("[i] Enter untuk tanggal hari ini");
                LocalDate tanggal = bacaInputTanggal("\nTanggal (yyyy-MM-dd): ");
                System.out.println("Tanggal yang dipakai: " + tanggal);
                String deskripsi = bacaInputString("Deskripsi: ", "-");

                transaksiService.tambahTransaksi(id, jenis, kategori, jumlah, tanggal, deskripsi);
                System.out.println("Transaksi berhasil ditambahkan.");

                if (!bacaInputString("Tambah transaksi lagi? (y/n): ").equalsIgnoreCase("y")) {
                    break;
                }
            }
        }

        private void lihatTransaksi() {
            ArrayList<Transaksi> transaksiList = transaksiService.getTransaksi();
            if (transaksiList.isEmpty()) {
                System.out.println("[!] Tidak ada transaksi yang tersedia.");
                return;
            }
            System.out.println("\n[R] Menampilkan Data Transaksi");
            System.out.println(Transaksi.getTableHeader());
            for (Transaksi transaksi : transaksiList) {
                System.out.println(transaksi);
            }
        }

        private void updateTransaksi() {
            System.out.println("\n[U] Silahkan masukkan data transaksi yang akan diupdate");

            int id = bacaIdTransaksi(false);
            if (id == -1) return;

            String jenis = bacaJenisTransaksi();
            String kategori = bacaInputString("Kategori: ");
            double jumlah = bacaInputDouble("Jumlah: ");
            System.out.print("[i] Enter untuk tanggal hari ini");
            LocalDate tanggal = bacaInputTanggal("\nTanggal (yyyy-MM-dd): ");
            System.out.println("Tanggal yang dipakai: " + tanggal);
            String deskripsi = bacaInputString("Deskripsi: ", "-");

            transaksiService.updateTransaksi(id, jenis, kategori, jumlah, tanggal, deskripsi);
            System.out.println("Transaksi berhasil diupdate.");
        }

        private void hapusTransaksi() {
            System.out.println("\n[D] Menghapus Transaksi");
            int id = bacaIdTransaksi(false);
            if (id == -1) return;

            transaksiService.hapusTransaksi(id);
            System.out.println("Transaksi berhasil dihapus.");
        }

        private int bacaInputInt(String prompt) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(br.readLine());
            } catch (Exception e) {
                System.out.println("Input tidak valid.");
                return bacaInputInt(prompt);
            }
        }

        private double bacaInputDouble(String prompt) {
            System.out.print(prompt);
            try {
                return Double.parseDouble(br.readLine());
            } catch (Exception e) {
                System.out.println("Input tidak valid.");
                return bacaInputDouble(prompt);
            }
        }

        private String bacaInputString(String prompt, String defaultValue) {
            System.out.print(prompt);
            try {
                String input = br.readLine();
                if (input.isBlank()) {
                    return defaultValue;
                }
                return input;
            } catch (Exception e) {
                System.out.println("Input tidak valid.");
                return bacaInputString(prompt, defaultValue);
            }
        }

        private String bacaInputString(String prompt) {
            return bacaInputString(prompt, "Umum");
        }

        private LocalDate bacaInputTanggal(String prompt) {
            System.out.print(prompt);
            try {
                String input = br.readLine();
                if (input.isBlank()) {
                    return LocalDate.now();
                }
                return LocalDate.parse(input, DateTimeFormatter.ISO_LOCAL_DATE);
            } catch (Exception e) {
                System.out.println("Input tidak valid. Gunakan format yyyy-MM-dd (contoh: 2025-04-16).");
                return bacaInputTanggal(prompt);
            }
        }

        private int bacaIdTransaksi(boolean isNew) {
            int id;
            do {
                id = bacaInputInt("ID: ");
                if (isNew && transaksiService.idExists(id)) {
                    System.out.println("ID sudah ada. Silakan masukkan ID yang berbeda.");
                } else if (!isNew && !transaksiService.idExists(id)) {
                    System.out.println("ID tidak ditemukan. Masukkan ID yang benar.");
                } else {
                    return id;
                }
            } while (true);
        }

        private String bacaJenisTransaksi() {
            String jenis;
            do {
                jenis = bacaInputString("Jenis ([1]Pemasukan/[2]Pengeluaran/[3]Rutinan): ");
                if (jenis.equals("1")) return " Pemasukan ";
                if (jenis.equals("2")) return "Pengeluaran";
                if (jenis.equals("3")) return "Rutinan";
                System.out.println("Jenis tidak valid. Silakan masukkan jenis yang benar.");
            } while (true);
        }

        protected void tampilkanLaporan() {
            Laporan laporan = laporanService.generateLaporan();
            System.out.println("\n[L] Laporan Keuangan");
            System.out.println("===============================================");
            System.out.println("Total Pemasukan  : Rp" + laporan.getTotalPemasukan());
            System.out.println("Total Pengeluaran: Rp" + laporan.getTotalPengeluaran());
            System.out.println("Sisa Saldo       : Rp" + laporan.getSaldo());
            System.out.println("===============================================");
        }
    }
