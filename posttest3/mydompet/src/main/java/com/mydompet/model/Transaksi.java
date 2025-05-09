package com.mydompet.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Transaksi {
    protected int id;
    protected String jenis;
    protected double jumlah;
    protected LocalDate tanggal;
    protected String keterangan;
    protected String kategori;

    public Transaksi(int id, String jenis, double jumlah, LocalDate tanggal, String keterangan, String kategori) {
        this.id = id;
        this.jenis = jenis;
        this.jumlah = jumlah;
        this.tanggal = tanggal;
        this.keterangan = keterangan;
        this.kategori = kategori;
    }

    // Getter & Setter
    public int getId() {
        return id;
    }

    public String getJenis() {
        return jenis;
    }

    public double getJumlah() {
        return jumlah;
    }

    public LocalDate getTanggal() {
        return tanggal;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public String getKategori() {
        return kategori;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public void setJumlah(double jumlah) {
        this.jumlah = jumlah;
    }

    public void setTanggal(LocalDate tanggal) {
        this.tanggal = tanggal;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public boolean isPemasukan() {
        return false; 
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return String.format(
            "| %3d | %-10s | %-10s | %12.2f | %-10s | %-15s |\n" +
            "--------------------------------------------------------------------------------------",
            id, jenis, kategori, jumlah, tanggal.format(formatter), keterangan
        );
    }

    public static String getTableHeader() {
        return "--------------------------------------------------------------------------------------\n" +
               "| ID  |   Jenis     |  Kategori   |   Jumlah     |  Tanggal   |   Keterangan     |\n" +
               "--------------------------------------------------------------------------------------";
    }
}