/*
* ==== Four - In - A - Row ====
*
* Two players compete to get their letters four in a row/ column/ or in any diagonal like below :

    |(D)| 0 | 0 | 0 | 0 | 0 | 0 |   // 4 same letters in any diagonal (letter D) makes player D the winner
    | 0 |(D)| 0 | 0 | 0 | 0 | 0 |
    | 0 | 0 |(D)| 0 | 0 | 0 |(G)|   // 4 same letters in the same column (letter G) makes player G the winner
    | 0 | 0 | 0 |(D)| 0 | 0 |(G)|
    | 0 | 0 | 0 | 0 | 0 | 0 |(G)|
    | 0 |(R | R | R | R)| 0 |(G)|   // 4 same letters in the same row (letter R) makes player R the winner

*
* Enter the column numbers as input to put the letter in the specific column
* */

package com.realapps.games.fourinarow;

import com.realapps.games.util.CommonMethods;

import java.util.Arrays;
import java.util.Scanner;

public class FourInARow {

//    static fields
    private static String playerOne = "A", playerTwo = "B", currentPlayer = playerOne;

//    final fields
    private static final int row = 6;
    private static final int col = 7;
    private static final String[][] gameBoard = new String[row][col];
    private static final int[] colPtr = new int[col];
    private static final int[][] positionOfFourInRow = new int[4][2];
    private static final Scanner sc = new Scanner(System.in);

//    start point of the game
    public static void startGame() {
        System.out.println("==== Four - In - A - Row ====");
        initBoard();
        printBoard();
        setPlayerValues();
        String winner = null;
        do {
            takeInput();
            winner = checkWinner();
            printBoard();
        } while (winner == null);
        System.out.println("Winner is "+winner);
        System.out.println("Position of four in a row : "+ Arrays.deepToString(positionOfFourInRow));
    }

//    initialize the board to 0
    private static void initBoard(){
        int i;
        for(i=0; i<row; i++){
            for(int j=0; j<col; j++){
                gameBoard[i][j] = ""+0;
            }
            colPtr[i] = row - 1;
        }
        colPtr[i] = row - 1;
    }

//    method to print the board
    private static void printBoard(){
        for(String[] i : gameBoard){
            for(String j : i){
                System.out.print("| "+j+" ");
            }
            System.out.println("|");
        }
    }

//    method to set the player's input letter values to the properties defined in the game.properties file
    private static void setPlayerValues(){
        String p1 = CommonMethods.getPropertyFromPropertiesFile("playerOne.input.letter");
        String p2 = CommonMethods.getPropertyFromPropertiesFile("playerTwo.input.letter");
        currentPlayer = playerOne = (p1 == null || p1.equals(p2)) ? playerOne : p1;
        playerTwo = (p2 == null || p2.equals(p1)) ? playerTwo : p2;
    }

//    method to take input from console
    private static void takeInput(){
        System.out.println("(Player "+currentPlayer+") Enter your input : ");
        int colNo = sc.nextInt();
        sc.nextLine();
        if(checkValidInput(colNo)) {
            fillInput(colNo - 1, currentPlayer);
            changeTurn();
        } else
            System.out.println("Kindly enter a valid input !");
    }

//    method to check if the input value is valid or not
    private static boolean checkValidInput(int colNum){
        return colNum >= 1 && colNum <= col && colPtr[colNum-1] != -1;
    }

//    method to fill the input into the gameBoard array
    private static void fillInput(int colNum, String player){
        int ptr = colPtr[colNum];
        gameBoard[ptr][colNum] = player;
        colPtr[colNum] = ptr - 1;
    }

//    method to change the player's turn
    private static void changeTurn(){
        if(currentPlayer.equals(playerOne))
            currentPlayer = playerTwo;
        else
            currentPlayer = playerOne;
    }

//    method which checks for all the possible winning positions
    private static String checkWinner(){
        String check1 = checkWinnerInRow();
        String check2 = checkWinnerInColumn();
        String check3 = checkWinnerInLeftDiagonal();
        String check4 = checkWinnerInRightDiagonal();
        if(!check1.isEmpty())
            return check1;
        if(!check2.isEmpty())
            return check2;
        if(!check3.isEmpty())
            return check3;
        if(!check4.isEmpty())
            return check4;
        return null;
    }

//    method to check for winning positions in each row
    private static String checkWinnerInRow(){
        for(int i=0; i<row; i++){
            for(int j=0; j<=col-4; j++){
                String s1 = gameBoard[i][j];
                if(s1.contains("0"))
                    continue;
                String s2 = gameBoard[i][j+1];
                String s3 = gameBoard[i][j+2];
                String s4 = gameBoard[i][j+3];
                if(s1.equals(s2) && s2.equals(s3) && s2.equals(s4)) {
                    for(int k=0; k<4; k++)
                        positionOfFourInRow[k] = new int[]{i, j+k};
                    return s1;
                }
            }
        }
        return "";
    }

//    method to check for winning positions in each column
    private static String checkWinnerInColumn(){
        for(int i=0; i<col; i++){
            for(int j=0; j<=row-4; j++){
                String s1 = gameBoard[j][i];
                if(s1.contains("0"))
                    continue;
                String s2 = gameBoard[j+1][i];
                String s3 = gameBoard[j+2][i];
                String s4 = gameBoard[j+3][i];
                if(s1.equals(s2) && s2.equals(s3) && s2.equals(s4)) {
                    for(int k=0; k<4; k++)
                        positionOfFourInRow[k] = new int[]{j+k, i};
                    return s1;
                }
            }
        }
        return "";
    }

//    method to check for winning positions in diagonals from top left to right bottom in each row
    private static String checkWinnerInLeftDiagonal(){
        for(int i=0; i<=row-4; i++){
            for(int j=0; j<=col-4; j++){
                String s1 = gameBoard[i][j];
                if(s1.contains("0"))
                    continue;
                String s2 = gameBoard[i+1][j+1];
                String s3 = gameBoard[i+2][j+2];
                String s4 = gameBoard[i+3][j+3];
                if(s1.equals(s2) && s2.equals(s3) && s2.equals(s4)) {
                    for(int k=0; k<4; k++)
                        positionOfFourInRow[k] = new int[]{i+k, j+k};
                    return s1;
                }
            }
        }
        return "";
    }

//    method to check for winning positions in diagonals from top right to left bottom in each row
    private static String checkWinnerInRightDiagonal(){
        for(int i=0; i<=row-4; i++){
            for(int j=col-1; j>=col-4; j--){
                String s1 = gameBoard[i][j];
                if(s1.contains("0"))
                    continue;
                String s2 = gameBoard[i+1][j-1];
                String s3 = gameBoard[i+2][j-2];
                String s4 = gameBoard[i+3][j-3];
                if(s1.equals(s2) && s2.equals(s3) && s2.equals(s4)){
                    for(int k=0; k<4; k++)
                        positionOfFourInRow[k] = new int[]{i+k, j-k};
                    return s1;
                }
            }
        }
        return "";
    }
}
