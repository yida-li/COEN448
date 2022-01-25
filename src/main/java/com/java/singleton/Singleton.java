package com.java.singleton;

import java.awt.*;

public class Singleton {
    private static Singleton robot;
    private boolean penState;
    private Point coordinates;

    public Singleton(){
        this.penState = false;
        coordinates = new Point(0,0);
    }

    public static Singleton getInstance(){
        if (robot == null)
            robot = new Singleton();

        return robot;
    }

    public boolean getPenState(){
        return this.penState;
    }

    public void setPenState(){
        this.penState ^= true;
    }

    public Point getCoordinates(){
        return this.coordinates;
    }

    public void setCoordinates(Point p){
        this.coordinates = p;
    }
}