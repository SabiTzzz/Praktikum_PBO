package main.model;

import java.util.Date;

public class Laporan {
    private Date periodeAwal;
    private Date periodeAkhir;

    public Laporan(Date periodeAwal, Date periodeAkhir) {
        this.periodeAwal = periodeAwal;
        this.periodeAkhir = periodeAkhir;
    }

    public Date getPeriodeAwal() {
        return periodeAwal;
    }

    public Date getPeriodeAkhir() {
        return periodeAkhir;
    }
}