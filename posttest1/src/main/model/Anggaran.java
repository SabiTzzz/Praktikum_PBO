package main.model;

import java.time.LocalDate;

public class Anggaran {
    private String nama;
    private LocalDate periodeMulai;
    private LocalDate periodeAkhir;
    private double jumlah;

    public Anggaran(String nama, LocalDate periodeMulai, LocalDate periodeAkhir, double jumlah) {
        this.nama = nama;
        this.periodeMulai = periodeMulai;
        this.periodeAkhir = periodeAkhir;
        this.jumlah = jumlah;
    }

    public String getNama() {
        return nama;
    }

    public LocalDate getPeriodeMulai() {
        return periodeMulai;
    }

    public LocalDate getPeriodeAkhir() {
        return periodeAkhir;
    }

    public double getJumlah() {
        return jumlah;
    }
}