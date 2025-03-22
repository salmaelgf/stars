package com.example.recycleview.beans;

public class Star {

    private int id;
    private String name;
    private String img;
    private Float star;

    private static int comp;

    public Star(String name, String img, Float star) {
        this.id = ++comp;
        this.name = name;
        this.img = img;
        this.star = star;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Float getStar() {
        return star;
    }

    public void setStar(Float star) {
        this.star = star;
    }

    public static int getComp() {
        return comp;
    }

    public static void setComp(int comp) {
        Star.comp = comp;
    }
}
