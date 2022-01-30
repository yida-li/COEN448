package com.java.main;

import com.java.singleton.CommandHandler;

public class Main {
    public static void main(String[] args){
        //start commandHandler and display UI
        CommandHandler commandHandler = new CommandHandler();
        commandHandler.ui();
    }
}
