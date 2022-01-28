package com.java.singleton;

import java.awt.*;

public class Singleton {
    private static Singleton robot;
    private boolean penState; //false: up, true: down
    private Point coordinates; //coordinate of robot on table
    private String direction; // North, East, South, West

    //constructor
    public Singleton(){
        //initialise robot
        this.penState = false;
        this.coordinates = new Point(0,0);
        this.direction = "north";
    }

    //get the current instance of the robot
    public static Singleton getInstance(){
        if (robot == null)
            robot = new Singleton();
        return robot;
    }

    //getters
    public String getDirection() {
        return this.direction;
    }
    public boolean getPenState(){
        return this.penState;
    }
    public Point getCoordinates(){
        return this.coordinates;
    }

    //setters
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
    public void setPenState(boolean state){
        this.penState = state;
    }
    public void setCoordinates(Point p){
        this.coordinates = p;
    }

    //reinitialize the robot to the initial point
    public void reinitialize(){
        this.penState = false;
        this.coordinates = new Point(0,0);
        this.direction = "north";
    }
}