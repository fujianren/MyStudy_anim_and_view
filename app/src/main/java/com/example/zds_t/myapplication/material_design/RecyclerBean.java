package com.example.zds_t.myapplication.material_design;

import java.io.Serializable;

/**
 * Created by ZDS-T on 2018/1/11.
 */

class RecyclerBean implements Serializable{
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getImglarge() {
        return imglarge;
    }

    public void setImglarge(int imglarge) {
        this.imglarge = imglarge;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getAuthor_intro() {
        return author_intro;
    }

    public void setAuthor_intro(String author_intro) {
        this.author_intro = author_intro;
    }

    public String getCatalog() {
        return catalog;
    }

    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }

    private String title;
    private int img;
    private String info;
    private int imglarge;

    private String summary;
    private String author_intro;
    private String catalog;
}
