package com.boc.autoparking.model;

import java.io.Serializable;

public class Coordinate implements Serializable {

    private int posX;

    private int posY;



    public Coordinate(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
    }

    public void changeVertically(int unit) {
        this.posY = this.posY + unit;
    }

    public void changeHorizontally(int unit) {
        this.posX = this.posX + unit;
    }


    @Override
    public String toString() {
        return posX + ", " + posY;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

}
