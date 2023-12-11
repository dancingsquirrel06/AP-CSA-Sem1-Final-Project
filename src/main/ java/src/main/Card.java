package src.main;

public class Card {
    private String suit;
    private String rank;

    public Card(String rank, String suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public String getSuit() {
        // returns suit of the card
        return suit;
    }

    public String getRank() {
        // returns rank of the card
        return rank;
    }

    public int getNumericValue() {
        // returns numeric value of the card (not always the same as the rank)
        switch (rank) {
            case "Ace":
                return 11; // Ace can be 1 or 11, but initially, we'll treat it as 11
            case "King":
            case "Queen":
            case "Jack":
                return 10;
            default:
                return Integer.parseInt(rank); // For cards 2-10
        }
    }

    @Override
    public String toString() {
        return rank + " of " + suit;
    }
}
