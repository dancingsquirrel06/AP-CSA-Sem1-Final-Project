package main.java;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static ArrayList<Player> players = new ArrayList<>();
    private static ArrayList<Integer> deck = new ArrayList<>();
    private static Random num = new Random();
    private static boolean gameRunning = true;

    public static void main(String[] args) {
        while (gameRunning) {
            System.out.println("Welcome to BlackJack!");
            System.out.print("How many players will be playing in this game?: ");
            String input; // input
            startGame();
        }
    }

    public static void makeDeck() {
        deck = new ArrayList<>();
        for (int i = 1; i <= 13; i++) {
            int temp = 4;
            while (temp > 0) {
                if (i < 10) {
                    deck.add(i);
                } else {
                    deck.add(10);
                }
                temp--;
            }
        }
        return;
    }

    public static void startGame() {
        for (Player p : players) {
            makeDeck();
            int a = num.nextInt(deck.size() - 1);
            int card1 = deck.get(a);
            deck.remove(a);
            int b = num.nextInt(deck.size() - 1);
            int card2 = deck.get(b);
            deck.remove(b);
            p.addCards(card1, card2);
            System.out.println("Cards: " + p.printCards());
        }
        round();
        return;
    }

    public static void round() {
        for (Player p : players) {
            System.out.print("Would you like to hit or stay?");
            String input; // scanner takes in input
            if (input.equals("hit")) {
                int a = num.nextInt(deck.size() - 1);
                p.hit(deck.get(a));
                deck.remove(a);
            }
            System.out.println();
            System.out.println("Cards: " + p.printCards());
            if (p.getSum() > 21) {
                System.out.println("Bust!");
            } else if (p.getSum() == 21) {
                System.out.println("You have reached 21, winner!");
                end();
                return;
            } 
        }
        round();
        return;
    }

    public static void end() {
        System.out.print("Would you like to play again? ");
        String input;
        if (input.equals("yes")) {
            return;
        } else if (input.equals("no")) {
            System.out.println("Thank you for playing!");
            gameRunning = false;
        } else {
            System.out.println("Option not valid");
            return;
        }
    }
}