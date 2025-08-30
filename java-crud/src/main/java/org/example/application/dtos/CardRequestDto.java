package org.example.application.dtos;

public class CardRequestDto {
    public String collection;
    public String name;
    public String price;

    public CardRequestDto() {}

    public CardRequestDto(String collection, String name, String price) {
        this.collection = collection;
        this.name = name;
        this.price = price;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
