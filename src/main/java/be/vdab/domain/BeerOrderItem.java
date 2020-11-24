package be.vdab.domain;

import javax.persistence.*;

@Entity
@Table(name = "BeerOrderItems")
public class BeerOrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;
    @ManyToOne
    @JoinColumn(name = "BeerId")
    private Beer beer;
    @Column(name = "Number")
    private int number;

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

    @Override
    public String toString() {
        return number + " x " + beer.getName();
    }

}
