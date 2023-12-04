package com.ZSR.app.Project;
import java.util.*;

public class Game {
    public static void main(String[] args) {
        Deck deck = new Deck();
        deck.shuffle();

        for (int i = 0; i < 5; i++) {
            deck.deal();
        }
    }
        //need add to hand
}
