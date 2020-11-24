package be.vdab;

import be.vdab.exceptions.InvalidBeerException;
import be.vdab.exceptions.InvalidNumberException;
import be.vdab.service.BeerService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class TransactionApp {
    public static void main(String[] args) throws InvalidNumberException, InvalidBeerException {

        ConfigurableApplicationContext ctx = SpringApplication.run(TransactionApp.class,args);
        BeerService beerService = ctx.getBean(BeerService.class);

        //beerService.orderBeer("Elias",49,5);
        beerService.orderBeers("Timothy",new int[][]{{1,2},{27,1}});

    }
}
