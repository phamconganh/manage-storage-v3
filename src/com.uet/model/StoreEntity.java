package com.uet.model;

public class StoreEntity {
  public String name;
  public String code;
  public String person;
  public String createdAt;
  public String type;
  public int quantity;
  public int price;
  public int total;
  public String note;

  @Override
  public String toString(){
    return name + "-" + code + "-" + person + "-" + createdAt + "-" + type + "-" + quantity + "-" + price + "-" + total;
  }
}
