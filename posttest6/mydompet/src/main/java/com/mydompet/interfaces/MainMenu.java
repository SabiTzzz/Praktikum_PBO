package com.mydompet.interfaces;

import com.mydompet.service.*;
import com.mydompet.model.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public final class MainMenu {
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
        int choice = -1;
        try {
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
        } catch (Exception e) {
            System.out.println("[!] Error: " + e.getMessage());
        }
    }
    
    private void showUserMenu() throws Exception {
        boolean running = true;
        while (running) {
            try {
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
                        running = false;
                    }
                    default -> System.out.println("Pilihan tidak valid.");
                }
            } catch (NumberFormatException e) {
                System.out.println("[!] Input harus berupa angka.");
            } catch (Exception e) {
                System.out.println("[!] Error: " + e.getMessage());
            } finally {
                System.out.println("[i] Kembali ke menu utama...");
            }
        }
    }
    

    private void tambahTransaksi() {
        while (true) {
            try {
                System.out.println("\n[C] Silahkan masukkan data transaksi (tentukan ID)");
                int id = bacaIdTransaksi(true);
                String jenis = bacaJenisTransaksi();
                String kategori = bacaInputString("Kategori: ");
                double jumlah = bacaInputDouble("Jumlah: ");
                LocalDate tanggal = bacaInputTanggal("Tanggal (yyyy-MM-dd): ");
                String deskripsi = bacaInputString("Deskripsi: ", "-");
    
                transaksiService.tambahTransaksi(id, jenis, kategori, jumlah, tanggal, deskripsi);
                System.out.println("Transaksi berhasil ditambahkan.");
            } catch (Exception e) {
                System.out.println("[!] Error: " + e.getMessage());
            }
    
            String lagi = bacaInputString("Tambah transaksi lagi? (y/n): ");
            if (!lagi.equalsIgnoreCase("y")) break;
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
        try {
            System.out.println("\n[U] Masukkan data untuk update transaksi:");
            int id = bacaIdTransaksi(false);
            String jenis = bacaJenisTransaksi();
            String kategori = bacaInputString("Kategori: ");
            double jumlah = bacaInputDouble("Jumlah: ");
            LocalDate tanggal = bacaInputTanggal("Tanggal (yyyy-MM-dd): ");
            String deskripsi = bacaInputString("Deskripsi: ", "-");
    
            transaksiService.updateTransaksi(id, jenis, kategori, jumlah, tanggal, deskripsi);
            System.out.println("Transaksi berhasil diupdate.");
        } catch (Exception e) {
            System.out.println("[!] Gagal update: " + e.getMessage());
        }
    }
    
    private void hapusTransaksi() {
        try {
            System.out.println("\n[D] Masukkan ID transaksi untuk dihapus:");
            int id = bacaIdTransaksi(false);
            transaksiService.hapusTransaksi(id);
            System.out.println("Transaksi berhasil dihapus.");
        } catch (Exception e) {
            System.out.println("[!] Gagal hapus: " + e.getMessage());
        }
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

    private int bacaIdTransaksi(boolean isNew) throws Exception {
        int id = bacaInputInt("ID: ");
        if (isNew && transaksiService.idExists(id)) {
            throw new Exception("ID sudah ada.");
        } else if (!isNew && !transaksiService.idExists(id)) {
            throw new Exception("ID tidak ditemukan.");
        }
        return id;
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
        laporanService.tampilkanInformasi(laporan); 
    }
    
}
