package org.example.infrastructure;

import net.jqwik.api.*;
import net.jqwik.api.Combinators;
import org.example.domain.entities.Card;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class CardRepositoryPropertyTest {

    @Property
    @Report(Reporting.GENERATED)
    void addCardShouldIncreaseSize(@ForAll("validCards") Card card) {
        CardRepository repository = new CardRepository();
        int initialSize = repository.getCards().size();

        repository.addCard(card);

        assertEquals(initialSize + 1, repository.getCards().size());
        assertTrue(repository.getCards().contains(card));
    }

    @Property
    void getCardsShouldReturnAllAddedCards(@ForAll("validCards") Card card1,
                                          @ForAll("validCards") Card card2) {
        CardRepository repository = new CardRepository();

        repository.addCard(card1);
        repository.addCard(card2);

        assertEquals(2, repository.getCards().size());
        assertTrue(repository.getCards().contains(card1));
        assertTrue(repository.getCards().contains(card2));
    }

    @Property
    void removeCardShouldDecreaseSize(@ForAll("validCards") Card card) {
        CardRepository repository = new CardRepository();
        repository.addCard(card);
        int sizeAfterAdd = repository.getCards().size();

        repository.removeCard(card.getId());

        assertEquals(sizeAfterAdd - 1, repository.getCards().size());
        assertFalse(repository.getCards().contains(card));
    }

    @Property
    void removeNonExistentCardShouldNotChangeSize(@ForAll long nonExistentId) {
        CardRepository repository = new CardRepository();
        Card card = new Card("Test", "Test", new BigDecimal("10.00"));
        repository.addCard(card);

        Assume.that(nonExistentId != card.getId());

        int sizeBefore = repository.getCards().size();
        repository.removeCard(nonExistentId);

        assertEquals(sizeBefore, repository.getCards().size());
    }

    @Property
    void updateCardShouldReplaceExistingCard(@ForAll("validCards") Card originalCard,
                                           @ForAll("validCards") Card updatedCard) {
        CardRepository repository = new CardRepository();
        repository.addCard(originalCard);
        int sizeBefore = repository.getCards().size();

        repository.updateCard(originalCard.getId(), updatedCard);

        assertEquals(sizeBefore, repository.getCards().size());
        assertFalse(repository.getCards().contains(originalCard));
        assertTrue(repository.getCards().contains(updatedCard));
    }

    @Property
    void updateNonExistentCardShouldNotChangeRepository(@ForAll long nonExistentId,
                                                       @ForAll("validCards") Card updateCard) {
        CardRepository repository = new CardRepository();
        Card existingCard = new Card("Existing", "Card", new BigDecimal("5.00"));
        repository.addCard(existingCard);

        Assume.that(nonExistentId != existingCard.getId());

        int sizeBefore = repository.getCards().size();
        repository.updateCard(nonExistentId, updateCard);

        assertEquals(sizeBefore, repository.getCards().size());
        assertTrue(repository.getCards().contains(existingCard));
    }

    @Provide
    Arbitrary<Card> validCards() {
        return Combinators.combine(
                Arbitraries.strings().withCharRange('a', 'z').ofMinLength(1).ofMaxLength(30),
                Arbitraries.strings().withCharRange('A', 'Z').ofMinLength(1).ofMaxLength(30),
                Arbitraries.bigDecimals().between(new BigDecimal("0.01"), new BigDecimal("9999.99")).ofScale(2)
        ).as(Card::new);
    }
}
