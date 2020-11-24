package be.vdab;

import be.vdab.domain.BeerOrder;
import be.vdab.exceptions.InvalidBeerException;
import be.vdab.exceptions.InvalidNumberException;
import be.vdab.repository.BeerOrderRepository;
import be.vdab.repository.BeerRepository;
import be.vdab.service.BeerService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TransactionApp.class)
public class BeerServiceTest {

    @Autowired
    BeerRepository beerRepository;
    @Autowired
    BeerOrderRepository beerOrderRepository;
    @Autowired
    BeerService beerService;

    // test orderBeer(): ordering ONE beer

    @Test
    void orderBeer_CorrectValues() throws InvalidNumberException, InvalidBeerException {
        int orderId = beerService.orderBeer("Klaas", 1, 5);
        assertEquals(3, orderId);
        BeerOrder order = beerOrderRepository.getBeerOrderById(3);
        assertEquals("Klaas",order.getName());
        assertEquals("TestBeer",order.getItems().get(0).getBeer().getName());
        assertEquals(5,order.getItems().get(0).getNumber());
    }

    @Test
    void orderBeer_NonExistentBeer_InvalidBeerException(){
        assertThrows(
                InvalidBeerException.class,
                ()->{beerService.orderBeer("Klaas", 50, 5);}
        );
    }

    @Test
    void orderBeer_NegativeNumber_InvalidNumberException() {
        assertThrows(
                InvalidNumberException.class,
                ()->{beerService.orderBeer("Klaas", 1, -1);}
        );
    }

    // test orderBeers(): ordering MULTIPLE beers

    @Test
    void orderBeers_CorrectValues() throws InvalidNumberException, InvalidBeerException {
        int[][] order1 = new int[][]{{1,5},{1,4}};
        int orderId = beerService.orderBeers("Klaas", order1);
        assertEquals(2, orderId);
        BeerOrder order2 = beerOrderRepository.getBeerOrderById(2);
        assertEquals("Klaas",order2.getName());
        assertEquals("TestBeer",order2.getItems().get(0).getBeer().getName());
        assertEquals("TestBeer",order2.getItems().get(1).getBeer().getName());
        assertEquals(5,order2.getItems().get(0).getNumber());
        assertEquals(4,order2.getItems().get(1).getNumber());
    }

    @Test
    void orderBeers_NonExistentBeer_InvalidBeerException() {
        int[][] order = new int[][]{{50,5},{1,4}};
        assertThrows(
                InvalidBeerException.class,
                ()->{beerService.orderBeers("Klaas",order);}
        );
    }

    @Test
    void orderBeers_NegativeNumber_InvalidNumberException() {
        int[][] order = new int[][]{{1,-5},{1,4}};
        assertThrows(
                InvalidNumberException.class,
                ()->{beerService.orderBeers("Klaas",order);}
        );
    }

}
