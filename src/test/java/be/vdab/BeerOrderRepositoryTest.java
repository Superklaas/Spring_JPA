package be.vdab;

import be.vdab.domain.Beer;
import be.vdab.domain.BeerOrder;
import be.vdab.domain.BeerOrderItem;
import be.vdab.repository.BeerOrderRepository;
import be.vdab.repository.BeerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = BeerApp.class)
public class BeerOrderRepositoryTest {

    @Autowired
    BeerRepository beerRepository;
    @Autowired
    BeerOrderRepository beerOrderRepository;

    Beer testBeer;
    BeerOrder testOrder;
    BeerOrderItem testItem;

    @BeforeEach
    void before() {
        testBeer = beerRepository.getBeerById(1);
        testOrder = new BeerOrder().setName("Klaas");
        testItem = new BeerOrderItem().setBeer(testBeer).setNumber(5).setBeerOrder(testOrder);
        testOrder.setItems(Arrays.asList(new BeerOrderItem[]{testItem}));
    }

    @Test
    void getBeerOrderById() {
        BeerOrder beerOrder = beerOrderRepository.getBeerOrderById(1);
        assertEquals("JoeBiden",beerOrder.getName());
    }

    @Test
    void saveOrder() {
        int idTestOrder = beerOrderRepository.saveOrder(testOrder);
        assertEquals(2,idTestOrder);
        BeerOrder beerOrder = beerOrderRepository.getBeerOrderById(2);
        assertEquals("Klaas",beerOrder.getName());
        assertEquals(testItem.toString().trim(),beerOrder.getItems().get(0).toString().trim());
        assertEquals(testBeer.toString(),beerOrder.getItems().get(0).getBeer().toString());
        assertEquals(5,beerOrder.getItems().get(0).getNumber());
        assertEquals(testOrder.toString(),beerOrder.getItems().get(0).getBeerOrder().toString());
    }

}
