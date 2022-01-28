package com.java.singleton;

import java.awt.*;
import java.util.Scanner;

import static java.lang.System.exit;

public class CommandHandler {

    private static final Scanner sc = new Scanner(System.in);
    private static Table table;
    private static Singleton robot;
    private static boolean initialized = false;

    //constructor, runs the UI
    public CommandHandler(){ui();}

    /**
     * UI of the client
     * displays commands to server and handles them
     */
    public static void ui(){
        String val = "";    //input from user

        while (!val.equals("exit")) {
            //ui
            System.out.println("\nEnter 'Q' to close program");
            System.out.println("Possible commands:\n" +
                    "I n: Initialize the system\n"+
                    "U: Pen Up\n"+
                    "D: Pen Down\n"+
                    "R: Turn Right\n"+
                    "L: Turn Left\n"+
                    "M s: Move forward s spaces\n" +
                    "P: Print the table\n"+
                    "C: Print current position of the pen\n"+
                    "Q: Stop the program\n");

            System.out.println("Enter the wanted command:");
            val = sc.nextLine();

            if (val.isEmpty()) {
                val = "-1";
            }

            //number given for initialization and move spaces
            int number = 0;

            //if m or i is entered alone without a number
            if((val.charAt(0) == 'M' || val.charAt(0) == 'm' || val.charAt(0) == 'I' || val.charAt(0) == 'i')
                    && val.length()==1) {
                val = "x"; //incorrect input
            }
            else if(val.charAt(0) == 'I' || val.charAt(0) == 'i') { //if I input, initialization requested
                //get number given
                number = intValueGiven(val);
                //if number is negative, invalid input
                if(number <=0) {
                    val = "x";
                } else {
                    val = "I";
                }
            }
            else if(val.charAt(0) == 'M' || val.charAt(0) == 'm') { //if m input, move requested
                //get number given
                number = intValueGiven(val);
                //if number is negative, invalid input
                if(number <=0) {
                    val = "x";
                } else {
                    val = "M";
                }
            }

            //cases for every possible commands
            //make sure system is initialized before accepting any other command
            switch (val) {
                case "i":
                case "I":
                    initializeSystem(number);
                    break;
                case "u":
                case "U":
                    if(initialized) {
                        penUp();
                    }else
                        System.out.println("Please initialize the system first");
                    break;
                case "d":
                case "D":
                    if(initialized) {
                        penDown();
                    }else
                        System.out.println("Please initialize the system first");
                    break;
                case "r":
                case "R":
                    if(initialized) {
                        turnRight();
                    }else
                        System.out.println("Please initialize the system first");
                    break;
                case "l":
                case "L":
                    if(initialized) {
                        turnLeft();
                    }else
                        System.out.println("Please initialize the system first");
                    break;
                case "m":
                case "M":
                    if(initialized) {
                        moveRobot(number);
                    }else
                        System.out.println("Please initialize the system first");
                    break;
                case "p":
                case "P":
                    if(initialized) {
                        printTable();
                    }else
                        System.out.println("Please initialize the system first");
                    break;
                case "c":
                case "C":
                    if(initialized) {
                        printPosition();
                    }else
                        System.out.println("Please initialize the system first");
                    break;
                case "q":
                case "Q":
                    exit(1);
                    break;
                case "-1":
                    System.out.println("User selected Nothing");
                    break;
                default:
                    System.out.println("Invalid entry");
            }
        }
        exit(1);
    }

    //get the number entered from the string
    private static int intValueGiven(String val)
    {
        int number = 0;
        String temp;

        //get int from char
        StringBuilder sb = new StringBuilder(val);
        sb.deleteCharAt(0);
        removeBlankSpace(sb);
        temp = sb.toString();

        if(isNumeric(temp)){
            //save given number
            try{
                number = Integer.parseInt(temp);
            }
            catch (NumberFormatException ex){
                ex.printStackTrace();
            }
        }else{
            //invalid format
            number = -1;
        }
        //System.out.println("number is:" + number);

        return number;
    }

    //remove whitespaces from the string
    private static void removeBlankSpace(StringBuilder sb) {
        int j = 0;
        for(int i = 0; i < sb.length(); i++) {
            if (!Character.isWhitespace(sb.charAt(i))) {
                sb.setCharAt(j++, sb.charAt(i));
            }
        }
        sb.delete(j, sb.length());
    }

    //make sure given value is a number
    private static boolean isNumeric(String str){
        return str != null && str.matches("[0-9.]+");
    }

    //print the position of the robot
    private static void printPosition() {
        System.out.println("Printing position...");
        //get coordinates
        Point position = robot.getCoordinates();
        int x = position.x;
        int y = position.y;
        //get state of the pen
        boolean upDown = robot.getPenState();
        String pen;
        if (upDown)
            pen = "down";
        else
            pen = "up";

        //get direction of the pen
        String direction = robot.getDirection();

        //print position
        System.out.println("Position: " + x + ", " + y + " - Pen: " + pen + " - Facing: " + direction);
    }

    //print the table
    private static void printTable() {
        System.out.println("Printing table...");
        table.printTable();
    }

    //move the robot
    private static void moveRobot(int spaces) {
        System.out.println("Moving...");
        //todo: move the robot, make sure it is not going out of the table,
        // print to the table if the pen is down (true)
        // set new coordinates of the robot
    }

    //turn the robot left
    private static void turnLeft() {
        System.out.println("Turning left...");
        String currentDirection = robot.getDirection();

        //turn correct direction, depending on current direction
        if(currentDirection.equals("north")){
            robot.setDirectionWest();
        }else if(currentDirection.equals("south")){
            robot.setDirectionEast();
        }else if(currentDirection.equals("east")){
            robot.setDirectionNorth();
        }else if(currentDirection.equals("west")){
            robot.setDirectionSouth();
        }
    }

    //turn the robot right
    private static void turnRight() {
        System.out.println("Turning right...");
        String currentDirection = robot.getDirection();

        //turn correct direction, depending on current direction
        if(currentDirection.equals("north")){
            robot.setDirectionEast();
        }else if(currentDirection.equals("south")){
            robot.setDirectionWest();
        }else if(currentDirection.equals("east")){
            robot.setDirectionSouth();
        }else if(currentDirection.equals("west")){
            robot.setDirectionNorth();
        }
    }

    //make the pen face down
    private static void penDown() {
        System.out.println("Pen direction going down...");
        robot.setPenState(true);
    }

    //make the pen face up
    private static void penUp() {
        System.out.println("Pen direction going up...");
        robot.setPenState(false);
    }

    //initialize the system
    //make new table
    //reset robot
    //set initialized boolean
    private static void initializeSystem(int size) {
        System.out.println("Initializing with size: " + size);
        table = new Table(size,size);
        robot = Singleton.getInstance();
        robot.reinitialize();
        initialized = true;
    }
}
