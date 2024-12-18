package model;

public class Student {
    private String msv;
    private String hoTen;
    private String maLop;
    private double diemLyThuyet;
    private double diemThucHanh;
    private double diemTrungBinh;
    private String ketQua;

    public Student(String msv, String hoTen, String maLop, double diemLyThuyet, double diemThucHanh) {
        this.msv = msv;
        this.hoTen = hoTen;
        this.maLop = maLop;
        this.diemLyThuyet = diemLyThuyet;
        this.diemThucHanh = diemThucHanh;
        this.diemTrungBinh = (diemLyThuyet + diemThucHanh) / 2;
        this.ketQua = diemTrungBinh >= 5 ? "Passed" : "Failed";
    }

    public String getMsv() {
        return msv;
    }

    public void setMsv(String msv) {
        this.msv = msv;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getMaLop() {
        return maLop;
    }

    public void setMaLop(String maLop) {
        this.maLop = maLop;
    }

    public double getDiemLyThuyet() {
        return diemLyThuyet;
    }

    public void setDiemLyThuyet(double diemLyThuyet) {
        this.diemLyThuyet = diemLyThuyet;
    }

    public double getDiemThucHanh() {
        return diemThucHanh;
    }

    public void setDiemThucHanh(double diemThucHanh) {
        this.diemThucHanh = diemThucHanh;
    }

    public double getDiemTrungBinh() {
        return diemTrungBinh;
    }

    public void setDiemTrungBinh(double diemTrungBinh) {
        this.diemTrungBinh = diemTrungBinh;
    }

    public String getKetQua() {
        return ketQua;
    }

    public void setKetQua(String ketQua) {
        this.ketQua = ketQua;
    }

    @Override
    public String toString() {
        return msv + ";" + hoTen + ";" + maLop + ";" + diemLyThuyet + ";" + diemThucHanh + ";" + diemTrungBinh + ";" + ketQua;
    }
}
