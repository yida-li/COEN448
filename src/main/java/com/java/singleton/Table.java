package com.java.singleton;

import java.awt.*;

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
        }

        //initialize array
        tableArray = new int[x][y];

        //initialize table with 0s
        for (int row = 0; row < tableArray.length; row++)
        {
            for (int col = 0; col < tableArray[row].length; col++)
            {
                tableArray[row][col] = 0;
            }
        }
    }

    //verify if a given coordinate is on the table
    public boolean isOnTable(Point coordinates) {
        double x = coordinates.getX();
        double y = coordinates.getY();
        return ((x<= width && x>=0) &&( y<=height && y>=0))?  true :  false;
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
    public void printTable(){
        for (int[] row : tableArray){
            for (int element : row){
                if(element == 0)
                    System.out.print("  ");
                else
                    System.out.print("* ");
            }
            System.out.print("\n");
        }
    }
}
