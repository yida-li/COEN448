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
    void testUi() {
        //todo
    }

    @Test
    void testIntValueGiven() {
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
    void testRemoveBlankSpace() {
        String val = " I";
        StringBuilder sb = new StringBuilder(val);

        assertEquals("I",commandHandler.removeBlankSpace(sb));
    }

    @Test
    void testIsNumeric() {
        String numb = "10";
        String notNumb = "A";
        assertAll(() -> assertTrue(commandHandler.isNumeric(numb)),
                () -> assertFalse(commandHandler.isNumeric(notNumb)));
    }

    @Test
    void testPrintPosition() {
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
    void testPrintTable() {
        //get expected output
        StringBuilder output = new StringBuilder();

        int[][] tableArray;
        //initialize array
        tableArray = new int[10][10];

        //initialize table with 0s
        for (int[] ints : tableArray) {
            Arrays.fill(ints, 0);
        }

        for (int[] row : tableArray){
            for (int element : row){
                if(element == 0)
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
    void testMoveRobot() {
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
    void testPenDown() {
        commandHandler.penDown();
        assertTrue(commandHandler.robot.getPenState());
    }

    @Test
    void testPenUp() {
        commandHandler.penUp();
        assertFalse(commandHandler.robot.getPenState());
    }

    @Test
    void testInitializeSystem() {
        commandHandler.initializeSystem(5);
        assertTrue(commandHandler.initialized);

    }
}