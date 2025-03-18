package main.interfaces;

import main.service.*;
import main.model.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class MainMenu {
    private BufferedReader reader;
    private TransaksiService transaksiService;

    public MainMenu(BufferedReader reader, TransaksiService transaksiService) {
        this.reader = reader;
        this.transaksiService = transaksiService;
    }

    public void showLoginMenu() throws IOException {
        while (true) {
            System.out.println("\n==============================");
            System.out.println("| Selamat Datang di MyDompet |");
            System.out.println("==============================");
            System.out.println("[1] Login sebagai Pengguna");
            System.out.println("[2] Keluar");
            System.out.println("==============================");
            System.out.print("Pilih menu: ");
            int choice = Integer.parseInt(reader.readLine());

            if (choice == 1) {
                showUserMenu();
            } else if (choice == 2) {
                break;
            } else {
                System.out.println("Pilihan tidak valid.");
            }
        }
        }

        private void showUserMenu() throws IOException {
        while (true) {
            System.out.println("\n======================");
            System.out.println("[O] Menu Transaksi [O]");
            System.out.println("======================");
            System.out.println("[1] Tambah Transaksi");
            System.out.println("[2] Lihat Transaksi");
            System.out.println("[3] Update Transaksi");
            System.out.println("[4] Hapus Transaksi");
            System.out.println("[0] Keluar");
            System.out.println("======================");
            System.out.print("[?] Pilih menu: ");
            int choice = Integer.parseInt(reader.readLine());

            if (choice == 1) {
                while (true) {
                    try {
                        System.out.println("\n[C] Silahkan masukkan data transaksi (tentukan ID)");
                        System.out.print("ID: ");
                        int id = Integer.parseInt(reader.readLine());

                        if (id < 0 || transaksiService.idExists(id)) {
                            System.out.println("ID tidak valid atau sudah ada. Silakan masukkan ID yang berbeda.");
                            continue;
                        }

                        System.out.print("Jenis ([1]Pemasukan/[2]Pengeluaran): ");
                        String jenis = reader.readLine();

                        if (!jenis.equals("1") && !jenis.equals("2")) {
                            System.out.println("Jenis tidak valid. Silakan masukkan jenis yang benar.");
                            continue;
                        } else {
                            jenis = jenis.equals("1") ? "Pemasukan" : "Pengeluaran";
                        }

                        System.out.print("Kategori: ");
                        String kategori = reader.readLine();

                        System.out.print("Jumlah: ");
                        double jumlah = Double.parseDouble(reader.readLine());

                        System.out.print("Tanggal (yyyy-MM-dd): ");
                        LocalDate tanggal = LocalDate.parse(reader.readLine(), DateTimeFormatter.ISO_LOCAL_DATE);

                        System.out.print("Deskripsi: ");
                        String deskripsi = reader.readLine();

                        transaksiService.tambahTransaksi(id, jenis, kategori, jumlah, tanggal, deskripsi);
                        System.out.println("Transaksi berhasil ditambahkan.");

                        System.out.print("Apakah Anda ingin menambahkan transaksi lagi? (y/n): ");
                        String lanjut = reader.readLine();
                        if (!lanjut.equalsIgnoreCase("y")) {
                            break;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Input tidak valid. Silakan masukkan data yang benar.");
                    } catch (Exception e) {
                        System.out.println("Terjadi kesalahan: " + e.getMessage());
                    }
                }

            } else if (choice == 2) {
                if (transaksiService.getTransaksi().isEmpty()) {
                    System.out.println("[!] Tidak ada transaksi yang tersedia.");
                    continue;
                } else {
                    System.out.println("\n[R] Menampilkan Data Transaksi");
                    System.out.println(Transaksi.getTableHeader());
                    ArrayList<Transaksi> transaksiList = transaksiService.getTransaksi();
                        for (Transaksi transaksi : transaksiList) {
                            System.out.println(transaksi);
                    }
                }

            } else if (choice == 3) {
                System.out.println("\n[U] Silahkan masukkan data transaksi yang akan diupdate");
                System.out.print("Id Transaksi yang akan diupdate: ");
                int id = Integer.parseInt(reader.readLine());

                if (id < 0 || !transaksiService.idExists(id)) {
                    System.out.println("ID tidak valid atau tidak ada. Silakan masukkan ID yang benar.");
                    return;
                }

                System.out.print("Jenis ([1]Pemasukan/[2]Pengeluaran): ");
                String jenis = reader.readLine();

                if (!jenis.equals("1") && !jenis.equals("2")) {
                    System.out.println("Jenis tidak valid. Silakan masukkan jenis yang benar.");
                    continue;
                } else {
                    jenis = jenis.equals("1") ? "Pemasukan" : "Pengeluaran";
                }
                
                System.out.print("Kategori: ");
                String kategori = reader.readLine();

                System.out.print("Jumlah: ");
                double jumlah = Double.parseDouble(reader.readLine());

                System.out.print("Tanggal (yyyy-MM-dd): ");
                LocalDate tanggal = LocalDate.parse(reader.readLine(), DateTimeFormatter.ISO_LOCAL_DATE);

                System.out.print("Deskripsi: ");
                String deskripsi = reader.readLine();

                transaksiService.updateTransaksi(id, jenis, kategori, jumlah, tanggal, deskripsi);
                System.out.println("Transaksi berhasil diupdate.");

            } else if (choice == 4) {
                System.out.println("\n[D] Menghapus Transaksi");
                System.out.print("ID Transaksi yang akan dihapus: ");
                int id = Integer.parseInt(reader.readLine());

                if (id < 0 || !transaksiService.idExists(id)) {
                    System.out.println("ID tidak valid atau tidak ada. Silakan masukkan ID yang benar.");
                    return;
                }

                transaksiService.hapusTransaksi(id);
                System.out.println("Transaksi berhasil dihapus.");

            } else if (choice == 0) {
                break;
            } else {
                System.out.println("Pilihan tidak valid.");
            }
        }
    }
}
