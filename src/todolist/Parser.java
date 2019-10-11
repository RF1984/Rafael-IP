package todolist;

import java.util.Scanner;

public class Parser {
    private inputCommands commands;
    private Scanner reader;

    public Parser() {
        commands = new inputCommands();
        reader = new Scanner(System.in);
    }
}

