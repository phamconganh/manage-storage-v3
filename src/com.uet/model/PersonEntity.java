/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uet.model;

/**
 *
 * @author Storm Spirit
 */
public class PersonEntity {
    public String name;
    public String address;
    public String phone;
    public String createdAt;
    public int total;
    public String note;

    @Override
    public String toString(){
        return name + "-" + address + "-" + phone + "-" + createdAt + "-" + total + "-" + note;
    }
}
