package Storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Warehouse warehouse = new Warehouse();
        PointOfSale pointOfSale = new PointOfSale();
        Human UFallTag = new Customer();
        warehouse.setInfo();

        Human worker = new ResponsiblePerson();
        System.out.println("\nPаполните информацию о РАБОТНИКЕ");
        worker.setValue();

        pointOfSale.setInfo();
//        Warehouse warehouse2 = new Warehouse();
//        warehouse2.setInfo();
//        warehouse.swapResponsiblePerson(warehouse2);
//        warehouse.getInfo();
//        warehouse2.getInfo();

        warehouse.purchase();
        //       warehouse.responsibleperson.firedWorker();
        //warehouse.closing(pointOfSale);
        warehouse.exchsngingProducts(pointOfSale);
        pointOfSale.sale(UFallTag);


        warehouse.output("data.json");

        System.out.println("DСЕГО ХОРОШЕГО");


    }
}