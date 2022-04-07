package com.java.singleton;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class TableTest {
    Table table;
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        table = new Table(10,10);
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    void TearDown() {
        System.setOut(standardOut);
    }

    @Test
    void testTableConstructor() {
        table = new Table(-5,-5);
        assertEquals(0, table.getHeight());
    }
    @Test
    void testIsOnTable() {
        //point on table
        Point validPoint = new Point(5,5);
        //point out of bound
        Point invalidPoint = new Point(11,11);
        assertAll(() -> assertTrue(table.isOnTable(validPoint)),        //Test valid point
                () -> assertFalse(table.isOnTable(invalidPoint)));      //test invalid point
    }

    @Test
    void testGetWidth() {
        assertEquals(10,table.getWidth());
    }

    @Test
    void testGetHeight() {
        assertEquals(10,table.getHeight());
    }

    @Test
    void testGetTableArray() {
        //get expected array
        int[][] tableArray = new int[10][10];

        for (int row = 0; row < 10; row++)
        {
            for (int col = 0; col < 10; col++)
            {
                tableArray[row][col] = 0;
            }
        }

        assertTrue(Arrays.deepEquals(tableArray, table.getTableArray()));
    }

    String table(boolean flag){
        //get expected output
        StringBuilder output = new StringBuilder();

        int[][] tableArray = table.getTableArray();
        tableArray[5][5] = 1;

        for (int row = tableArray.length-1; row >= 0; row--) {
            output.append(row + "| ");
            for (int col = 0; col < tableArray[row].length; col++) {
                if (row == 0 && col == 0)
                    if(flag)
                        output.append("↑ ");
                    else
                        output.append("↓ ");
                else if (tableArray[row][col] == 0)
                    output.append("  ");
                else
                    output.append("* ");
            }
            output.append("\n");
        }
        output.append("  ");
        for (int col = 0; col < tableArray.length; col++) {
            output.append("__");
        }
        output.append("\n   ");
        for (int col = 0; col < tableArray.length; col++) {
            output.append(col + " ");
        }

        return output.toString();
    }

    @Test
    void testPrintTable() {

        String output = table(true);

        Point point = new Point(5,5);
        table.writeTable(point,true);
        Point temp=new Point(0,0); // location of pen
        table.printTable(temp,false); // initially printing table with pen up at location 0,0
        assertEquals(output, outputStreamCaptor.toString());

        outputStreamCaptor.reset();
        output = table(false);
        table.printTable(temp,true);
        assertEquals(output, outputStreamCaptor.toString());
    }
}