
package src.main;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static ArrayList<Player> players = new ArrayList<>();
    private static ArrayList<Card> deck = new ArrayList<>();
    private static boolean gameRunning = true;

    public static void main(String[] args) {
        System.out.println("Welcome to BlackJack!");

        // Initialize players
        System.out.print("How many players will be playing in this game?: ");
        int numOfPlayers = scanner.nextInt();
        scanner.nextLine(); // Consume the remaining newline
        for (int i = 0; i < numOfPlayers; i++) {
            System.out.print("Enter player " + (i + 1) + "'s name: ");
            String playerName = scanner.nextLine();
            System.out.print("Enter initial money for " + playerName + ": ");
            int initialMoney = scanner.nextInt();
            scanner.nextLine(); // Consume the newline
            players.add(new Player(playerName, initialMoney));
        }

        while (gameRunning) {
            startGame();
            if (!askToPlayAgain()) {
                gameRunning = false;
            }
        }
    }

    public static void makeDeck() {
        deck.clear();
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};
        for (String suit : suits) {
            for (String rank : ranks) {
                deck.add(new Card(rank, suit));
            }
        }
        Collections.shuffle(deck);
    }

    public static void startGame() {
        makeDeck();
    
        // Initialize the dealer
        Player dealer = new Player("Dealer",0);
    
        // Deal cards to players and show their hands
        for (Player p : players) {
            boolean betPlaced = false;
            while (!betPlaced) {
                System.out.println(p.getName() + ", you have $" + p.getMoney());
                System.out.print("Enter your bet amount: ");
                int betAmount = scanner.nextInt();
                betPlaced = p.placeBet(betAmount);
            }
        }
        for (Player p : players) {
            p.addCard(deck.remove(0));
            p.addCard(deck.remove(0));
            System.out.println(p.getName() + "'s Cards: " + p.getCardList());
        }
    
        // Deal cards to dealer
        dealer.addCard(deck.remove(0));
        dealer.addCard(deck.remove(0));
    
        // Play rounds for players
        for (Player p : players) {
            playerTurn(p);
        }
    
        // Dealer's turn
        dealerTurn(dealer);
    
        // Determine outcome for each player
        for (Player p : players) {
            determineOutcome(p, dealer);
        }
    
        // Reset players and dealer for the next game
        resetPlayers();
        dealer.resetHand();
    }
    

    public static void playerTurn(Player player) {
        while (true) {
            System.out.println(player.getName() + "'s turn. Would you like to hit or stay?");
            String input = scanner.next();

            if (input.equalsIgnoreCase("hit")) {
                player.addCard(deck.remove(0));
                System.out.println("Cards: " + player.getCardList());
                if (player.getHandValue() > 21) {
                    System.out.println("Bust for player " + player.getName() + "!");
                    break; // Exit the loop if the player busts
                }
            } else if (input.equalsIgnoreCase("stay")) {
                System.out.println(player.getName() + " stays.");
                break; // Exit the loop if the player chooses to stay
            } else {
                System.out.println("Invalid input. Please choose 'hit' or 'stay'.");
            }
        }
    }

    public static void dealerTurn(Player dealer) {
        System.out.println("Dealer's turn.");
        while (dealer.getHandValue() < 17) {
            dealer.addCard(deck.remove(0));
        }
        System.out.println("Dealer's Cards: " + dealer.getCardList());
        if (dealer.getHandValue() > 21) {
            System.out.println("Dealer busts!");
        }
    }

  
    public static void determineOutcome(Player player, Player dealer) {
        int playerValue = player.getHandValue();
        int dealerValue = dealer.getHandValue();

        System.out.print(player.getName() + ": ");
        if (playerValue > 21) {
            System.out.println("Busts. Loss.");
            player.loseBet();
        } else if (dealerValue > 21 || playerValue > dealerValue) {
            System.out.println("Wins.");
            player.winBet();
        } else if (playerValue == dealerValue) {
            System.out.println("Push (tie).");
            player.winBet(); // In a push, the player gets their bet back
        } else {
            System.out.println("Loss.");
            player.loseBet();
        }
    }

    public static boolean askToPlayAgain() {
        System.out.print("Would you like to play again? (yes/no): ");
        String input = scanner.next();
        if (input.equalsIgnoreCase("no")) {
            System.out.println("Thank you for playing!");
            return false;
        }
        return true;
    }

    public static void resetPlayers() {
      for (Player p : players) {
            p.resetHand();
        }
    }
}