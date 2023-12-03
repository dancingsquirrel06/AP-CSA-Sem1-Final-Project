package main.java;
import java.util.ArrayList;
import java.util.Arrays;

public class Player {
    private String name;
    private ArrayList<Card> cards;

    public Player(String n) {
        name = n;
        cards = new ArrayList<>();
    }

    public void addCards(Card card) {
       cards.add(card);
    }

    public void hit(int card) {
        cards.add(card);
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
