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
    void handleInput() {
        /*
            [U|u] Pen up
            [D|d] Pen down
            [R|r] Turn right
            [L|l] Turn left
            [M s|m s] Move forward s spaces (s is a non-negative integer)
            [P|p] Print the N by N array and display the indices
            [C|c] Print current position of the pen and whether it is up or down and its facing direction
            [Q|q] Stop the program
            [I n|i n] Initialize the system: The values of the array floor are zeros and the robot is back to [0, 0],
                        pen up and facing north. n size of the array, an integer greater than zero
            [H|h] Replay all the commands entered by the user as a history
         */

        String val1 = "u";
        String val2 = "U";
        String val3 = "d";
        String val4 = "D";
        String val5 = "r";
        String val6 = "R";
        String val7 = "l";
        String val8 = "L";
        String val9 = "M 2";
        String val10 = "m 2";
        String val11 = "P";
        String val12 = "p";
        String val13 = "C";
        String val14 = "c";
        String val15 = "Q";
        String val16 = "q";
        String val17 = "I 10";
        String val18 = "i 10";
        String val19 = "H";
        String val20 = "h";
        String val21 = "";
        String val22 = "i";
        String val23 = "m";
        String val24 = "i 1";
        String val25 = "menu";
        String val26 = "m 0";
        String val27 = "m 10";

        outputStreamCaptor.reset();
        CommandHandler.handleInput(val11,true);
        assertEquals(table(false), outputStreamCaptor.toString());

        outputStreamCaptor.reset();
        CommandHandler.handleInput(val12,true);
        assertEquals(table(false), outputStreamCaptor.toString());

        outputStreamCaptor.reset();
        CommandHandler.handleInput(val1,true);
        assertEquals("Pen direction already up", outputStreamCaptor.toString().trim());

        outputStreamCaptor.reset();
        CommandHandler.handleInput(val2,true);
        assertEquals("Pen direction already up", outputStreamCaptor.toString().trim());

        outputStreamCaptor.reset();
        CommandHandler.handleInput(val3,true);
        assertEquals("Pen direction going down...", outputStreamCaptor.toString().trim());

        outputStreamCaptor.reset();
        CommandHandler.handleInput(val4,true);
        assertEquals("Pen direction already down", outputStreamCaptor.toString().trim());

        outputStreamCaptor.reset();
        CommandHandler.handleInput(val5,true);
        assertEquals("Turning right...", outputStreamCaptor.toString().trim());

        outputStreamCaptor.reset();
        CommandHandler.handleInput(val6,true);
        assertEquals("Turning right...", outputStreamCaptor.toString().trim());

        outputStreamCaptor.reset();
        CommandHandler.handleInput(val7,true);
        assertEquals("Turning left...", outputStreamCaptor.toString().trim());

        outputStreamCaptor.reset();
        CommandHandler.handleInput(val8,true);
        assertEquals("Turning left...", outputStreamCaptor.toString().trim());

        outputStreamCaptor.reset();
        CommandHandler.handleInput(val9,true);
        assertEquals("Moving...", outputStreamCaptor.toString().trim());

        outputStreamCaptor.reset();
        CommandHandler.handleInput(val10,true);
        assertEquals("Moving...", outputStreamCaptor.toString().trim());

        outputStreamCaptor.reset();
        String output = "Position: 0, 4 - Pen: down - Facing: north";
        CommandHandler.handleInput(val13,true);
        assertEquals(output, outputStreamCaptor.toString().trim());

        outputStreamCaptor.reset();
        CommandHandler.handleInput(val14,true);
        assertEquals(output, outputStreamCaptor.toString().trim());

        outputStreamCaptor.reset();
        assertTrue(CommandHandler.handleInput(val15,true));

        outputStreamCaptor.reset();
        assertTrue(CommandHandler.handleInput(val16,true));

        outputStreamCaptor.reset();
        CommandHandler.handleInput(val17,true);
        assertEquals("Initializing with size: 10", outputStreamCaptor.toString().trim());

        outputStreamCaptor.reset();
        CommandHandler.handleInput(val18,true);
        assertEquals("Initializing with size: 10", outputStreamCaptor.toString().trim());

        outputStreamCaptor.reset();
        CommandHandler.handleInput(val19,true);
        assertTrue(commandHandler.commands.isEmpty());

        outputStreamCaptor.reset();
        CommandHandler.handleInput(val20,true);
        assertTrue(commandHandler.commands.isEmpty());

        outputStreamCaptor.reset();
        CommandHandler.handleInput(val21,true);
        assertEquals("User selected Nothing", outputStreamCaptor.toString().trim());

        outputStreamCaptor.reset();
        CommandHandler.handleInput(val22,true);
        assertEquals("Invalid entry", outputStreamCaptor.toString().trim());

        outputStreamCaptor.reset();
        CommandHandler.handleInput(val23,true);
        assertEquals("Invalid entry", outputStreamCaptor.toString().trim());

        outputStreamCaptor.reset();
        CommandHandler.handleInput(val24,true);
        assertEquals("Invalid entry", outputStreamCaptor.toString().trim());

        outputStreamCaptor.reset();

        output = "Enter 'Q' to close program\n"
                 +"Possible commands:\n" +
                "I n: Initialize the system\n" +
                "U: Pen Up\n" +
                "D: Pen Down\n" +
                "R: Turn Right\n" +
                "L: Turn Left\n" +
                "M s: Move forward s spaces\n" +
                "P: Print the table\n" +
                "C: Print current position of the pen\n" +
                "H: Replay all the commands entered\n" +
                "Q: Stop the program";

        CommandHandler.handleInput(val25,true);
        assertEquals(output.replaceAll("\n", "").replaceAll("\r", ""), outputStreamCaptor.toString().trim().replaceAll("\n", "").replaceAll("\r", ""));

        outputStreamCaptor.reset();
        CommandHandler.handleInput(val26,true);
        assertEquals("Invalid entry", outputStreamCaptor.toString().trim());

        outputStreamCaptor.reset();
        CommandHandler.handleInput(val27,true);
        assertEquals("Can not move " + 10 + " in the " + commandHandler.robot.getDirection() + " direction, the robot will fall off the table", outputStreamCaptor.toString().trim());

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

    String table(boolean flag){
        //get expected output
        StringBuilder output = new StringBuilder();

        int[][] tableArray;
        //initialize array
        tableArray = new int[10][10];

        //initialize table with 0s
        for (int[] ints : tableArray) {
            Arrays.fill(ints, 0);
        }

        if(flag){
            tableArray[5][5]=1;
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
        return output.toString();
    }
    @Test
    void printTable() {

        String output  = table(true);

        outputStreamCaptor.reset();
        Point point = new Point(5,5);
        commandHandler.table.writeTable(point,true);
        commandHandler.printTable();
        assertEquals(output, outputStreamCaptor.toString());
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

    @Test
    void runHistory(){
        commandHandler.commands.add("i10");
        commandHandler.commands.add("m5");
        commandHandler.commands.add("r");
        commandHandler.commands.add("d");
        commandHandler.commands.add("m5");

        commandHandler.runHistory();
        assertTrue(commandHandler.commands.isEmpty());
    }
}