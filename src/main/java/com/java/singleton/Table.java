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
        tableArray[0][0]=1;
    }

    //verify if a given coordinate is on the table
    public boolean isOnTable(Point coordinates) {
        double x = coordinates.getX();
        double y = coordinates.getY();
        return ((x<= width && x>=0) &&( y<=height && y>=0))?  true :  false;
    }

    //write on table if pen is holding up
    public void writeTable(Point coordinates) {
        tableArray[(int) coordinates.getX()][(int) coordinates.getY()]=1;
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
    public void printTable(Point coordinates,boolean x){


        /*
        for (int[] row : tableArray){
            for (int element : row){
                if(element == 0)
                    System.out.print("  ");
                else
                    System.out.print("* ");
            }
            System.out.print("\n");
        }
        */

        // prints the robot position along with the pen orientation
        if (x==false) { // if the pen is facing upwards
            for (int row = 0; row < tableArray.length; row++) {
                for (int col = 0; col < tableArray[row].length; col++) {
                    if (row == coordinates.getX() && col == coordinates.getY())
                        System.out.print("↑ ");
                    else if (tableArray[row][col] == 0)
                        System.out.print(". ");
                    else
                        System.out.print("* ");
                }
                System.out.print("\n");
            }
        }
        else {         // if the pen is facing downwards
            for (int row = 0; row < tableArray.length; row++) {
                for (int col = 0; col < tableArray[row].length; col++) {
                    if (row == coordinates.getX() && col == coordinates.getY())
                        System.out.print("↓ ");
                    else if (tableArray[row][col] == 0)
                        System.out.print(". ");
                    else
                        System.out.print("* ");
                }
                System.out.print("\n");

            }
        }

    }
}
