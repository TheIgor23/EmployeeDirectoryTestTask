package ru.jezemoin.emploedirectory;

import ru.jezemoin.emploedirectory.commands.CommandProcessor;


public class Main {
    public static void main(String[] args) {
            CommandProcessor commandProcessor = new CommandProcessor();
            commandProcessor.execute(args);
    }
}
