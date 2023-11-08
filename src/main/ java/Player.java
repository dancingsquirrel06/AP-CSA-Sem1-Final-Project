package src.main.java;
import java.util.ArrayList;
import java.util.Arrays;

public class Player {
    private String name;
    private ArrayList<Integer> cards;

    public Player(int card1, int card2, String n) {
        name = n;
        cards = new ArrayList<>();
    }

    public void deal() {
        cards.add(card1);
        cards.add(card2);
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
}
