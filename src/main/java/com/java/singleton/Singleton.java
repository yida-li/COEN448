package com.java.singleton;

import java.awt.*;

public class Singleton {
    private static Singleton robot;
    private boolean penState; //false: up, true: down
    private Point coordinates;
    private String direction;

    public Singleton(){
        this.penState = false;
        coordinates = new Point(0,0);
        direction = "north";
    }

    public static Singleton getInstance(){
        if (robot == null)
            robot = new Singleton();

        return robot;
    }
    public String getDirection() {
        return this.direction;
    }

    public void setDirectionNorth(){
        this.direction = "north";
    }

    public void setDirectionSouth(){
        this.direction = "south";
    }

    public void setDirectionEast(){
        this.direction = "east";
    }

    public void setDirectionWest(){
        this.direction = "west";
    }

    public boolean getPenState(){
        return this.penState;
    }

    public void changePenState(){
        this.penState ^= true;
    }

    public Point getCoordinates(){
        return this.coordinates;
    }

    public void setCoordinates(Point p){
        this.coordinates = p;
    }
}