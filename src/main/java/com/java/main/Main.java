package com.java.main;

import com.java.singleton.CommandHandler;
import com.java.singleton.Singleton;

public class Main {
    public static void main(String[] args){
        CommandHandler commandHandler = new CommandHandler();
        commandHandler.ui();
    }
}
