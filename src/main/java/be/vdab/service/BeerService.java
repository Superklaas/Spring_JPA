package be.vdab.service;

import be.vdab.domain.Beer;
import be.vdab.domain.BeerOrder;
import be.vdab.domain.BeerOrderItem;
import be.vdab.exceptions.InvalidBeerException;
import be.vdab.exceptions.InvalidNumberException;
import be.vdab.repository.BeerOrderRepository;
import be.vdab.repository.BeerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class BeerService {

    private BeerRepository beerRepository;
    private BeerOrderRepository beerOrderRepository;

    @Autowired
    public BeerService setBeerRepository(BeerRepository beerRepository) {
        this.beerRepository = beerRepository;
        return this;
    }

    @Autowired
    public void setBeerOrderRepository(BeerOrderRepository beerOrderRepository) {
        this.beerOrderRepository = beerOrderRepository;
    }

    @Transactional(rollbackFor={InvalidBeerException.class,InvalidNumberException.class})
    public int orderBeer(String name, int beerId, int number) throws InvalidNumberException, InvalidBeerException {
        int[][] order = {{beerId,number}};
        return orderBeers(name,order);
    }

    @Transactional(rollbackFor={InvalidBeerException.class,InvalidNumberException.class})
    public int orderBeers(String name, int[][] order) throws InvalidNumberException, InvalidBeerException {
        List<BeerOrderItem> items = getBeerOrderItems(order);
        updateBeers(order);
        int idOrder = saveOrder(name, items);
        return idOrder;
    }

    public List<BeerOrderItem> getBeerOrderItems(int[][] order) throws InvalidNumberException, InvalidBeerException {
        List<BeerOrderItem> items = new ArrayList<>();
        try {
            for (int i = 0; i < order.length; i++) {
                int beerId = order[i][0];
                int number = order[i][1];
                if (number < 0) {
                    throw new InvalidNumberException();
                }
                Beer beer = beerRepository.getBeerById(beerId);
                items.add(new BeerOrderItem().setBeer(beer).setNumber(number));
            }
        } catch (NullPointerException ex) {
            throw new InvalidBeerException();
        }
        return items;
    }

    public int saveOrder(String name, List<BeerOrderItem> items) {
        BeerOrder beerOrder = new BeerOrder().setName(name).setItems(items);
        int idOrder = beerOrderRepository.saveOrder(beerOrder);
        System.out.println("Beers ordered with idOrder " + idOrder);
        return idOrder;
    }

    public void updateBeers(int[][] order) throws InvalidBeerException {
        try {
            for (int i = 0; i < order.length; i++) {
                int number = order[i][1];
                int beerId = order[i][0];
                Beer beer = beerRepository.getBeerById(beerId);
                beer.setStock(beer.getStock()- number);
                beerRepository.updateBeer(beer);
            }
        } catch (NullPointerException ex) {
            throw new InvalidBeerException();
        }
    }

}

