package com.realapps.games;

import com.realapps.games.fourinarow.FourInARow;
import com.realapps.games.hangman.Hangman;

import java.util.Scanner;

public class StartPointForAllGames {

    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Welcome to the Console - Mini - Games");
        System.out.println("(Note : You can change the configuration of games, by modifying the properties inside the game.properties file)\n");
        displayListOfGameOptions();
        takeInputAndStartGame();
    }

    private static void displayListOfGameOptions(){
        System.out.println("Type in the name or number of the game you want to play from the below list : ");
        System.out.println("1. Four - In - A - Row");
        System.out.println("2. Hangman");
        System.out.println("3. Exit");
    }

    private static void takeInputAndStartGame(){
        String input = sc.nextLine();
        switch (input) {
            case "1", "Four - In - A - Row", "Four-In-A-Row", "four in a row", "Four In A Row" -> {
                FourInARow.startGame();
                displayPlayAgainOptions();
            }
            case "2", "Hangman", "hangman", "h", "H" -> {
                Hangman.startGame();
                displayPlayAgainOptions();
            }
            case "3", "quit", "q", "Quit", "Exit", "exit" -> System.out.println("Exiting the game.");
            default -> {
                System.out.println("Please enter a valid input !\n");
                displayListOfGameOptions();
                takeInputAndStartGame();
            }
        }
    }

    private static void displayPlayAgainOptions(){
        System.out.println("Do you want to play again ? (Y/N)");
        String playAgain = sc.nextLine();
        if (playAgain.startsWith("y") || playAgain.startsWith("Y")) {
            displayListOfGameOptions();
            takeInputAndStartGame();
        } else
            System.out.println("Exiting the game.");
    }
}
