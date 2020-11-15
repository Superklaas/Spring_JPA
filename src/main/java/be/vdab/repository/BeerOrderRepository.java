package be.vdab.repository;

import be.vdab.domain.BeerOrder;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;

@Repository
public class BeerOrderRepository {

    private EntityManagerFactory emf;

    @PersistenceUnit
    public void setEmf(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public int saveOrder(BeerOrder order) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(order);
        tx.commit();
        em.close();
        return order.getId();
    }

    public BeerOrder getBeerOrderById(int id) {
        EntityManager em = emf.createEntityManager();
        BeerOrder order = em.find(BeerOrder.class,id);
        em.close();
        return order;
    }

}
