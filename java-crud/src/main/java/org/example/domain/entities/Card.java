package org.example.domain.entities;

import java.math.BigDecimal;
import java.util.UUID;

public class Card {
    private long id;
    private String collection;
    private String name;
    private BigDecimal price;

    public Card(String collection, String name, BigDecimal price) {
        this.collection = collection;
        this.name = name;
        this.price = price;
        this.id = UUID.randomUUID().hashCode();
    }

    public long getId() {
        return id;
    }

    public String getCollection() {
        return collection;
    }

    public void setCollection(String collection) {
        this.collection = collection;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
