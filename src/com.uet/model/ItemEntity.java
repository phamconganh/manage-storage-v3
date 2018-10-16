package com.uet.model;

public class ItemEntity {
    public String name;
    public String code;
    public String type;
    public String provider;
    public int quantity;
    public int priceImport;
    public int priceExport;
    public String note;

    @Override
    public String toString(){
        return name + "-" + code + "-" + type + "-" + provider + "-" + quantity + "-" + priceImport + "-" + priceExport + "-" + note;
    }
}
