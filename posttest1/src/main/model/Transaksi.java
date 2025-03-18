package main.model;

import java.time.LocalDate;

public class Transaksi {
    private int id;
    private String jenis; 
    private String kategori;
    private double jumlah;
    private LocalDate tanggal;
    private String deskripsi;

    public Transaksi(int id, String jenis, String kategori, double jumlah, LocalDate tanggal, String deskripsi) {
        this.id = id;
        this.jenis = jenis;
        this.kategori = kategori;
        this.jumlah = jumlah;
        this.tanggal = tanggal;
        this.deskripsi = deskripsi;
    }

    public int getId() {
        return id;
    }

    public String getJenis() {
        return jenis;
    }

    public String getKategori() {
        return kategori;
    }

    public double getJumlah() {
        return jumlah;
    }

    public LocalDate getTanggal() {
        return tanggal;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    // Output
    @Override
    public String toString() {
        return String.format(
            "| %2d | %-5s | %-8s | %6.2f | %10s | %-14s |" +
            "\n---------------------------------------------------------------",
            id, jenis, kategori, jumlah, tanggal, deskripsi
        );
    }

    public static String getTableHeader() {
        return "----------------------------------------------------------------\n" +
               "| ID | Jenis | Kategori | Jumlah |  Tanggal   |    Deskripsi   |\n" +
               "----------------------------------------------------------------";
    }
}
