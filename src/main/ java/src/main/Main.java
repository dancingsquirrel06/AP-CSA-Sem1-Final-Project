// package src.main;
// import java.util.ArrayList;
// import java.util.Collections;
// import java.util.Scanner;



// public class Main {
    
//     private static final Scanner scanner = new Scanner(System.in);
//     private static ArrayList<Player> players = new ArrayList<>();
//     private static ArrayList<Card> deck = new ArrayList<>();
//     private static boolean gameRunning = true;

//     public static void main(String[] args) {
//         System.out.println("Welcome to BlackJack!");

//         // Initialize players
//         System.out.print("How many players will be playing in this game?: ");
//         int numOfPlayers = scanner.nextInt();
//         for (int i = 0; i < numOfPlayers; i++) {
//             System.out.print("Enter player " + (i + 1) + "'s name: ");
//             String playerName = scanner.next();
//             players.add(new Player(playerName));
//         }

//         while (gameRunning) {
//             startGame();
//         }
//     }

//     public static void makeDeck() {
//         deck.clear();
//         String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
//         String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};
//         for (String suit : suits) {
//             for (String rank : ranks) {
//                 deck.add(new Card(rank, suit));
//             }
//         }
//         Collections.shuffle(deck);
//         return;
//     }

//     public static void startGame() {
//         makeDeck();
//         for (Player p : players) {
//             p.addCard(deck.remove(0));
//             p.addCard(deck.remove(0));
//             System.out.println(p.getName() + "'s Cards: " + p.getCardList());
//         }
//         round();
//         return;
//     }

//     public static void round() {
//         for (Player p : players) {
//             // while (true) {
//                 System.out.println(p.getName() + "'s turn. Would you like to hit or stay?");
//                 String input = scanner.next();
//                 if (input.equalsIgnoreCase("hit")) {
//                     p.addCard(deck.remove(0));
//                     System.out.println("Cards: " + p.getCardList());
//                     if (p.getHandValue() > 21) {
//                         System.out.println("Bust for player " + p.getName() + "!");
//                         players.remove(p);
//                         if (players.size() == 0) {
//                             end();
//                             return;
//                         }
//                     } else if (p.getHandValue() == 21) {
//                         System.out.println(p.getName() + " wins!");
//                         end();
//                         return;
//                     }
//                 }
//             // }
//         }
//         round();
//         return;
//     }

//     public static void end() {
//         System.out.print("Would you like to play again? (yes/no): ");
//         String input = scanner.next();
//         if (input.equalsIgnoreCase("no")) {
//             System.out.println("Thank you for playing!");
//             gameRunning = false;
//         }
//         return;
//         // Reset players for a new game
//         // for (Player p : players) {
//         //     p.resetHand();
//         // }
//     }
// }
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
        for (int i = 0; i < numOfPlayers; i++) {
            System.out.print("Enter player " + (i + 1) + "'s name: ");
            String playerName = scanner.next();
            players.add(new Player(playerName));
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
        for (Player p : players) {
            p.addCard(deck.remove(0));
            p.addCard(deck.remove(0));
            System.out.println(p.getName() + "'s Cards: " + p.getCardList());
        }
        boolean roundOver = false;
        while (!roundOver) {
            roundOver = round();
        }
        resetPlayers();
    }

    // public static boolean round() {
    //     for (Player p : players) {
    //         System.out.println(p.getName() + "'s turn. Would you like to hit or stay?");
    //         String input = scanner.next();
    //         if (input.equalsIgnoreCase("hit")) {
    //             p.addCard(deck.remove(0));
    //             System.out.println("Cards: " + p.getCardList());
    //             if (p.getHandValue() > 21) {
    //                 System.out.println("Bust for player " + p.getName() + "!");
    //                 return true;
    //             } else if (p.getHandValue() == 21) {
    //                 System.out.println(p.getName() + " wins!");
    //                 return true;
    //             }
    //         } else if (input.equalsIgnoreCase("stay")) {
    //             // Implement logic for when player chooses to stay
    //         }
    //     }
    //     return false;
    // }
    public static boolean round() {
        boolean roundOver = true;
    
        for (Player p : players) {
            // Check if the player has already busted in a previous turn
            if (p.getHandValue() > 21) {
                continue; // Skip the turn for this player
            }
    
            System.out.println(p.getName() + "'s turn. Would you like to hit or stay?");
            String input = scanner.next();
    
            if (input.equalsIgnoreCase("hit")) {
                p.addCard(deck.remove(0));
                System.out.println("Cards: " + p.getCardList());
                if (p.getHandValue() > 21) {
                    System.out.println("Bust for player " + p.getName() + "!");
                    // Don't end the round immediately to allow other players to finish their turns
                }
                roundOver = false; // Round continues as the player chose to hit
            } else if (input.equalsIgnoreCase("stay")) {
                System.out.println(p.getName() + " stays.");
                // Player chose to stay, check the next player
            } else {
                System.out.println("Invalid input. Please choose 'hit' or 'stay'.");
                roundOver = false; // Invalid input, give the player another chance
            }
        }
    
        return roundOver; // Return true if all players stayed or busted, false otherwise
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
