package com.ZSR.app.Project;
import java.util.*;

public class Player {
    private String name;
    private List<Card> hand;
    private int chips;

    public Player(String name, int startingChips) {
        this.name = name;
        this.hand = new ArrayList<>();
        this.chips = startingChips;
    }

    public List<Card> getHand() {
        return this.hand;
    }

    public void drawCard(Deck deck) {
        this.hand.add(deck.deal());
    }

    public List<Card> sortHand() { //insertion sort
        for (int i = 1; i < hand.size(); i++) {
            Card currentCard = hand.get(i);
            int j = i - 1;
            while (j >= 0 && hand.get(j).getValue().compareTo(currentCard.getValue()) > 0) {
                hand.set(j + 1, hand.get(j));
                j = j - 1;
            }
            hand.set(j + 1, currentCard);
        }
        return hand; 
    }

    public void redraw(Deck deck, Scanner scanner) {
        int count = 0;
        while (count < 3) {
            System.out.println("Redraw? 0 for NO, 1-5 to select CARD");
            int selection = scanner.nextInt() - 1;
            if (selection < 0) {
                break;
            }
            if (selection >= 0 && selection < this.hand.size()) {
                this.hand.remove(selection);
                //this.hand.add(deck.deal());
                this.showHand();
                count++;
            } else {
                System.out.println("Invalid selection. Please try again.");
            }
        }
        for (int i = count; i > 0;i-- ) {
            this.hand.add(deck.deal());
        }
        System.out.println("Player " + name + " redrew " + count + " cards"); 
    }
    

    public void showHand() {
        for (Card card : hand) {
            System.out.print(card + ", ");
        }
    }

    public int getChips() {
        return this.chips;
    }

    public void setChips(int chips) {
        this.chips = chips;
    }

    public void showChips() {
        System.out.println("Player " + name + "has" + chips + "left");
    }

    public void awardPotToPlayer(Player winner, int Pot) {
        int currentChips = winner.getChips();
        winner.setChips(currentChips + Pot);
    }

    public int placeBet(int chips, Scanner scanner) {
        int bet = 0;
        boolean choice = false;
        while (!choice) {
            System.out.println("Place a bet? 1 for YES, 0 for NO.");
            int userChoice = scanner.nextInt();     
            if (userChoice == 1) {
                System.out.println("How much will you bet? You have " + this.chips + " CHIPS available.");
                bet = scanner.nextInt();
    
                if (bet > this.chips) {
                    System.out.println("Insufficient CHIPS. Please try again.");
                } else {
                    this.chips -= bet; 
                    choice = true; 
                }
            } else if (userChoice == 0) {
                choice = true; 
            } else {
                System.out.println("Invalid selection. Please try again.");
            }
        }
        return bet;
    }
    // betting
}