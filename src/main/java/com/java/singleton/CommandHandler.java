package com.java.singleton;

import java.awt.*;
import java.util.Scanner;

import static java.lang.System.exit;

public class CommandHandler {

    private static final Scanner sc = new Scanner(System.in);
    private static Table table;
    public static Singleton robot;
    public static boolean initialized = false;

    //constructor, runs the UI
    public CommandHandler(){}

    /**
     * UI of the client
     * displays commands to server and handles them
     */
    public static void ui(){
        String val = "";    //input from user

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

        //if m or I is entered alone without a number
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
        ui();
    }

    //get the number entered from the string
    public static int intValueGiven(String val)
    {
        int number = 0;
        String temp;

        //get int from char
        StringBuilder sb = new StringBuilder(val);
        sb.deleteCharAt(0);
        temp = removeBlankSpace(sb);

        if(isNumeric(temp)){
            //save given number
            number = Integer.parseInt(temp);
        }else{
            //invalid format
            number = -1;
        }
        //System.out.println("number is:" + number);

        return number;
    }

    //remove whitespaces from the string
    public static String removeBlankSpace(StringBuilder sb) {
        int j = 0;
        for(int i = 0; i < sb.length(); i++) {
            if (!Character.isWhitespace(sb.charAt(i))) {
                sb.setCharAt(j++, sb.charAt(i));
            }
        }
        sb.delete(j, sb.length());
        return sb.toString();
    }

    //make sure given value is a number
    public static boolean isNumeric(String str){
        return str != null && str.matches("[0-9.]+");
    }

    //print the position of the robot
    public static void printPosition() {
        //System.out.println("Printing position...");
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
        System.out.println("Position: " + y + ", " + x + " - Pen: " + pen + " - Facing: " + direction);
    }

    //print the table along with the coordinates of the robot and the orientation of the pen
    public static void printTable() {
        //System.out.println("Printing table...");
        table.printTable(robot.getCoordinates(), robot.getPenState());
    }

    //move the robot
    public static void moveRobot(int steps) {
        String direction = robot.getDirection();
        Point position = robot.getCoordinates();
        Point nextPoint;
        int x = position.x;
        int y = position.y;
        boolean onTable;
        boolean penState = robot.getPenState();

        switch (direction) {
            case "north":
                nextPoint = new Point(x + steps, y);
                break;
            case "south":
                nextPoint = new Point(x - steps, y);
                break;
            case "west":
                nextPoint = new Point(x, y - steps);
                break;
            case "east":
                nextPoint = new Point(x, y + steps);
                break;
            default:
                nextPoint = new Point(-1, -1);
                break;
        }

        onTable = table.isOnTable(nextPoint);

        if(onTable){
            //move robot
            System.out.println("Moving...");
            while(steps>0) {
                if (direction.equals("north"))
                    robot.getCoordinates().move((int) (robot.getCoordinates().getX()+1), (int) robot.getCoordinates().getY());
                if (direction.equals("south"))
                    robot.getCoordinates().move((int) (robot.getCoordinates().getX()-1), (int) robot.getCoordinates().getY());
                if (direction.equals("west"))
                    robot.getCoordinates().move((int) (robot.getCoordinates().getX()), (int) robot.getCoordinates().getY()-1);
                if (direction.equals("east"))
                    robot.getCoordinates().move((int) (robot.getCoordinates().getX()), (int) robot.getCoordinates().getY()+1);
                steps--;
                table.writeTable(robot.getCoordinates(), penState);
            }
        }else{
            System.out.println("Can not move " + steps + " in the " + direction + " direction, the robot will fall off the table");
        }
    }

    //turn the robot left
    public static void turnLeft() {
        System.out.println("Turning left...");
        String currentDirection = robot.getDirection();

        //turn correct direction, depending on current direction
        switch (currentDirection) {
            case "north":
                robot.setDirectionWest();
                break;
            case "south":
                robot.setDirectionEast();
                break;
            case "east":
                robot.setDirectionNorth();
                break;
            case "west":
                robot.setDirectionSouth();
                break;
        }
    }

    //turn the robot right
    public static void turnRight() {
        System.out.println("Turning right...");
        String currentDirection = robot.getDirection();

        //turn correct direction, depending on current direction
        switch (currentDirection) {
            case "north":
                robot.setDirectionEast();
                break;
            case "south":
                robot.setDirectionWest();
                break;
            case "east":
                robot.setDirectionSouth();
                break;
            case "west":
                robot.setDirectionNorth();
                break;
        }
    }

    //make the pen face down
    public static void penDown() {
        if(robot.getPenState()){
            System.out.println("Pen direction already down");
        } else{
            System.out.println("Pen direction going down...");
            robot.setPenState(true);
            table.writeTable(robot.getCoordinates(), true);//when pen is turned down, automatically write * on the current robot position
        }
    }

    //make the pen face up
    public static void penUp() {
        if(!robot.getPenState()){
            System.out.println("Pen direction already up");
        } else{
            System.out.println("Pen direction going up...");
            robot.setPenState(false);
        }
    }

    //initialize the system
    //make new table
    //reset robot
    //set initialized boolean
    public static void initializeSystem(int size) {
        if(size < 2){
            System.out.println("Please choose a size bigger or equal to 2");
        } else{
            System.out.println("Initializing with size: " + size);
            table = new Table(size,size);
            robot = Singleton.getInstance();
            robot.reinitialize();
            initialized = true;
        }
    }
}
