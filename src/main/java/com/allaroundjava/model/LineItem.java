package com.allaroundjava.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "LINE_ITEM")
public final class LineItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "item_name", nullable = false)
    private String itemName;
    @Column(nullable = false)
    private BigDecimal price;
    @Column(nullable = false)
    private int quantity;

    private LineItem() {

    }

    private LineItem(String name, BigDecimal price, int quantity) {
        this.itemName = name;
        this.price = price;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public String getItemName() {
        return itemName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public static LineItemBuilder getBuilder() {
        return new LineItemBuilder();
    }

    public static class LineItemBuilder {
        private String itemName;
        private BigDecimal price;
        private int quantity;

        public LineItemBuilder withItemName(String itemName) {
            this.itemName = itemName;
            return this;
        }

        public LineItemBuilder withPrice(BigDecimal price) {
            this.price = price;
            return this;
        }

        public LineItemBuilder withQuantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public LineItem build() {
            return new LineItem(this.itemName, this.price, this.quantity);
        }
    }
}
