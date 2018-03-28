package com.example.zds_t.myapplication.addressSelector;

/**
 * Created by ZDS-T on 2018/1/30.
 */

public class AddressBean {

    private long id;
    private String address;

    public AddressBean(long id, String address){
        this.id = id;
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
