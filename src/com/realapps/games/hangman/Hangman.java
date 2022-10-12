/**
 Hangman Game

 Description :
 A word is selected from the list at random, and the player/user needs to guess all the letters in it one by one (You just got 10 attempts to guess all the letters in the word)

 NOTE :
 You can have a better experience of the below code in an interactive IDE (eg. - IntelliJ, Eclipse)
 Since the program would be much better if it takes input interactively at runtime

 You can try the above on Jdoodle as well
 Just turn it's interactive switch ON


 Sample Input (To run the code on Sololearn) : Any 10 random characters one by one on each line
 For eg :
 a
 e
 y
 o
 u
 p
 q
 h
 g
 n

 And click on Submit.
 */

package com.realapps.games.hangman;

import com.realapps.games.util.CommonMethods;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Hangman {

//    static fields
    private static List<String> list = new ArrayList<>();
    private static int TOTAL_NUMBER_OF_GUESSES = 10;
    private static int remainingGuesses = TOTAL_NUMBER_OF_GUESSES;

//    final fields
    private static final Random r = new Random();
    private static final Scanner sc = new Scanner(System.in);
    private static final List<Character> missedGuesses = new ArrayList<>();
    private static final List<Character> result = new ArrayList<>();
    private static final String FILE_NAME_OF_WORDS_LIST = "resources/words_list.txt";

//    start point of the game
    public static void startGame(){
        initializeGame();
        String selection = list.get(r.nextInt(list.size()));
        int len = selection.length();
        for(int i=0; i<len; i++)
            result.add('_');

        String prop = CommonMethods.getPropertyFromPropertiesFile("total.number.of.guesses");
        try {
            TOTAL_NUMBER_OF_GUESSES = Integer.parseInt(prop);
        } catch (Exception e){
            TOTAL_NUMBER_OF_GUESSES = len + r.nextInt(6-3+1) + 3;
        }
        remainingGuesses = TOTAL_NUMBER_OF_GUESSES;
        takeInputAndShowResult(selection);
    }

//    method to reset the game fields
    private static void resetGame(){
        list.clear();
        missedGuesses.clear();
        result.clear();
        remainingGuesses = TOTAL_NUMBER_OF_GUESSES;
    }

//    method to initialize the game, fill the list with words
    private static void initializeGame(){
        resetGame();
        list = CommonMethods.getLinesFromFileInAList(FILE_NAME_OF_WORDS_LIST)
                .stream().filter(s -> !s.isBlank()).map(s -> s.toLowerCase().trim()).collect(Collectors.toList());
// You can add new words for selection either inside the words_list.txt file or over here (for eg. - list.add("new_word"));
        if(list.size() <= 0){
            list.add("hangman");
            list.add("november");
            list.add("avocado");
        }
    }

//    method to take input from the user and check if the game is won or lost
    private static void takeInputAndShowResult(String selection){
        for(int i=0; i<TOTAL_NUMBER_OF_GUESSES; i++) {
            if(i==0) {
                System.out.println("YOU HAVE A TOTAL OF "+TOTAL_NUMBER_OF_GUESSES+" GUESSES");
                System.out.println(result);
            }

            System.out.println("\nEnter your guess");
            char guess = sc.next().charAt(0);

            checkInput(selection, guess);
            display(guess);

            if(checkWinner()) {
                System.out.println("\nYou won!");
                return;
            }
        }

        System.out.println("\nYou Lost");
        System.out.println("Correct Word was : "+selection);
    }

//    method to check if the player has won or not
    private static boolean checkWinner(){
        for(char c : result){
            if(c == '_')
                return false;
        }
        return true;
    }

//    method to check the input matches the word or not
    private static void checkInput(String selection, char guess) {
        CharSequence temp = String.valueOf(guess);
        var tempArr = new ArrayList<Integer>();

        if(selection.contains(temp)){
            for(int i=0; i<selection.length(); i++){
                if(selection.charAt(i) == guess){
                    tempArr.add(i);
                }
            }

            addToResult(tempArr, guess);
        } else{
            missedGuesses.add(guess);
        }
    }

//    method to add the guess inside the result list
    private static void addToResult(ArrayList<Integer> tempArr, char guess) {
        for (int integer : tempArr) {
            result.set(integer, guess);
        }
    }

//    method to display the details of the game
    private static void display(char guess){
        System.out.println(result);
        System.out.println("Your Guess : "+ guess);
        System.out.println("Missed Guesses : "+missedGuesses);
        System.out.println("Number of Guesses left : "+ --remainingGuesses);
    }

}