package com.java.singleton;

import java.util.Scanner;

import static java.lang.System.exit;

public class CommandHandler {


    private static final Scanner sc = new Scanner(System.in);
    private static Table table;
    private static boolean initialized = false;

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
                System.out.println("val is:" + val);
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

    }

    private static void printTable() {
        System.out.println("Printing table...");

        table.printTable();

    }

    private static void moveRobot(int spaces) {
        System.out.println("Moving...");
    }

    private static void turnLeft() {
        System.out.println("Turning left...");
    }

    private static void turnRight() {
        System.out.println("Turning right...");

    }

    private static void penDown() {
        System.out.println("Pen direction going down...");

    }

    private static void penUp() {
        System.out.println("Pen direction going up...");
    }

    private static void initializeSystem(int size) {
        System.out.println("Initializing with size: " + size);
        table = new Table(size,size);
        initialized = true;
    }
}
