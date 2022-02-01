package com.java.singleton;

import java.awt.*;
import java.util.Arrays;

public class Table {
    private int width;
    private int height;
    private int[][] tableArray;

    //constructor
    public Table(int x, int y){
        if (x>0 && y>0)
        {
            width = x;
            height = y;
        }else{
            //if size is negative, table size is 0
            width=0;
            height=0;
            x=0;
            y=0;
        }

        //initialize array
        tableArray = new int[x][y];

        //initialize table with 0s
        for (int[] ints : tableArray) {
            Arrays.fill(ints, 0);
        }

    }

    //verify if a given coordinate is on the table
    public boolean isOnTable(Point coordinates) {
        double x = coordinates.getX();
        double y = coordinates.getY();
        return (x < width && x >= 0) && (y < height && y >= 0);
    }

    //write on table if pen is holding up
    public void writeTable(Point coordinates, boolean penState) {
        if(penState){
            tableArray[(int) coordinates.getX()][(int) coordinates.getY()]=1;
        }
    }

    //getters
    public int getWidth(){
        return width;
    }
    public int getHeight(){
        return height;
    }
    public int[][] getTableArray(){
        return tableArray;
    }

    //printer
    //print the table
    public void printTable(Point coordinates,boolean penState){
        // prints the robot position along with the pen orientation
        for (int row = tableArray.length-1; row >= 0; row--) {
            System.out.print(row + "| ");
            for (int col = 0; col < tableArray[row].length; col++) {
                if (row == coordinates.getX() && col == coordinates.getY())
                    if(!penState)
                        System.out.print("↑ ");
                    else
                        System.out.print("↓ ");
                else if (tableArray[row][col] == 0)
                    System.out.print("  ");
                else
                    System.out.print("* ");
            }
            System.out.print("\n");
        }
        System.out.print("  ");
        for (int col = 0; col < tableArray.length; col++) {
            System.out.print("__");
        }
        System.out.print("\n   ");
        for (int col = 0; col < tableArray.length; col++) {
            System.out.print(col + " ");
        }
    }
}
