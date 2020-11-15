package be.vdab;

import be.vdab.domain.Beer;
import be.vdab.domain.BeerOrder;
import be.vdab.domain.BeerOrderItem;
import be.vdab.repository.BeerOrderRepository;
import be.vdab.repository.BeerRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class BeerApp {
    public static void main(String[] args) {

        ConfigurableApplicationContext ctx = SpringApplication.run(BeerApp.class,args);
        BeerRepository beerRepository = ctx.getBean(BeerRepository.class);
        BeerOrderRepository beerOrderRepository = ctx.getBean(BeerOrderRepository.class);

        // test getBeerById
        Beer aldegondeBrune = beerRepository.getBeerById(20);
        Beer adler = beerRepository.getBeerById(11);
        System.out.println(aldegondeBrune.toString());
        System.out.println(adler.toString());

        // test getBeerByAlcohol
        List<Beer> beerList = beerRepository.getBeerByAlcohol(7);
        for (Beer b : beerList) {
            System.out.println(b.toString());
        }

        // test updateBeer
        aldegondeBrune.setStock(300);
        beerRepository.updateBeer(aldegondeBrune);

        // test saveOrder
        BeerOrder orderKlaas = new BeerOrder().setName("Klaas");
        BeerOrderItem item1 = new BeerOrderItem().setBeer(aldegondeBrune).setNumber(5).setBeerOrder(orderKlaas);
        BeerOrderItem item2 = new BeerOrderItem().setBeer(adler).setNumber(2).setBeerOrder(orderKlaas);
        orderKlaas.setItems(Arrays.asList(new BeerOrderItem[]{item1, item2}));
        int idOrderKlaas = beerOrderRepository.saveOrder(orderKlaas);
        System.out.println("The id of orderKlaas is " + idOrderKlaas);

        // test getBeerOrderById
        orderKlaas = beerOrderRepository.getBeerOrderById(28);
        System.out.println(orderKlaas.toString());

    }
}
