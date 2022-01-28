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
    void testIsOnTable() {
        //point on table
        Point validPoint = new Point(5,5);
        //point out of bound
        Point invalidPoint = new Point(11,11);
        assertAll(() -> assertEquals(true, table.isOnTable(validPoint)),
                () -> assertEquals(false, table.isOnTable(invalidPoint)));
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

    @Test
    void testPrintTable() {

        //get expected output
        String output = "";

        int[][] tableArray = table.getTableArray();
        for (int[] row : tableArray){
            for (int element : row){
                if(element == 0)
                    output +="  ";
                else
                    output +="* ";
            }
            output +="\n";
        }

        table.printTable();
        assertEquals(output, outputStreamCaptor.toString());
    }
}