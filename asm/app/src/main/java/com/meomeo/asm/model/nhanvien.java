package com.meomeo.asm.model;

import java.io.Serializable;

public class nhanvien implements Serializable {
    private String maNv;
    private String name;
    private String phongBan;

    public nhanvien(String maNv, String name, String phongBan) {
        this.maNv = maNv;
        this.name = name;
        this.phongBan = phongBan;
    }

    public String getMaNv() {
        return maNv;
    }

    public String getName() {
        return name;
    }

    public String getPhongBan() {
        return phongBan;
    }

    public void setMaNv(String maNv) {
        this.maNv = maNv;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhongBan(String phongBan) {
        this.phongBan = phongBan;
    }
}
