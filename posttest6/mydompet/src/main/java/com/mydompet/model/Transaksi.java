package com.mydompet.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class Transaksi {
    protected final int id;
    protected String jenis;
    protected double jumlah;
    protected LocalDate tanggal;
    protected String keterangan;
    protected String kategori;
    
    public abstract boolean isPemasukan();

    public Transaksi(int id, String jenis, double jumlah, LocalDate tanggal, String keterangan, String kategori) {
        this.id = id;
        this.jenis = jenis;
        this.jumlah = jumlah;
        this.tanggal = tanggal;
        this.keterangan = keterangan;
        this.kategori = kategori;
    }

    public Transaksi(int id, String jenis, double jumlah, String keterangan, String kategori) {
        this(id, jenis, jumlah, LocalDate.now(), keterangan, kategori);
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

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return String.format(
            "| %3d | %-10s | %-11s | %12.2f | %-10s | %-20s |\n" +
            "--------------------------------------------------------------------------------------",
            id, jenis, kategori, jumlah, tanggal.format(formatter), keterangan
        );
    }

    public final static String getTableHeader() {
        return "--------------------------------------------------------------------------------------\n" +
               "| ID  |   Jenis     |  Kategori   |   Jumlah     |  Tanggal   |   Keterangan         |\n" +
               "--------------------------------------------------------------------------------------";
    }
}