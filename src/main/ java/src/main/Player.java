package src.main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Player {
    private static final Scanner scanner = new Scanner(System.in);
    private int money;
    private int currentBet;
    private String name;
    private ArrayList<Card> cards;

    public Player(String n, int initialMoney) {
        name = n;
        cards = new ArrayList<>();
        money = initialMoney;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public boolean placeBet(int betAmount) {
        if (betAmount <= money && betAmount > 0) {
            currentBet = betAmount;
            money -= betAmount;
            return true;
        } else {
            System.out.print("Insufficient funds. Would you like to add more money to your fund? ");
            String input = scanner.next();
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
        currentBet = 0;
    }

    public int getMoney() {
        return money;
    }

    public int getHandValue() { // replaces getsum
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
        return cards;
    }

    public String getName() {
        return name;
    }

    // public String printCards() {
    // String temp = "";
    // for (int card : cards) {
    // temp += card + " ";
    // }
    // return temp;
    // }
    public String getCardList() { // replaces printcards
        StringBuilder cardList = new StringBuilder();
        for (Card card : cards) {
            cardList.append(card.toString()).append(", ");
        }
        return cardList.length() > 0 ? cardList.substring(0, cardList.length() - 2) : cardList.toString();
    }

    public void resetHand() {
        cards.clear();
    }

    public void addMoney() {

    }
}
