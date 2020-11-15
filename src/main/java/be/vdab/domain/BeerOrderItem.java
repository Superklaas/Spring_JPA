package be.vdab.domain;

import javax.persistence.*;

@Entity
@Table(name = "BeerOrderItems")
public class BeerOrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;
    @Column(name = "Number")
    private int number;
    @ManyToOne
    @JoinColumn(name = "BeerId")
    private Beer beer;
    @ManyToOne
    @JoinColumn(name = "BeerOrderId")
    private BeerOrder beerOrder;

    public int getId() {
        return id;
    }

    public int getNumber() {
        return number;
    }

    public BeerOrderItem setNumber(int number) {
        this.number = number;
        return this;
    }

    public Beer getBeer() {
        return beer;
    }

    public BeerOrderItem setBeer(Beer beer) {
        this.beer = beer;
        return this;
    }

    public BeerOrder getBeerOrder() {
        return beerOrder;
    }

    public BeerOrderItem setBeerOrder(BeerOrder beerOrder) {
        this.beerOrder = beerOrder;
        return this;
    }

    @Override
    public String toString() {
        return number + " x " + beer.getName();
    }

}
