package com.java.main;

import com.java.singleton.Singleton;

public class Main {
    public static void main(String[] args){
        Singleton robot = Singleton.getInstance();
        System.out.println("Robot created !");
        System.out.println("Hello World!!!");
    }
}
