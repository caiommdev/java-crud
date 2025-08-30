package org.example.application.controllers;

import org.example.application.dtos.CardRequestDto;
import org.example.domain.entities.Card;
import org.example.infrastructure.CardRepository;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/cards")
public class CardController {
    private final CardRepository cardRepository;

    public CardController(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @GetMapping
    public ArrayList<Card> getCards() {
        return cardRepository.getCards();
    }

    @PostMapping
    public void addCard(@RequestBody CardRequestDto request) {
        Card card = new Card(request.getCollection(), request.getName(), new java.math.BigDecimal(request.getPrice()));
        cardRepository.addCard(card);
    }

    @PutMapping("/{id}")
    public Card updateCard(@PathVariable long id, @RequestBody CardRequestDto request) {
        Card card = new Card(request.getCollection(), request.getName(), new java.math.BigDecimal(request.getPrice()));
        return cardRepository.updateCard(id, card);
    }

    @DeleteMapping("/{id}")
public void deleteCard(@PathVariable long id) {
        cardRepository.removeCard(id);
    }
}
