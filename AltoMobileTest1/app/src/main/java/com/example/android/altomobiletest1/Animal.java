package com.example.android.altomobiletest1;

/*
 * This class is not needed
 */
import java.util.ArrayList;
import java.util.List;

public class Animal
{
    private int life;
    private String name;
    private int id;
    private String pictureURL;
    private List dataset = new ArrayList();

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
