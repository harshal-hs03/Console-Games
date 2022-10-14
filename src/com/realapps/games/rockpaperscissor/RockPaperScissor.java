/**
 * ==== Rock - Paper - Scissor ====
 *
 * Sample Input :
 * You can either
 * Enter 1 for Rock/ 2 for Paper/ 3 for Scissor
 * Or
 * Enter rock/ paper/ scissor in any case (upper or lower)
 *
 * Also added the below as valid inputs :
 * r/ p/ s/ scissors in any case (upper or lower)
 */

package com.realapps.games.rockpaperscissor;

import java.util.Scanner;

//    usage of enums for the tools required for this game
enum GameTools {
    ROCK, PAPER, SCISSOR
}

public class RockPaperScissor{

//   ==  static fields ==
    private static String result = "";
    private static int computer = 1;

//    final fields
    private static final Scanner sc = new Scanner(System.in);
    private static final GameTools[] tools = GameTools.values();

//    == public methods ==

    //    start point of the game
    public static void startGame(){
        System.out.println("Let's begin with Rock-Paper-Scissor");
        String input;
        while(true) {
            System.out.println("(Type 1/r/rock for ROCK, 2/p/paper for PAPER, 3/s/scissor/scissors for SCISSOR) ");
            System.out.println("Enter your input : ");
            input = sc.nextLine();
            if(input.startsWith("q") || input.startsWith("Q") || input.startsWith("E") || input.startsWith("e"))
                break;
            try {
                computer = (int) (Math.random()*(3-1+1) + 1);
                int n = Integer.parseInt(input);
                checkWinnerWithInt(n);
            } catch (Exception e) {
                checkWinner(formatInput(input));
            }
            System.out.println("\nType Quit/Exit to exit the game.");
        }
    }

//    == private methods ==

    //    to check the winner in case of an integer input
    private static void checkWinnerWithInt(final int n){
        if(!(n >= 1 && n <= 3)){
            generateMessage("", false);
            return;
        }
        String user = tools[n-1].name();
        checkWinner(user);
    }

    //    This method calculates the actual winner
    private static void checkWinner(final String n){
        if(!checkInput(n)){
            generateMessage("", false);
            return;
        }
        String compSelection = tools[computer-1].name();

        if((n.equals(tools[0].name()) && compSelection.equals(tools[1].name())) ||
                (n.equals(tools[1].name()) && compSelection.equals(tools[2].name())) ||
                (n.equals(tools[2].name()) && compSelection.equals(tools[0].name())))
            result = "You Lost";
        else if(n.equals(compSelection))
            result = "It's a draw";
        else
            result = "You won!";

        generateMessage(n, true);
    }

    //    This method checks if the input is valid
    private static boolean checkInput(final String n){
        if(n.length()<4 || n.length()>7)
            return false;
        for(GameTools g : tools){
            if(g.name().equalsIgnoreCase(n))
                return true;
        }
        return false;
    }

    //    This method is used to generate the display message
    private static void generateMessage(String n, boolean flag){
        if(flag)
            System.out.println("Computers input : "+tools[computer-1].name()+", \nYour input : "+n+"\n"+result);
        else
            System.out.println("Invalid input. Try again!");
    }

    //    This method is used to format the input in the required format of the application
    private static String formatInput(String input) {
        switch( input.toUpperCase() ) {
            case "R":
                return tools[0].name();
            case "P":
                return tools[1].name();
            case "S":
            case "SCISSORS":
                return tools[2].name();
            default:
                return input.toUpperCase();
        }
    }
}

