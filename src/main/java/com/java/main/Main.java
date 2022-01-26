package main.java.com.java.main;

import main.java.com.java.singleton.Singleton;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Singleton robot = Singleton.getInstance();
        System.out.println("Robot created !");
        System.out.println("Hello World!!!");
        new Grid();
    }


}

