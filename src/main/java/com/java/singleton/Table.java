package com.java.singleton;

import java.awt.*;
import java.util.Arrays;

public class Table {
    private int width;
    private int height;
    private int[][] tableArray;

    public Table(int x, int y){
        setSize(x,y);
    }

    public void setSize(int x, int y){
        if (x>0 && y>0)
        {
            width = x;
            height = y;
        }
        tableArray = new int[x][y];

        //initialize table with 0s
        //2D arrays are row major, so always row first
        for (int row = 0; row < tableArray.length; row++)
        {
            for (int col = 0; col < tableArray[row].length; col++)
            {
                tableArray[row][col] = 0;
            }
        }

    }

    public boolean isOnTable(Point coordinates) {
        double x = coordinates.getX();
        double y = coordinates.getY();
        return ((x<= width && x>=0) &&( y<=height && y>=0))?  true :  false;
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

    public int[][] getTableArray(){
        return tableArray;
    }

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
