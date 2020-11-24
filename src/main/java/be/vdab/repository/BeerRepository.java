package be.vdab.repository;

import be.vdab.domain.Beer;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.List;

@Repository
public class BeerRepository {

    private EntityManager em;

    @PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }

    public Beer getBeerById(int id) {
        return em.find(Beer.class,id);
    }

    public List<Beer> getBeerByAlcohol(float alcohol) {
        return em.createQuery("select b from Beer b where b.alcohol = " + alcohol,Beer.class).getResultList();
    }

    @Transactional
    public void updateBeer(Beer beer) {
        Beer dbBeer = em.find(Beer.class,beer.getId());
        dbBeer.setName(beer.getName());
        dbBeer.setPrice(beer.getPrice());
        dbBeer.setStock(beer.getStock());
        dbBeer.setAlcohol(beer.getAlcohol());
        dbBeer.setBrewer(beer.getBrewer());
        dbBeer.setCategory(beer.getCategory());
        System.out.println("beer " + dbBeer.getName() + " updated");
        System.out.println("new stock: " + dbBeer.getStock());
    }

}
