package org.example.infrastructure;

import org.example.domain.entities.Card;

import java.util.ArrayList;

public class CardRepository {
    ArrayList<Card> cards = new ArrayList<>();

    public void addCard(Card card) {
        cards.add(card);
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public void removeCard(long id) {
        for(int i = 0; i < cards.size(); i++) {
            if (cards.get(i).getId() == id) {
                cards.remove(i);
                return;
            }
        }
    }

    public void updateCard(long id, Card card) {
        for (int i = 0; i < cards.size(); i++) {
            if (cards.get(i).getId() == id) {
                cards.set(i, card);
                return;
            }
        }
    }
}
