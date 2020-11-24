package be.vdab.repository;

import be.vdab.domain.BeerOrder;
import be.vdab.domain.BeerOrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;

@Repository
public class BeerOrderRepository {

    private EntityManager em;

    @PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }

    @Transactional
    public int saveOrder(BeerOrder order) {
        em.persist(order);
        return order.getId();
    }

    public BeerOrder getBeerOrderById(int id) {
        return em.find(BeerOrder.class, id);
    }

}
