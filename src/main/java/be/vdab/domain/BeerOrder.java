package be.vdab.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "BeerOrders")
public class BeerOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;
    @Column(name = "Name")
    private String name;
    @OneToMany(mappedBy = "beerOrder",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<BeerOrderItem> items;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BeerOrder setName(String name) {
        this.name = name;
        return this;
    }

    public List<BeerOrderItem> getItems() {
        return items;
    }

    public BeerOrder setItems(List<BeerOrderItem> items) {
        this.items = items;
        return this;
    }

    @Override
    public String toString() {
        return "BeerOrder{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", items=" + items +
                '}';
    }

}
