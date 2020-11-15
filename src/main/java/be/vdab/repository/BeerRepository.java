package be.vdab.repository;

import be.vdab.domain.Beer;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;
import java.util.List;

@Repository
public class BeerRepository {

    private EntityManagerFactory emf;

    @PersistenceUnit
    public void setEmf(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public Beer getBeerById(int id) {
        EntityManager em = emf.createEntityManager();
        Beer beer = em.find(Beer.class,id);
        em.close();
        return beer;
    }

    public List<Beer> getBeerByAlcohol(float alcohol) {
        EntityManager em = emf.createEntityManager();
        List<Beer> beerList =
                em.createQuery("select b from Beer b where b.alcohol = " + alcohol,Beer.class).getResultList();
        em.close();
        return beerList;
    }

    public void updateBeer(Beer beer) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Beer dbBeer = em.find(Beer.class,beer.getId());
        dbBeer.setName(beer.getName());
        dbBeer.setPrice(beer.getPrice());
        dbBeer.setStock(beer.getStock());
        dbBeer.setAlcohol(beer.getAlcohol());
        dbBeer.setBrewer(beer.getBrewer());
        dbBeer.setCategory(beer.getCategory());
        tx.commit();
        em.close();
        System.out.println("beer updated");
    }

}
