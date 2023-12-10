package src.main;
import java.util.ArrayList;
import java.util.Arrays;



public class Player {
    private String name;
    private ArrayList<Card> cards;

    public Player(String n) {
        name = n;
        cards = new ArrayList<>();
    }

    public void addCard(Card card) {
       cards.add(card);
    }


   
    public int getHandValue() { //replaces getsum
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
    //     String temp = "";
    //     for (int card : cards) {
    //         temp += card + " ";
    //     }
    //     return temp;
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
}
