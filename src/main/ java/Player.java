package main.java;
import java.util.ArrayList;
import java.util.Arrays;

public class Player {
    private String name;
    private ArrayList<Integer> cards;

    public Player(String n) {
        name = n;
        cards = new ArrayList<>();
    }

    public void addCards(int card1, int card2) {
        cards.add(card1);
        cards.add(card2);
        return;
    }

    public void hit(int card) {
        cards.add(card);
        return;
    }

    public int getSum() {
        int sum = 0;
        for (int c : cards) {
            sum += c;
        }
        return sum;
    }

    public ArrayList<Integer> getCards() {
        return cards;
    }

    public String getName() {
        return name;
    }

    public String printCards() {
        String temp = "";
        for (int card : cards) {
            temp += card + " ";
        }
        return temp;
    }
}
