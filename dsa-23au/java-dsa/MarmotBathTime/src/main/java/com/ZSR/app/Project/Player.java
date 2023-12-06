package com.ZSR.app.Project;
import java.util.*;

public class Player {
    private String name;
    private List<Card> hand;

    public Player(String name) {
        this.name = name;
        this.hand = new ArrayList<>();
    }

    public List<Card> getHand() {
        return this.hand;
    }

    public void drawCard(Deck deck) {
        this.hand.add(deck.deal());
    }

    /*public void redraw(Deck deck,List<Card> hand) {
        Scanner scanner = new Scanner(System.in);
        int count = 0;
        while (count < 3) {
            System.out.print("Redraw? 0 for NO, 1 - 5 to select CARD");
            int Selection = scanner.nextInt();
            if (Selection == 0) {
                break;
            }
            this.hand.remove(Selection);
            this.hand.add(deck.deal());  
            count++; 
        }
        scanner.close();
        System.out.print("Player" + name + "redrew" + (count+1) + "cards");
    }
*/
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
        for (int i = count + 1; i > 0;i-- ) {
            this.hand.add(deck.deal());
        }
        System.out.println("Player " + name + " redrew " + count + " cards"); //prints extra card, figure out
    }
    

    public void showHand() {
        for (Card card : hand) {
            System.out.print(card + " ");
        }
    }

    // betting
}