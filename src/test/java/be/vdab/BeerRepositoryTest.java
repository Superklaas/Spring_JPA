package be.vdab;

import be.vdab.domain.Beer;
import be.vdab.repository.BeerRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = DataAccessApp.class)
public class BeerRepositoryTest {

    @Autowired
    BeerRepository beerRepository;

    @Test
    void getBeerById() {
        Beer beer = beerRepository.getBeerById(1);
        assertEquals("TestBeer",beer.getName());
    }

    @Test
    void getBeerByAlcohol() {
        List<Beer> beerList = beerRepository.getBeerByAlcohol(5);
        assertEquals("TestBeer",beerList.get(0).getName());
    }

    @Test
    void updateBeer() {
        Beer beer = beerRepository.getBeerById(1);
        beer.setStock(250);
        beerRepository.updateBeer(beer);
        assertEquals(250,beerRepository.getBeerById(1).getStock());
    }

}
