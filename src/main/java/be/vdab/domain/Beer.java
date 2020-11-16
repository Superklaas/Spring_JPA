package be.vdab.domain;

import javax.persistence.*;

@Entity
@Table(name = "Beers")
public class Beer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;
    @Column(name = "Name")
    private String name;
    @Column(name = "Price")
    private float price;
    @Column(name = "Stock")
    private int stock;
    @Column(name = "Alcohol")
    private float alcohol;
    @Version
    @Column(name = "Version")
    private int version;
    @ManyToOne
    @JoinColumn(name = "BrewerId")
    private Brewer brewer;
    @ManyToOne
    @JoinColumn(name = "CategoryId")
    private Category category;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Beer setName(String name) {
        this.name = name;
        return this;
    }

    public float getPrice() {
        return price;
    }

    public Beer setPrice(float price) {
        this.price = price;
        return this;
    }

    public int getStock() {
        return stock;
    }

    public Beer setStock(int stock) {
        this.stock = stock;
        return this;
    }

    public float getAlcohol() {
        return alcohol;
    }

    public Beer setAlcohol(float alcohol) {
        this.alcohol = alcohol;
        return this;
    }

    public int getVersion() {
        return version;
    }

    public Beer setVersion(int version) {
        this.version = version;
        return this;
    }

    public Brewer getBrewer() {
        return brewer;
    }

    public Beer setBrewer(Brewer brewer) {
        this.brewer = brewer;
        return this;
    }

    public Category getCategory() {
        return category;
    }

    public Beer setCategory(Category category) {
        this.category = category;
        return this;
    }

    @Override
    public String toString() {
        return "Beer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                ", alcohol=" + alcohol +
                ", version=" + version +
                ", brewer=" + brewer +
                ", category=" + category +
                '}';
    }

}
