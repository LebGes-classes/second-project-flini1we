package Storage;


import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.Jsoner;
import org.json.simple.JSONObject;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.SortedMap;

public abstract class Storage {
    static Scanner scan = new Scanner(System.in);
    private String id;
    private String address;
    private String idResponsiblePerson;
    private double fill;
    private double capacity;
    ResponsiblePerson responsibleperson = new ResponsiblePerson();
    private boolean state;
    List<Product> products = new ArrayList();
    //List<Integer> productsSize = new ArrayList<>();

   // List<Integer> productsPrice = new ArrayList<>();

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIdResponsiblePerson() {
        return idResponsiblePerson;
    }

    public void setIdResponsiblePerson(String idResponsiblePerson) {
        responsibleperson.setValue();
        responsibleperson.setId(idResponsiblePerson);
        this.idResponsiblePerson = idResponsiblePerson;
    }

    public double getFill() {
        return fill;
    }

    public void setFill(double fill) {
        this.fill = fill;
    }

    public double getCapacity() {
        return capacity;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }



    public void setInfo(){
        String tempInfoStr;
        int tempInfoInt;
        setFill(0);
        setState(true);
        System.out.println("ЗАПОЛНИТЕ ИНФОРМАЦИЮ О СКЛАДЕ ");
        do {
            System.out.println("ID: ");
            tempInfoStr = scan.next();
            setId(tempInfoStr);
        }while(tempInfoStr.isEmpty());


        do {
            System.out.println("АДРЕСС: ");
            tempInfoStr = scan.next();
            setAddress(tempInfoStr);
        }while(tempInfoStr.isEmpty());

        do {
            System.out.println("ВМЕСТИТЕЛЬНОСТЬ: ");
            tempInfoInt = scan.nextInt();
            setCapacity(tempInfoInt);
        }while(tempInfoInt <= 0);
        do {
            System.out.println("ID ОТВЕТСТВЕННОГО ЛИЦА: ");
            tempInfoStr = scan.next();
            setIdResponsiblePerson(tempInfoStr);
        }while(tempInfoStr.isEmpty());
        getInfo();
    }
    public void getInfo(){
        if(state){
        System.out.println("=========== ИНФОРМАЦИЯ ===========");
        System.out.println("\tID: " +getId());
        System.out.println("\tАдрес: " +getAddress());
        System.out.println("\tВместительность: " +getCapacity());
        System.out.println("\tЗаполненость: " +getFill());
        System.out.println("\tID ответсвенного лица: " +getIdResponsiblePerson());
        System.out.println("\tТовары: " + products.toString());
        }else{
            System.out.println("ГГ бой");
        }
    }
    public void swapResponsiblePerson(Storage storage){
        String id = getIdResponsiblePerson();
        setIdResponsiblePerson(storage.getIdResponsiblePerson());
        storage.setIdResponsiblePerson(id);
    }

    public void exchsngingProducts(Storage storage){
        String response;
        if(state && storage.state) {
            do {
                System.out.println("Выберите товар который вы хотели бы переместить: ");
                for (int i = 0; i < products.size(); i++) {
                    System.out.println((i+1) +  ") " + products.get(i).name);
                }
                int temp = scan.nextInt();
                if (filling(storage, temp)) {
                    storage.products.add(products.get(temp - 1));


                    storage.setFill(storage.getFill() - products.get(temp - 1).size);
                    products.remove(temp - 1);
                    System.out.println("Товар успешно перенесен: ");
                } else {
                    System.out.println("Браток за местом следи");
                }
                System.out.println("Желаете ли вы продолжить? ");
                response = scan.next();
            } while (response.equalsIgnoreCase("да"));
        }
            //        }else{
//            System.out.println("(Базар фильтруй браток) Склад закрыт");
//        }
    }
    public boolean filling(Storage storage, int temp){
        if(getCapacity() > storage.getFill() + products.get(temp - 1).size){
            return true;
        }else{
            return false;
        }
    }

    public ResponsiblePerson getResponsibleperson() {
        return responsibleperson;
    }

    public void closing(Storage storage){
        setState(false);
        storage.products.addAll(products);


        products.removeAll(products);


        System.out.println("Закрыто брат");
    }


    public void output (String Data) {
        try {
            BufferedWriter writer = Files.newBufferedWriter(Paths.get(Data));

            JSONObject storageJsonObject = new JSONObject();
            JSONObject responsiblePersonObject = new JSONObject();
            JsonArray productCellsArray = new JsonArray();

            responsiblePersonObject.put("ID responsiblePerson", getIdResponsiblePerson());
            responsiblePersonObject.put("Initials", getResponsibleperson().getInitials());
            responsiblePersonObject.put("dateOfBirth", getResponsibleperson().getDataBirth());

            for (int i = 0; i < products.size(); i++) {
                JSONObject productObject = new JSONObject();

                productObject.put("name", products.get(i).name);
                productObject.put("price", products.get(i).price);
                productObject.put("size", products.get(i).size);

                productCellsArray.add(productObject);
            }

            storageJsonObject.put("id", id);
            storageJsonObject.put("address", address);
            storageJsonObject.put("capacity", capacity);
            storageJsonObject.put("fill", fill);
            storageJsonObject.put("responsiblePerson", responsiblePersonObject);
            storageJsonObject.put("productCells", productCellsArray);

            Jsoner.serialize(storageJsonObject, writer);

            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
