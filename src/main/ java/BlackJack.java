package src.main.java;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class BlackJack {
    private ArrayList<Integer> deck = new ArrayList<>();
    private Random num = new Random();

    public void makeDeck() {
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


    public void deal(Player p) {
        int a = num.nextInt(deck.size() - 1);
        int card1 = deck.get(a);
        deck.remove(a);
        int b = num.nextInt(deck.size() - 1);
        int card2 = deck.get(b);
        deck.remove(b);
        p.deal(card1, card2);
        return;
    } 
}