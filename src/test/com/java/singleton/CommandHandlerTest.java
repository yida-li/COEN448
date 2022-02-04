package com.java.singleton;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class CommandHandlerTest {

    CommandHandler commandHandler;
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        commandHandler = new CommandHandler();
        System.setOut(new PrintStream(outputStreamCaptor));
        commandHandler.initializeSystem(10);
    }

    @AfterEach
    void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    void ui() {
        //todo
        InputStream stdin = System.in;
        System.setIn(new ByteArrayInputStream("i 10".getBytes()));

        //commandHandler.ui();

        //System.setIn(stdin);
    }

    @Test
    void intValueGiven() {
        //gets int value after a letter in a string
        String val = "i10";
        String val1 = "i";
        String val2 = "i 5";
        String val3 = "i -5";
        assertAll(() -> assertEquals(10, CommandHandler.intValueGiven(val)), //test valid number with no space
                () -> assertEquals(-1,commandHandler.intValueGiven(val1)),   //test no number given
                () -> assertEquals(5,commandHandler.intValueGiven(val2)),    //test valid number with space
                () -> assertEquals(-1,commandHandler.intValueGiven(val3)));  //test invalid number
    }

    @Test
    void removeBlankSpace() {
        String val = " I";
        StringBuilder sb = new StringBuilder(val);

        assertEquals("I",commandHandler.removeBlankSpace(sb));
    }

    @Test
    void isNumeric() {
        String numb = "10";
        String notNumb = "A";
        assertAll(() -> assertTrue(commandHandler.isNumeric(numb)),    //test valid number
                () -> assertFalse(commandHandler.isNumeric(notNumb))); //test invalid number
    }

    @Test
    void printPosition() {
        String mes = "Position: " + 0 + ", " + 0 + " - Pen: " + "up" + " - Facing: " + "north";
        String mes2 = "Position: " + 0 + ", " + 0 + " - Pen: " + "down" + " - Facing: " + "north";
        String mes3 = "Position: " + 0 + ", " + 0 + " - Pen: " + "down" + " - Facing: " + "east";
        outputStreamCaptor.reset();
        commandHandler.printPosition();
        assertEquals(mes,outputStreamCaptor.toString().trim());     //test initial position
        commandHandler.penDown();
        outputStreamCaptor.reset();
        commandHandler.printPosition();
        assertEquals(mes2,outputStreamCaptor.toString().trim());    //test position with pen down
        commandHandler.turnRight();
        outputStreamCaptor.reset();
        commandHandler.printPosition();
        assertEquals(mes3,outputStreamCaptor.toString().trim());    //test position facing east

    }

    @Test
    void printTable() {
        //get expected output
        StringBuilder output = new StringBuilder();

        int[][] tableArray;
        //initialize array
        tableArray = new int[10][10];

        //initialize table with 0s
        for (int[] ints : tableArray) {
            Arrays.fill(ints, 0);
        }

        //get initial table
        for (int row = tableArray.length-1; row >= 0; row--) {
            output.append(row + "| ");
            for (int col = 0; col < tableArray[row].length; col++) {
                if (row == 0 && col == 0)
                    output.append("↑ ");
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

        outputStreamCaptor.reset();
        commandHandler.printTable();
        assertEquals(output.toString(), outputStreamCaptor.toString());
    }

    @Test
    void moveRobot() {
        Point coo1 = commandHandler.robot.getCoordinates();

        coo1.move(coo1.x+1,coo1.y);
        commandHandler.moveRobot(1);
        assertEquals(coo1, commandHandler.robot.getCoordinates());

        commandHandler.turnRight();
        coo1.move(coo1.x,coo1.y+1);
        commandHandler.moveRobot(1);
        assertEquals(coo1, commandHandler.robot.getCoordinates());

        commandHandler.turnRight();
        coo1.move(coo1.x-1,coo1.y);
        commandHandler.moveRobot(1);
        assertEquals(coo1, commandHandler.robot.getCoordinates());

        commandHandler.turnRight();
        coo1.move(coo1.x,coo1.y-1);
        commandHandler.moveRobot(1);
        assertEquals(coo1, commandHandler.robot.getCoordinates());
    }

    @Test
    void turnLeft() {
        commandHandler.turnLeft();
        assertEquals("west", commandHandler.robot.getDirection()); //test left turn if initially facing west
        commandHandler.turnLeft();
        assertEquals("south", commandHandler.robot.getDirection()); //test left turn if initially facing south
        commandHandler.turnLeft();
        assertEquals("east", commandHandler.robot.getDirection()); //test left turn if initially facing east
        commandHandler.turnLeft();
        assertEquals("north", commandHandler.robot.getDirection()); //test left turn if initially facing north
    }

    @Test
    void turnRight() {
        commandHandler.turnRight();
        assertEquals("east", commandHandler.robot.getDirection());  //test right turn if initially facing east
        commandHandler.turnRight();
        assertEquals("south", commandHandler.robot.getDirection());  //test right turn if initially facing south
        commandHandler.turnRight();
        assertEquals("west", commandHandler.robot.getDirection());  //test right turn if initially facing west
        commandHandler.turnRight();
        assertEquals("north", commandHandler.robot.getDirection());  //test right turn if initially facing north

    }

    @Test
    void penDown() {
        //test pen initially up
        commandHandler.penDown();
        assertTrue(commandHandler.robot.getPenState());

        //test pen already down
        outputStreamCaptor.reset();
        commandHandler.penDown();
        assertEquals("Pen direction already down", outputStreamCaptor.toString().trim());
    }

    @Test
    void penUp() {
        //test pen initially up
        outputStreamCaptor.reset();
        commandHandler.penUp();
        assertEquals("Pen direction already up", outputStreamCaptor.toString().trim());

        //test pen already up
        commandHandler.robot.setPenState(true);
        commandHandler.penUp();
        assertFalse(commandHandler.robot.getPenState());
    }

    @Test
    void initializeSystem() {
        //test with valid table size
        commandHandler.initializeSystem(5);
        assertTrue(commandHandler.initialized);

        //test with invalid table size
        outputStreamCaptor.reset();
        commandHandler.initializeSystem(1);
        assertEquals("Please choose a size bigger or equal to 2", outputStreamCaptor.toString().trim());
    }
}