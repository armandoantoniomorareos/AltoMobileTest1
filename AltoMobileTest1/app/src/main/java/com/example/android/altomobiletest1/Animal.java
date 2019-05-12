package com.example.android.altomobiletest1;

public class Animal
{

    private String name, pictureURL;
    private int life;
    private int id;

    public Animal(int life, String name, int id, String pictureURL){
        this.life = life;
        this.name = name;
        this.id = id;
        this.pictureURL = pictureURL;
    }

    public String getName() {
        return name;
    }

    public String getPictureURL() {
        return pictureURL;
    }

    public int getLife() {
        return life;
    }


}
