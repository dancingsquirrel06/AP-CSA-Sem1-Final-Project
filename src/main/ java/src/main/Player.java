package src.main;

import java.util.ArrayList;
import java.util.Scanner;

public class Player {
    private static final Scanner scanner = new Scanner(System.in);
    private int money;
    private int currentBet;
    private String name;
    private ArrayList<Card> cards;

    public Player(String n, int initialMoney) {
        // creates new player (constructer)
        name = n;
        cards = new ArrayList<>();
        money = initialMoney;
    }

    public void addCard(Card card) {
        // gives the player a card when they hit
        cards.add(card);
    }

    public boolean placeBet(int betAmount) {
        if (betAmount <= money && betAmount > 0) {
            currentBet = betAmount;
            money -= betAmount;
            // if player has sufficient funds the amount bet is withdrawn
            return true;
        } else {
            System.out.print("Insufficient funds. Would you like to add more money to your fund? ");
            String input = scanner.next();
            // player is given the option to add more money to their fund
            if (input.equals("yes")) {
                System.out.print("How much would you like to add? ");
                String add = scanner.next();
                int funds = Integer.parseInt(add);
                money += funds;
                System.out.println();
            } else {
                System.out.println("Invalid bet amount. Please enter an amount between " + money + " and 0");
            }
            return false;
        }
    }

    public void winBet() {
        money += (currentBet * 2); // Win double the bet amount
        currentBet = 0;
    }

    public void loseBet() {
        currentBet = 0; // dealer keeps money
    }

    public int getMoney() {
        // how much money the player has
        return money;
    }

    public int getHandValue() {
        // the sum of the value of all cards the player has
        int value = 0;
        int acesCount = 0;

        for (Card card : cards) {
            if (card.getRank().equals("Ace")) {
                acesCount++;
                value += 11;
            } else {
                value += card.getNumericValue();
            }
        }

        // Adjust for aces
        while (value > 21 && acesCount > 0) {
            value -= 10; // Count the Ace as 1 instead of 11
            acesCount--;
        }

        return value;
    }

    public ArrayList<Card> getCards() {
        // returns a list of cards the player has
        return cards;
    }

    public String getName() {
        // return the player's name
        return name;
    }

    public String getCardList() {
        // prints the list of cards the player has
        StringBuilder cardList = new StringBuilder();
        for (Card card : cards) {
            cardList.append(card.toString()).append(", ");
        }
        return cardList.length() > 0 ? cardList.substring(0, cardList.length() - 2) : cardList.toString();
    }

    public void resetHand() {
        // clears the player's cards
        cards.clear();
    }
}
