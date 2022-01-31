package com.java.singleton;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
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

    }

    @Test
    void intValueGiven() {
        //gets int value after a letter in a string
        String val = "i10";
        String val1 = "i";
        String val2 = "i 5";
        String val3 = "i -5";
        assertAll(() -> assertEquals(10, CommandHandler.intValueGiven(val)),
                () -> assertEquals(-1,commandHandler.intValueGiven(val1)),
                () -> assertEquals(5,commandHandler.intValueGiven(val2)),
                () -> assertEquals(-1,commandHandler.intValueGiven(val3)));
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
        assertAll(() -> assertTrue(commandHandler.isNumeric(numb)),
                () -> assertFalse(commandHandler.isNumeric(notNumb)));
    }

    @Test
    void printPosition() {
        String mes = "Position: " + 0 + ", " + 0 + " - Pen: " + "up" + " - Facing: " + "north";
        String mes2 = "Position: " + 0 + ", " + 0 + " - Pen: " + "down" + " - Facing: " + "north";
        outputStreamCaptor.reset();
        commandHandler.printPosition();
        assertEquals(mes,outputStreamCaptor.toString().trim());
        commandHandler.penDown();
        outputStreamCaptor.reset();
        commandHandler.printPosition();
        assertEquals(mes2,outputStreamCaptor.toString().trim());

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

        for (int row = tableArray.length-1; row >= 0; row--) {
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

        outputStreamCaptor.reset();
        commandHandler.printTable();
        assertEquals(output.toString(), outputStreamCaptor.toString());
    }

    @Test
    void moveRobot() {
        //todo


    }

    @Test
    void turnLeft() {
        commandHandler.turnLeft();
        assertEquals("west", commandHandler.robot.getDirection());
        commandHandler.turnLeft();
        assertEquals("south", commandHandler.robot.getDirection());
        commandHandler.turnLeft();
        assertEquals("east", commandHandler.robot.getDirection());
        commandHandler.turnLeft();
        assertEquals("north", commandHandler.robot.getDirection());
    }

    @Test
    void turnRight() {
        commandHandler.turnRight();
        assertEquals("east", commandHandler.robot.getDirection());
        commandHandler.turnRight();
        assertEquals("south", commandHandler.robot.getDirection());
        commandHandler.turnRight();
        assertEquals("west", commandHandler.robot.getDirection());
        commandHandler.turnRight();
        assertEquals("north", commandHandler.robot.getDirection());

    }

    @Test
    void penDown() {
        commandHandler.penDown();
        assertTrue(commandHandler.robot.getPenState());

        outputStreamCaptor.reset();
        commandHandler.penDown();
        assertEquals("Pen direction already down", outputStreamCaptor.toString().trim());
    }

    @Test
    void penUp() {
        outputStreamCaptor.reset();
        commandHandler.penUp();
        assertEquals("Pen direction already up", outputStreamCaptor.toString().trim());

        commandHandler.robot.setPenState(true);
        commandHandler.penUp();
        assertFalse(commandHandler.robot.getPenState());


    }

    @Test
    void initializeSystem() {
        commandHandler.initializeSystem(5);
        assertTrue(commandHandler.initialized);

        outputStreamCaptor.reset();
        commandHandler.initializeSystem(1);
        assertEquals("Please choose a size bigger or equal to 2", outputStreamCaptor.toString().trim());
    }
}