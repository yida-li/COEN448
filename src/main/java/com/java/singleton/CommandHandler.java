package com.java.singleton;

import java.awt.*;
import java.util.Scanner;

import static java.lang.System.exit;

public class CommandHandler {


    private static final Scanner sc = new Scanner(System.in);
    private static Table table;
    private static boolean initialized = false;
    private static Singleton robot;

    public CommandHandler(){}

    /**
     * UI of the client
     * displays commands to server and handles them
     */
    public static void ui(){
        String val = "";
        while (!val.equals("exit")) {
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

            int number = 0;

            if((val.charAt(0) == 'M' || val.charAt(0) == 'm' || val.charAt(0) == 'I' || val.charAt(0) == 'i')
                    && val.length()==1) {
                val = "x";
            } else if(val.charAt(0) == 'I' || val.charAt(0) == 'i') {
                number = intValueGiven(val);
                if(number <=0) {
                    val = "x";
                } else {
                    val = "I";
                }
            } else if(val.charAt(0) == 'M' || val.charAt(0) == 'm') {
                number = intValueGiven(val);
                if(number <=0) {
                    val = "x";
                } else {
                    val = "M";
                }
                //System.out.println("val is:" + val);
            }

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
                        System.out.println("Please initalize the system first");
                    break;
                case "d":
                case "D":
                    if(initialized) {
                        penDown();
                    }else
                        System.out.println("Please initalize the system first");
                    break;
                case "r":
                case "R":
                    if(initialized) {
                        turnRight();
                    }else
                        System.out.println("Please initalize the system first");
                    break;
                case "l":
                case "L":
                    if(initialized) {
                        turnLeft();
                    }else
                        System.out.println("Please initalize the system first");
                    break;
                case "m":
                case "M":
                    if(initialized) {
                        moveRobot(number);
                    }else
                        System.out.println("Please initalize the system first");
                    break;
                case "p":
                case "P":
                    if(initialized) {
                        printTable();
                    }else
                        System.out.println("Please initalize the system first");
                    break;
                case "c":
                case "C":
                    if(initialized) {
                        printPosition();
                    }else
                        System.out.println("Please initalize the system first");
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

    private static void removeBlankSpace(StringBuilder sb) {
        int j = 0;
        for(int i = 0; i < sb.length(); i++) {
            if (!Character.isWhitespace(sb.charAt(i))) {
                sb.setCharAt(j++, sb.charAt(i));
            }
        }
        sb.delete(j, sb.length());
    }

    private static boolean isNumeric(String str){
        return str != null && str.matches("[0-9.]+");
    }

    private static void printPosition() {
        System.out.println("Printing position...");
        Point position = robot.getCoordinates();
        int x = position.x;
        int y = position.y;
        boolean upDown = robot.getPenState();
        String pen;
        if (upDown)
            pen = "down";
        else
            pen = "up";

        String direction = robot.getDirection();
        System.out.println("Position: " + x + ", " + y + " - Pen: " + pen + " - Facing: " + direction);
    }

    private static void printTable() {
        System.out.println("Printing table...");
        table.printTable();
    }

    private static void moveRobot(int spaces) {
        System.out.println("Moving...");
        //todo: move the robot, make sure it is not going out of the table,
        // print to the table if the pen is down (true)
        // set new coordinates of the robot
    }

    private static void turnLeft() {
        System.out.println("Turning left...");
        String currentDirection = robot.getDirection();

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

    private static void turnRight() {
        System.out.println("Turning right...");
        String currentDirection = robot.getDirection();

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

    private static void penDown() {
        System.out.println("Pen direction going down...");
        robot.setPenState(true);
    }

    private static void penUp() {
        System.out.println("Pen direction going up...");
        robot.setPenState(false);
    }

    private static void initializeSystem(int size) {
        System.out.println("Initializing with size: " + size);
        table = new Table(size,size);
        robot = Singleton.getInstance();
        initialized = true;

    }
}
