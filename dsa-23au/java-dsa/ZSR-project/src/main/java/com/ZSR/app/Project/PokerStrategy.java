package com.ZSR.app.Project;
import java.util.*;

public class PokerStrategy {
    private String hand;
    private String suit;
    private String unopenedPotEP;
    private String unopenedPotLP;
    private String withLimperEP;
    private String withLimperLP;
    private String raiseInFrontEP;
    private String raiseInFrontLP;

    public PokerStrategy(String hand, String suit, String unopenedPotEP, String unopenedPotLP, String withLimperEP, String withLimperLP, String raiseInFrontEP, String raiseInFrontLP) {
        this.hand = hand;
        this.suit = suit;
        this.unopenedPotEP = unopenedPotEP;
        this.unopenedPotLP = unopenedPotLP;
        this.withLimperEP = withLimperEP;
        this.withLimperLP = withLimperLP;
        this.raiseInFrontEP = raiseInFrontEP;
        this.raiseInFrontLP = raiseInFrontLP;
    
    }

    public String getHand() {
        return hand;
    }

    public String getSuit() {
        return suit;
    }

    public String getUnopenedPotEP() {
        return unopenedPotEP;
    }

    public String getUnopenedPotLP() {
        return unopenedPotLP;
    }

    public String getWithLimperEP() {
        return withLimperEP;
    }

    public String getWithLimperLP() {
        return withLimperLP;
    }

    public String getRaiseInFrontEP() {
        return raiseInFrontEP;
    }

    public String getRaiseInFrontLP() {
        return raiseInFrontLP;
    }
}
