package Storage;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Warehouse extends Storage{
    static Scanner scan = new Scanner(System.in);
    
    List<Dealer> dealers;
    @Override
    public String toString() {
        return "Warehouse{" +
                "products=" + products +
                '}';
    }

    public void purchase() throws IOException {
        Path filePath = Path.of("/Users/danilzabinskij/IdeaProjects/demo/untitled2/root.json");///Users/danilzabinskij/IdeaProjects/demo/untitled2/root.json
        String content = Files.readString(filePath); //C:/Users/core i5 9400/IdeaProjects/untitled2/root.json
        dealers = JsonParser.parseJson(content);//чтоб диллер было полем

        boolean continueShopping = true;
        do{
            System.out.println("\n===ИНФОРМАЦИЯ О ЗАКУПКЕ ТОВАРОВ===");
            int temper;
            for (int i = 0; i < 8; i++) {
                int z = i+1;
                System.out.println(z + ") " + dealers.get(i).name);
            }

            System.out.println("\nВыберите цифру от 1 до 8 соответственно для поставшика : ");
            temper = scan.nextInt();
            switch (temper){
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                    for (int i = 0; i < dealers.get(temper-1).getProducts().size(); i++) {
                        int z = i+1;
                        System.out.println(z + ") " + dealers.get(temper-1).getProducts().get(i).toString());
                    }
                    break;
                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
                    break;
            }
            System.out.println("Аналогично выберите товар Поставщика: ");
            int temp = 0;
            String response;
            boolean flag = true;
            do {
                int product = scan.nextInt() - 1;

                int productSize = dealers.get(temper-1).getProducts().get(product).size;
                if (getCapacity() >= productSize) {
                    products.add(dealers.get(temper-1).getProducts().get(temper-1));
                    setCapacity(getCapacity() - productSize);
                    System.out.println("Остаточная вместимость на складе: " + getCapacity());
                } else {
                    System.out.println("Вы превысили вместимость хранилища");
                    flag = false;
                    break;
                }

                System.out.println("Желаете ли вы продолжитть закупку?");
                response = scan.next();
            } while (response.equalsIgnoreCase("да") && flag);

            System.out.println("ΔΜØŇǤ ỮŞ ඞ" + "📜" + "\nВаша корзина==============");
            System.out.println(products.toString());

            System.out.println("Хотите продолжить покупку у другого поставщика? (да/нет)");
            String continueResponse = scan.next();
            if (continueResponse.equalsIgnoreCase("да")){
                continueShopping = true;
            }else{
                continueShopping = false;
            }
        }while (continueShopping);
    }



    public boolean filling(int temler, int product){
        int temp = dealers.get(temler - 1).getProducts().get(product - 1).size;
        if(getCapacity() > getFill() + temp){
            return true;
        }else{
            return false;
        }
    }

    public void swapResponsiblePerson(Warehouse warehouse){
        String id = getIdResponsiblePerson();
        setIdResponsiblePerson(warehouse.getIdResponsiblePerson());
        warehouse.setIdResponsiblePerson(id);
    }




}
