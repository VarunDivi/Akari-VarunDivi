package com.comp301.a09akari.model;

public class Lamp {

    private int r;
    private int c;

    public Lamp(int r, int c){
        if(r >= 0 && c >= 0){
            this.r = r;
            this.c = c;
        }
        else{
            throw new IndexOutOfBoundsException();
        }
    }


    public int getRow(){
        return this.r;
    }

    public int getCol(){
        return this.c;
    }

}
